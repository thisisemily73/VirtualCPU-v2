package hardware;

/**
 * ProgramCounter (PC)
 *
 * Role in CPU:
 * Stores the memory address of the NEXT instruction to execute.
 *
 * Part of the Fetch-Decode-Execute cycle:
 * 1. PC → provides address to RAM
 * 2. Instruction is fetched from that address
 * 3. PC is incremented to point to the next instruction
 *
 * Special behavior:
 * - Can be manually updated (set) for control flow instructions
 *   like JMP, JZ, and JNZ.
 *
 * Interacts with:
 * - RAM (supplies instruction address)
 * - InstructionRegister (receives fetched instruction)
 * - ControlUnit (may override PC for jumps)
 */
public class ProgramCounter {

    // Holds the current instruction address (16-bit)
    private int value;

    /**
     * Initializes the PC to 0 (program start).
     */
    public ProgramCounter() {
        value = 0;
    }

    /**
     * Sets the PC to a specific address.
     *
     * Used by:
     * - JMP (unconditional jump)
     * - JZ / JNZ (conditional jumps)
     *
     * Only lower 16 bits are kept (simulates hardware limit).
     */
    public void set(int value) {
        this.value = value & 0xFFFF;
    }

    /**
     * Returns the current instruction address.
     */
    public int get() {
        return value;
    }

    /**
     * Increments the PC to point to the next instruction.
     *
     * This happens automatically after each instruction fetch.
     * Wraps around at 16 bits (0 → 65535).
     */
    public void increment() {
        value = (value + 1) & 0xFFFF;
    }
}