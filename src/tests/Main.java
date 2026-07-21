package tests;

import control.*;
import hardware.*;

public class Main {
    public static void main(String[] args) {
        RAM ram = new RAM(4096);
        int addr = 0;

        // --- Setup some values ---
        // LOADI R1, 5
        ram.write(addr++, (Opcodes.LOADI << 11) | (1 << 8) | 5);
        // LOADI R2, 10
        ram.write(addr++, (Opcodes.LOADI << 11) | (2 << 8) | 10);
        // LOADI R3, 12
        ram.write(addr++, (Opcodes.LOADI << 11) | (3 << 8) | 12);

        // --- Arithmetic ---
        // ADD R4, R1, R2      -> R4 = 5 + 10 = 15
        int add = (Opcodes.ADD << 11) | (4 << 8) | (1 << 5) | (2 << 2);
        ram.write(addr++, add);

        // SUB R5, R3, R1      -> R5 = 12 - 5 = 7
        int sub = (Opcodes.SUB << 11) | (5 << 8) | (3 << 5) | (1 << 2);
        ram.write(addr++, sub);

        // --- Logical ---
        // AND R6, R1, R3      -> R6 = 5 & 12 = 4  (0101 & 1100 = 0100)
        int and = (Opcodes.AND << 11) | (6 << 8) | (1 << 5) | (3 << 2);
        ram.write(addr++, and);

        // OR  R7, R1, R2      -> R7 = 5 | 10 = 15 (0101 | 1010 = 1111)
        int or = (Opcodes.OR << 11) | (7 << 8) | (1 << 5) | (2 << 2);
        ram.write(addr++, or);

        // XOR R0, R1, R2      -> R0 = 5 ^ 10 = 15 (0101 ^ 1010 = 1111)
        int xor = (Opcodes.XOR << 11) | (0 << 8) | (1 << 5) | (2 << 2);
        ram.write(addr++, xor);

        // NOT R1, R2          -> R1 = ~10 (16-bit) = 0xFFF5 = 65525
        int not = (Opcodes.NOT << 11) | (1 << 8) | (2 << 5);
        // Rb field is unused for NOT; we just put 0 there
        not = (not & ~0b11100) | (0 << 2);
        ram.write(addr++, not);

        // --- Jump test ---
        // LOADI R2, 7
        ram.write(addr++, (Opcodes.LOADI << 11) | (2 << 8) | 7);
        // JZ skip               (we expect Z=0, so this should NOT jump)
        int jz = (Opcodes.JZ << 11) | (addr + 2); // skip next instruction if Z=1
        ram.write(addr++, jz);
        // LOADI R3, 99          (this should still run)
        ram.write(addr++, (Opcodes.LOADI << 11) | (3 << 8) | 99);
        // JMP end
        int jmp = (Opcodes.JMP << 11) | (addr + 2);
        ram.write(addr++, jmp);
        // LOADI R3, 42          (this should be skipped)
        ram.write(addr++, (Opcodes.LOADI << 11) | (3 << 8) | 42);
        // end:
        // HALT
        ram.write(addr++, (Opcodes.HALT << 11));

        CPU cpu = new CPU(ram);

        int cycles = 0;
        boolean running = true;
        while (running && cycles < 200) {
            running = cpu.step();
            cycles++;
        }

        System.out.println("cycles=" + cycles);
        System.out.println("R0=" + cpu.getRegisters().read(0)); // XOR result
        System.out.println("R1=" + cpu.getRegisters().read(1)); // NOT result
        System.out.println("R2=" + cpu.getRegisters().read(2)); // 7
        System.out.println("R3=" + cpu.getRegisters().read(3)); // 99 (JZ should not jump)
        System.out.println("R4=" + cpu.getRegisters().read(4)); // ADD result
        System.out.println("R5=" + cpu.getRegisters().read(5)); // SUB result
        System.out.println("R6=" + cpu.getRegisters().read(6)); // AND result
        System.out.println("R7=" + cpu.getRegisters().read(7)); // OR result
    }
}