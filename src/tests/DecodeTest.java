package tests;

import hardware.*;
import control.Opcodes;

public class DecodeTest {

    public static void main(String[] args) {

        InstructionRegister ir = new InstructionRegister();

        // Manually create an instruction:
        // LOADI R1, 10
        // opcode = 00001
        // dest = 001 (R1)
        // immediate = 00001010 (10)

        int instruction =
                (Opcodes.LOADI << 11) |   // opcode (top 5 bits)
                (1 << 8) |                // R1
                (10);                     // immediate value

        ir.load(instruction);

        // ===== DECODE =====
        int opcode = ir.getOpcode();
        int dest   = ir.getDest();
        int imm    = ir.getImmediate();
        // ==================

        System.out.println("Opcode = " + opcode);
        System.out.println("Dest   = R" + dest);
        System.out.println("Imm    = " + imm);
    }
}