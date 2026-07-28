package server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        CPUService cpuService = new CPUService();
        CPUController cpuController = new CPUController(cpuService);

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/api/cpu/health", cpuController.healthHandler());
        server.createContext("/api/cpu/state", cpuController.stateHandler());
        server.createContext("/api/cpu/step", cpuController.stepHandler());
        server.createContext("/api/cpu/reset", cpuController.resetHandler());
        server.createContext("/api/cpu/load", cpuController.loadHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("CPU API server running at http://localhost:" + PORT);
    }
}