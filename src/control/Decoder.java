package control;

/**
 * Decoder
 *
 * Responsible for extracting fields from a 16-bit instruction.
 *
 * Instruction formats supported:
 *
 * R-type:
 * [ opcode (5) | r1 (3) | r2 (3) | unused (5) ]
 *
 * I-type:
 * [ opcode (5) | reg (3) | immediate (8) ]
 *
 * J-type:
 * [ opcode (5) | address (11) ]
 *
 * Why this exists:
 * - Keeps bit manipulation OUT of ControlUnit
 * - Makes instruction handling clean and readable
 * - Centralizes instruction format logic
 */

public class Decoder {
    // Extracts the opcode (first 5 bits) from a 16-bit instruction
    public static int getOpcode(int instruction) {
        return (instruction >> 11) & 0b11111; // Mask to get 5 bits
    }

    // Extracts the first register (r1) from a 16-bit instruction
    public static int getR1(int instruction) {
        return (instruction >> 8) & 0b111; // Mask to get 3 bits
    }

    // Extracts the second register (r2) from a 16-bit instruction
    public static int getR2(int instruction) {
        return (instruction >> 5) & 0b111; // Mask to get 3 bits
    }

    // Extracts the immediate value (last 8 bits) from a 16-bit instruction
    public static int getImmediate(int instruction) {
        return instruction & 0xFF; // Mask to get 8 bits
    }

    // Extracts the address (last 11 bits) from a 16-bit instruction
    public static int getAddress(int instruction) {
        return instruction & 0x7FF; // Mask to get 11 bits
    }
}
