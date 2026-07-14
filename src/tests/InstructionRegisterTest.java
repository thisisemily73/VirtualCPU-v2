package tests;

import hardware.InstructionRegister;

public class InstructionRegisterTest {

    public static void main(String[] args) {

        InstructionRegister ir = new InstructionRegister();

        // Example: fake instruction
        // opcode=1, dest=2, src=3
        int instruction = (1 << 11) | (2 << 8) | (3 << 5);

        ir.load(instruction);

        System.out.println("Opcode: " + ir.getOpcode());
        System.out.println("Dest: " + ir.getDest());
        System.out.println("Src: " + ir.getSrc());
    }
}