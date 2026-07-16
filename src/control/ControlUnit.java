package control;

import hardware.*;

/**
 * ControlUnit (CU)
 *
 * Role in CPU:
 * Decodes instructions and coordinates execution.
 *
 * Responsibilities:
 * - Reads instruction from InstructionRegister
 * - Extracts opcode and operands
 * - Executes the correct operation
 *
 * Interacts with:
 * - RegisterFile
 * - RAM
 * - ALU
 * - ProgramCounter
 * - FlagsRegister
 */

public class ControlUnit {
    private RegisterFile registers;
    private RAM ram;
    private ALU alu;
    private ProgramCounter pc;
    private FlagsRegister flags;
    private InstructionRegister ir;

    public ControlUnit(RegisterFile registers, RAM ram,
        ALU alu, ProgramCounter pc, FlagsRegister flags,
        InstructionRegister ir) {
        this.registers = registers;
        this.ram = ram;
        this.alu = alu;
        this.pc = pc;
        this.flags = flags;
        this.ir = ir;
    }

    /**
     * Executes the instruction currently in the InstructionRegister.
     *
     * This method decodes the instruction and performs the appropriate action.
     */

    public boolean execute() {
        int instruction = ir.getInstruction();

        // Extract the opcode (top 5 bits) from the instruction.
        int opcode = (instruction >> 11) & 0b11111;

        switch (opcode) {
             case 0b00001: // LOADI
                int reg = (instruction >> 8) & 0b111;
                int value = instruction & 0xFF;

                registers.store(reg, value);
                break;

            case 0b00100: // ADD
                int r1 = (instruction >> 8) & 0b111;
                int r2 = (instruction >> 5) & 0b111;

                int result = alu.add(
                    registers.read(r1),
                    registers.read(r2)
                );

                registers.store(r1, result);

                flags.setZeroFlag(result == 0);
                break;

            case 0b00101: // SUB
                r1 = (instruction >> 8) & 0b111;
                r2 = (instruction >> 5) & 0b111;

                result = alu.sub(
                    registers.read(r1),
                    registers.read(r2)
                );

                registers.store(r1, result);

                flags.setZeroFlag(result == 0);
                break;

            case 0b00110: // JMP
                int address = instruction & 0x0FFF;
                pc.set(address);
                break;

            case 0b00111: // JZ
                address = instruction & 0x0FFF;
                if (flags.isZeroFlag()) {
                    pc.set(address);
                }
                break;

            case 0b01000: // JNZ
                address = instruction & 0x0FFF;
                if (!flags.isZeroFlag()) {
                    pc.set(address);
                }
                break;

            case 0b11111: // HALT
                return false;

            default:
                System.out.println("Unknown opcode: " + opcode);
                return false;
        }

        return true;
    }
}