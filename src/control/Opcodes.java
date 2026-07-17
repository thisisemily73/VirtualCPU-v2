package control;

/**
 * Opcodes
 * Defines all instruction codes used by the CPU.
 * Makes code readable and avoids magic numbers.
 * 
 * Each instruction is 16 bits total
 * The top 5 bits represent the opcode
 *
 * This allow for: 2^5 = 32 possible instructions
 *
 * The opcodes are intentionally organized by category:
 *
 * 000xx → Data movement / immediate
 * 001xx → ALU operations
 * 010xx → Control flow (jumps/branches)
 * 11111 → Special instruction (HALT)
 *
 * This grouping makes the CPU:
 * - Easier to extend later
 * - Easier to debug (patterns in binary)
 * - More readable when inspecting memory
 *
 * 00000 is left reserved for NOP and future
 * low-priority instructions.
 */

public class Opcodes {
    // No operation
    public static final int NOP = 0b00000;

    // Data movement
    public static final int LOADI = 0b00001;
    public static final int LOAD  = 0b00010;
    public static final int STORE = 0b00011;
    
    // Arithmetic and logic
    public static final int ADD   = 0b00100;
    public static final int SUB   = 0b00101;

    // Control flow
    public static final int JMP   = 0b00110;
    public static final int JZ    = 0b00111;
    public static final int JNZ   = 0b01000;

    // Stack
    public static final int PUSH  = 0b01001;
    public static final int POP   = 0b01010;

    // Logical
    public static final int AND   = 0b01011;
    public static final int OR    = 0b01100;
    public static final int XOR   = 0b01101;
    public static final int NOT   = 0b01110;

    // System
    public static final int HALT  = 0b11111;

    /**
     * Converts an opcode integer to its string representation.
     */
    public static String getInstruction(int opcode) {
        switch (opcode) {
            case NOP: return "NOP";
            case LOADI: return "LOADI";
            case LOAD: return "LOAD";
            case STORE: return "STORE";
            case ADD: return "ADD";
            case SUB: return "SUB";
            case JMP: return "JMP";
            case JZ: return "JZ";
            case JNZ: return "JNZ";
            case PUSH: return "PUSH";
            case POP: return "POP";
            case AND: return "AND";
            case OR: return "OR";
            case XOR: return "XOR";
            case NOT: return "NOT";
            case HALT: return "HALT";
            default: throw new IllegalArgumentException("Unknown instruction: " + opcode);
        }
    }

    /**
     * Converts an instruction string to its opcode integer representation. 
    */
    public static int getOpcode(String instruction) {
        switch (instruction) {
            case "NOP": return NOP;
            case "LOADI": return LOADI;
            case "LOAD": return LOAD;
            case "STORE": return STORE;
            case "ADD": return ADD;
            case "SUB": return SUB;
            case "JMP": return JMP;
            case "JZ": return JZ;
            case "JNZ": return JNZ;
            case "PUSH": return PUSH;
            case "POP": return POP;
            case "AND": return AND;
            case "OR": return OR;
            case "XOR": return XOR;
            case "NOT": return NOT;
            case "HALT": return HALT;
            default: throw new IllegalArgumentException("Unknown instruction: " + instruction);
        }
    }
}
