package hardware;

/**
 * RAM (Random Access Memory)
 *
 * Role in CPU:
 * Stores both instructions and data.
 * Acts as the main memory unit accessed during program execution.
 *
 * Interacts with:
 * - ProgramCounter (provides memory address)
 * - InstructionRegister (loads current instruction)
 * - ControlUnit (reads/writes data)
 */
public class RAM {

    private int[] memory;

    public RAM(int size) {
        memory = new int[size]; // 4096 memory locations
    }

    /**
     * Writes a 16-bit value to the specified memory address.
     */
    public void write(int address, int value) {
        if (address < 0 || address >= memory.length) {
            throw new IllegalArgumentException("Address out of bounds");
        }
        memory[address] = value & 0xFFFF;
    }

    
    /**
     * Reads a 16-bit value from the specified memory address.
     */
    public int read(int address) {
        if (address < 0 || address >= memory.length) {
            throw new IllegalArgumentException("Address out of bounds");
        }
        return memory[address] & 0xFFFF;
    }
}