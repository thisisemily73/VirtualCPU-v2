package control;

import hardware.*;

/**
 * ControlUnit (CU)
 *
 * Role in CPU: Decodes instructions and coordinates execution.
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
                       ALU alu, ProgramCounter pc,
                       FlagsRegister flags,
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
     */
    public boolean execute() {
        int instruction = ir.getInstruction();
        int opcode = Decoder.getOpcode(instruction);

        switch (opcode) {
            case Opcodes.LOADI: {
                // LOADI Rd, imm  ->  Rd = imm
                int rd = Decoder.getRd(instruction);
                int imm = Decoder.getImmediate(instruction);
                registers.store(rd, imm);
                break;
            }

            // Arithmetic

            case Opcodes.ADD: {
                // ADD Rd, Ra, Rb  ->  Rd = Ra + Rb
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);
                int rb = Decoder.getRb(instruction);

                int result = alu.add(
                    registers.read(ra),
                    registers.read(rb)
                );
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            case Opcodes.SUB: {
                // SUB Rd, Ra, Rb  ->  Rd = Ra - Rb
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);
                int rb = Decoder.getRb(instruction);

                int result = alu.sub(
                    registers.read(ra),
                    registers.read(rb)
                );
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            // Logic

            case Opcodes.AND: {
                // AND Rd, Ra, Rb  ->  Rd = Ra & Rb
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);
                int rb = Decoder.getRb(instruction);

                int result = alu.and(
                    registers.read(ra),
                    registers.read(rb)
                );
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            case Opcodes.OR: {
                // OR  Rd, Ra, Rb  ->  Rd = Ra | Rb
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);
                int rb = Decoder.getRb(instruction);

                int result = alu.or(
                    registers.read(ra),
                    registers.read(rb)
                );
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            case Opcodes.XOR: {
                // XOR Rd, Ra, Rb  ->  Rd = Ra ^ Rb
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);
                int rb = Decoder.getRb(instruction);

                int result = alu.xor(
                    registers.read(ra),
                    registers.read(rb)
                );
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            case Opcodes.NOT: {
                // NOT Rd, Ra  ->  Rd = ~Ra
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);

                int result = alu.not(registers.read(ra));
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            // Control Flow

            case Opcodes.JMP: {
                // JMP addr  ->  PC = addr
                int addr = Decoder.getAddress(instruction);
                pc.set(addr);
                break;
            }

            case Opcodes.JZ: {
                // JZ addr  ->  if Z then PC = addr
                int addr = Decoder.getAddress(instruction);
                if (flags.isZeroFlag()) {
                    pc.set(addr);
                }
                break;
            }

            case Opcodes.JNZ: {
                // JNZ addr  ->  if !Z then PC = addr
                int addr = Decoder.getAddress(instruction);
                if (!flags.isZeroFlag()) {
                    pc.set(addr);
                }
                break;
            }

            // System

            case Opcodes.HALT:
                return false;

            default:
                System.out.println("Unknown opcode: " + opcode);
                return false;
        }

        return true;
    }
}