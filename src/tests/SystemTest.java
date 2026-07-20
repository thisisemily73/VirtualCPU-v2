package tests;

import hardware.*;

public class SystemTest {

    public static void main(String[] args) {

        String program =
            "LOADI R1, 5\n" +
            "LOADI R2, 10\n" +
            "ADD R1, R2\n" +
            "HALT";

        Motherboard mb = new Motherboard();

        mb.loadProgram(program);
        mb.run();

        // 🔥 Check result
        int result = mb.getCPU().getRegisters().read(1);

        System.out.println("Final R1 = " + result);
    }
}