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
 * 00000 is left unused for future use (e.g., NOP).
 */

public class Opcodes {
    public static final int LOADI = 0b00001;
    public static final int ADD   = 0b00100;
    public static final int SUB   = 0b00101;
    public static final int JMP   = 0b00110;
    public static final int JZ    = 0b00111;
    public static final int JNZ   = 0b01000;
    public static final int HALT  = 0b11111;
}
