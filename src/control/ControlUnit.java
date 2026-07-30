package control;

import hardware.ALU;
import hardware.CPU;
import hardware.FlagsRegister;
import hardware.InstructionRegister;
import hardware.ProgramCounter;
import hardware.RAM;
import hardware.RegisterFile;

/**
 * ControlUnit (CU)
 *
 * Role in CPU: Decodes instructions and coordinates execution.
 *
 * Responsibilities: - Reads instruction from InstructionRegister - Extracts
 * opcode and operands - Executes the correct operation
 *
 * Interacts with: - RegisterFile - RAM - ALU - ProgramCounter - FlagsRegister
 */
public class ControlUnit {

    private RegisterFile registers;
    private RAM ram;
    private ALU alu;
    private ProgramCounter pc;
    private FlagsRegister flags;
    private InstructionRegister ir;
    private CPU cpu;

    public ControlUnit(RegisterFile registers, RAM ram,
            ALU alu, ProgramCounter pc,
            FlagsRegister flags,
            InstructionRegister ir,
            CPU cpu) {
        this.registers = registers;
        this.ram = ram;
        this.alu = alu;
        this.pc = pc;
        this.flags = flags;
        this.ir = ir;
        this.cpu = cpu;
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

                int a = registers.read(ra);
                int b = registers.read(rb);

                int result = alu.add(a, b);

                cpu.updateALU(a, b, result, "ADD");
                registers.store(rd, result);

                flags.setZeroFlag(result == 0);
                break;
            }

            case Opcodes.SUB: {
                // SUB Rd, Ra, Rb  ->  Rd = Ra - Rb
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);
                int rb = Decoder.getRb(instruction);

                int a = registers.read(ra);
                int b = registers.read(rb);

                int result = alu.sub(a, b);

                cpu.updateALU(a, b, result, "SUB");
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

                int a = registers.read(ra);
                int b = registers.read(rb);

                int result = alu.and(a, b);

                cpu.updateALU(a, b, result, "AND");
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            case Opcodes.OR: {
                // OR  Rd, Ra, Rb  ->  Rd = Ra | Rb
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);
                int rb = Decoder.getRb(instruction);

                int a = registers.read(ra);
                int b = registers.read(rb);

                int result = alu.or(a, b);

                cpu.updateALU(a, b, result, "OR");
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            case Opcodes.XOR: {
                // XOR Rd, Ra, Rb  ->  Rd = Ra ^ Rb
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);
                int rb = Decoder.getRb(instruction);

                int a = registers.read(ra);
                int b = registers.read(rb);

                int result = alu.xor(a, b);

                cpu.updateALU(a, b, result, "XOR");
                registers.store(rd, result);
                flags.setZeroFlag(result == 0);
                break;
            }

            case Opcodes.NOT: {
                // NOT Rd, Ra  ->  Rd = ~Ra
                int rd = Decoder.getRd(instruction);
                int ra = Decoder.getRa(instruction);

                int a = registers.read(ra);

                int result = alu.not(a);

                cpu.updateALU(a, 0, result, "NOT");
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
            case Opcodes.HALT: {
                return false;
            }

            case Opcodes.NOP: {
                break;
            }

            default:
                System.out.println("Unknown opcode: " + opcode);
                return false;
        }

        return true;
    }
}
