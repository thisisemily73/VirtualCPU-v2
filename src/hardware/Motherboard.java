package hardware;

import assembler.*;
import java.util.List;

public class Motherboard {

    private RAM ram;
    private CPU cpu;
    private Assembler assembler;

    public Motherboard() {
        ram = new RAM(4096);
        cpu = new CPU(ram);
        assembler = new Assembler();
    }

    /**
     * Loads assembly code into RAM
     */
    public void loadProgram(String program) {

        List<Integer> machineCode = assembler.assemble(program);

        for (int i = 0; i < machineCode.size(); i++) {
            ram.write(i, machineCode.get(i));
        }
    }

    /**
     * Runs until HALT
     */
    public void run() {
        while (cpu.step());
    }

    public CPU getCPU() {
        return cpu;
    }
}