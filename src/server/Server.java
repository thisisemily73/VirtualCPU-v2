package server;

import assembler.Assembler;
import com.sun.net.httpserver.*;
import hardware.*;
import java.io.*;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/run", exchange -> {

            InputStream is = exchange.getRequestBody();
            String source = new String(is.readAllBytes());

            // STEP 1: Assemble text → machine code
            Assembler assembler = new Assembler();
            java.util.List<Integer> machineCode = assembler.assemble(source);

            // STEP 2: Load into RAM
            RAM ram = new RAM(4096);

            for (int i = 0; i < machineCode.size(); i++) {
                ram.write(i, machineCode.get(i));
            }

            // STEP 3: Run CPU
            CPU cpu = new CPU(ram);

            boolean running = true;
            while (running) {
                running = cpu.step();   // stops on HALT
            }

            // STEP 4: Read result
            int r1 = cpu.getRegisters().read(1);

            String response = "R1 = " + r1;

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Server running on http://localhost:8080");
    }
}
