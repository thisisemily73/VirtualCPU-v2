package hardware;

/**
 * ALU (Arithmetic Logic Unit)
 *
 * Role in CPU:
 * Performs arithmetic and logical operations on data.
 * This is the core computation unit of the processor.
 *
 * Interacts with:
 * - RegisterFile (receives operands / returns results)
 * - FlagsRegister (sets condition flags based on results)
 */
public class ALU {
    /**
     * Adds two 16-bit values.
     */
    public int add(int a, int b) {
        return (a + b) & 0xFFFF; // Ensure only the lower 16 bits are returned
    }

    /**
     * Subtracts two 16-bit values.
     */
    public int sub(int a, int b) {
        return (a - b) & 0xFFFF; // Ensure only the lower 16 bits are returned
    }

    /**
     * Performs a bitwise AND operation on two 16-bit values.
     */
    public int and(int a, int b) {
        return a & b;
    }

    /**
     * Performs a bitwise OR operation on two 16-bit values.
     */
    public int or(int a, int b) {
        return a | b;
    }

    /**
     * Performs a bitwise NOT operation on a 16-bit value.
     */
    public int not(int a) {
        return (~a) & 0xFFFF; // Ensure only the lower 16 bits are returned
    }

    /**
     * Checks if a 16-bit value is zero.
     */
    public boolean isZero(int a) {
        return a == 0;
    }
}