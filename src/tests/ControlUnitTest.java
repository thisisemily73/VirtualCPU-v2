package tests;

import hardware.*;
import control.ControlUnit;

/**
 * ControlUnitTest
 *
 * Purpose:
 * Verifies that the Control Unit correctly decodes and executes instructions.
 *
 * We will test:
 * - LOADI
 * - ADD
 * - SUB
 * - HALT
 */
public class ControlUnitTest {

    public static void main(String[] args) {

        // --- Initialize all CPU components ---
        RegisterFile registers = new RegisterFile();
        RAM ram = new RAM();
        ALU alu = new ALU();
        ProgramCounter pc = new ProgramCounter();
        FlagsRegister flags = new FlagsRegister();
        InstructionRegister ir = new InstructionRegister();

        ControlUnit cu = new ControlUnit(
            registers, ram, alu, pc, flags, ir
        );

        /**
         * Instruction format reminder:
         *
         * [ opcode (5 bits) | reg (3 bits) | reg (3 bits) | rest ]
         */

        // --- Program: ---
        // R1 = 10
        // R2 = 5
        // R1 = R1 + R2
        // HALT

        int instr1 = (0b00001 << 11) | (1 << 8) | 10; // LOADI R1, 10
        int instr2 = (0b00001 << 11) | (2 << 8) | 5;  // LOADI R2, 5
        int instr3 = (0b00100 << 11) | (1 << 8) | (2 << 5); // ADD R1, R2
        int instr4 = (0b11111 << 11); // HALT

        int[] program = {instr1, instr2, instr3, instr4};

        // --- Execute program manually ---
        for (int i = 0; i < program.length; i++) {

            ir.load(program[i]);

            boolean running = cu.execute();

            if (!running) {
                System.out.println("HALT encountered");
                break;
            }
        }

        // --- Check result ---
        System.out.println("R1 = " + registers.read(1)); // should be 15
        System.out.println("R2 = " + registers.read(2)); // should be 5
    }
}