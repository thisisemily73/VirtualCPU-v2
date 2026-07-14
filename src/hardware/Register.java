package hardware;

/**
 * Register
 *
 * Role in CPU:
 * Represents a single 16-bit storage unit inside the CPU.
 * Used to temporarily hold data during execution.
 *
 * Interacts with:
 * - RegisterFile (managed as part of a group)
 * - ALU (provides operands / receives results)
 */
public class Register {
    
    // Stores a 16-bit value
    private int value;

    public Register() {
        value = 0;
    }

    /**
    * Stores a value in the register
    * Only the lower 16 bits are kept are stored to simulate hardware limits.
    */
    public void store(int value) {
        this.value = value & 0xFFFF; // Ensure only the lower 16 bits are stored
    }

    /**
    * Returns the current value stored in the register
    */
    public int read() {
        return value;
    }
}
