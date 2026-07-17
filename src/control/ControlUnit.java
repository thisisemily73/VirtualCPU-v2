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
        int opcode = Decoder.getOpcode(instruction);

        switch (opcode) {

            case Opcodes.LOADI:
                int reg = Decoder.getRegister1(instruction);
                int value = Decoder.getImmediate(instruction);

                registers.store(reg, value);
                break;

            case Opcodes.ADD:
                int r1 = Decoder.getRegister1(instruction);
                int r2 = Decoder.getRegister2(instruction);

                int result = alu.add(
                    registers.read(r1),
                    registers.read(r2)
                );

                registers.store(r1, result);

                flags.setZeroFlag(result == 0);
                break;

            case Opcodes.SUB:
                int reg1 = Decoder.getRegister1(instruction);
                int reg2 = Decoder.getRegister2(instruction);

                int subresult = alu.sub(
                    registers.read(reg1),
                    registers.read(reg2)
                );

                registers.store(reg1, subresult);

                flags.setZeroFlag(subresult == 0);
                break;

            case Opcodes.JMP:
                int address = Decoder.getAddress(instruction);
                pc.set(address);
                break;

            case Opcodes.JZ:
                address = Decoder.getAddress(instruction);

                if (flags.isZeroFlag()) {
                    pc.set(address);
                }
                break;

            case Opcodes.JNZ:
                address = Decoder.getAddress(instruction);

                if (!flags.isZeroFlag()) {
                    pc.set(address);
                }
                break;

            case Opcodes.HALT:
                return false;

            default:
                System.out.println("Unknown opcode: " + opcode);
                return false;
        }

        return true;
    }
}