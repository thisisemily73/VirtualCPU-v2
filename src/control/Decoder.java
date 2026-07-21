package control;

/**
 * Decoder
 *
 * Responsible for extracting fields from a 16-bit instruction.
 *
 * Instruction formats supported:
 *
 * R-type:
 * [ opcode (5) | Rd (3) | Ra (3) | Rb (3) | unused (2) ]
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

    // Extracts the first register (Rd) from a 16-bit instruction
    public static int getRd(int instruction) {
        return (instruction >> 8) & 0b111; // Mask to get 3 bits
    }

    // Extracts the second register (Ra) from a 16-bit instruction
    public static int getRa(int instruction) {
        return (instruction >> 5) & 0b111; // Mask to get 3 bits
    }

    // Extracts the third register (Rb) from a 16-bit instruction
    public static int getRb(int instruction) {
        return (instruction >> 2) & 0b111; // Mask to get 3 bits
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
