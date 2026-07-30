package assembler;

/**
 * InstructionEncoder
 *
 * Converts parsed Instruction objects into 16-bit machine code.
 *
 * The output format follows the CPU ISA:
 *
 * R-Type: [ opcode (5) | regA (3) | regB (3) | unused (5) ]
 *
 * I-Type: [ opcode (5) | regA (3) | immediate (8) ]
 *
 * J-Type: [ opcode (5) | address (11) ]
 */
import control.Opcodes;

public class InstructionEncoder {

    // Convertts and instruction into a 16-bit integer
    public int encode(Instruction instruction) {
        int opcode = Opcodes.getOpcode(instruction.getOpcode());

        switch (instruction.getOpcode()) {

            case "NOP":
                return encodeBasic(Opcodes.NOP);

            // DATA
            case "LOADI":
                return encodeIType(Opcodes.LOADI,
                        instruction.getRegisterA(),
                        instruction.getImmediate());

            case "LOAD":
                return encodeIType(Opcodes.LOAD,
                        instruction.getRegisterA(),
                        instruction.getImmediate());

            case "STORE":
                return encodeIType(Opcodes.STORE,
                        instruction.getRegisterA(),
                        instruction.getImmediate());

            // ALU
            case "ADD":
                return encodeRType(Opcodes.ADD,
                        instruction.getRegisterA(),
                        instruction.getRegisterB());

            case "SUB":
                return encodeRType(Opcodes.SUB,
                        instruction.getRegisterA(),
                        instruction.getRegisterA());

            // CONTROL
            case "JMP":
                return encodeJump(Opcodes.JMP,
                        instruction.getImmediate());

            case "JZ":
                return encodeIType(Opcodes.JZ,
                        instruction.getRegisterA(),
                        instruction.getImmediate());

            case "JNZ":
                return encodeIType(Opcodes.JNZ,
                        instruction.getRegisterA(),
                        instruction.getImmediate());

            // STACK
            case "PUSH":
                return encodeSingleReg(Opcodes.PUSH,
                        instruction.getRegisterA());

            case "POP":
                return encodeSingleReg(Opcodes.POP,
                        instruction.getRegisterA());

            // LOGIC
            case "AND":
                return encodeRType(Opcodes.AND,
                        instruction.getRegisterA(),
                        instruction.getRegisterB());

            case "OR":
                return encodeRType(Opcodes.OR,
                        instruction.getRegisterA(),
                        instruction.getRegisterB());

            case "XOR":
                return encodeRType(Opcodes.XOR,
                        instruction.getRegisterA(),
                        instruction.getRegisterB());

            case "NOT":
                return encodeSingleReg(Opcodes.NOT,
                        instruction.getRegisterA());

            // SYSTEM
            case "HALT":
                return encodeBasic(Opcodes.HALT);

            default:
                throw new IllegalArgumentException(
                        "Unsupported instruction: " + instruction.getOpcode()
                );
        }
    }

    /**
     * Encodes instructions with only an opcode.
     *
     * Format: [ opcode (5) | unused (11) ]
     */
    private int encodeBasic(int opcode) {
        return opcode << 11;
    }

    /**
     * Encodes R-type instructions.
     *
     * Format: [ opcode (5) | regA (3) | regB (3) | unused (5) ]
     */
    private int encodeRType(int opcode, int regA, int regB) {
        return (opcode << 11)
                | (regA << 8)
                | (regB << 5);
    }

    /**
     * Encodes I-type instructions.
     *
     * Format: [ opcode (5) | regA (3) | immediate (8) ]
     */
    private int encodeIType(int opcode, int regA, int immediate) {
        return (opcode << 11)
                | (regA << 8)
                | (immediate & 0xFF); // ensure 8 bits
    }

    /**
     * Encodes jump instructions.
     *
     * Format: [ opcode (5) | address (11) ]
     */
    private int encodeJump(int opcode, int address) {
        return (opcode << 11)
                | (address & 0x7FF); // ensure 11 bits
    }

    /**
     * Encodes instructions with one register.
     *
     * Format: [ opcode (5) | regA (3) | unused (8) ]
     */
    private int encodeSingleReg(int opcode, int regA) {
        return (opcode << 11)
                | (regA << 8);
    }
}
