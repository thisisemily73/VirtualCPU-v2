package hardware;

/**
 * ProgramCounter (PC)
 *
 * Role in CPU:
 * Holds the address of the next instruction to execute.
 * Automatically increments after each instruction fetch.
 *
 * Interacts with:
 * - RAM (used to fetch instructions)
 * - ControlUnit (may be updated for jumps/branches)
 */
public class ProgramCounter {
    private int value;

    public ProgramCounter() {
        value = 0;
    }

     /**
     * Sets the program counter to a specific address.
     * Used for jumps and branching.
     */
    public void set(int value) {
        this.value = value & 0xFFFF; // Ensure only the lower 16 bits are stored
    }

    public int get() {
        return value;
    }

    public void increment() {
        value = (value + 1) & 0xFFFF; // Increment and wrap around at 16 bits
    }
}
