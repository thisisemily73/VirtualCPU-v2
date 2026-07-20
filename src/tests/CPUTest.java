package tests;

import hardware.*;
import control.Opcodes;

public class CPUTest {

    public static void main(String[] args) {

        RAM ram = new RAM(4096);

        int instr1 = (Opcodes.LOADI << 11) | (1 << 8) | 5;
        int instr2 = (Opcodes.HALT << 11);

        ram.write(0, instr1);
        ram.write(1, instr2);

        CPU cpu = new CPU(ram);

        // Run until HALT
        while (cpu.step());

        // CHECK RESULT
        System.out.println("R1 = " + cpu.getRegisters().read(1));
    }
}