package hardware;

/**
 * InstructionRegister (IR)
 *
 * Role in CPU: Holds the CURRENT instruction being executed.
 *
 * This is part of the fetch-decode-execute cycle:
 *
 * 1. Fetch → instruction is read from RAM 2. Load → instruction is stored in
 * the IR 3. Decode → Control Unit reads fields from IR 4. Execute → instruction
 * is carried out
 *
 * Why this exists: The CPU should not directly operate on RAM values. Instead,
 * it loads the instruction into a stable location (IR) so the Control Unit can
 * safely decode and execute it.
 *
 * Interacts with: - RAM (provides instruction) - ControlUnit (reads and decodes
 * instruction)
 */
public class InstructionRegister {

    // Stores the current 16-bit instruction
    private int instruction;

    public InstructionRegister() {
        this.instruction = 0;
    }

    /**
     * Loads a new instruction into the register.
     *
     * Only the lower 16 bits are kept to simulate a 16-bit CPU.
     */
    public void load(int instruction) {
        this.instruction = instruction & 0xFFFF;
    }

    /**
     * Loads an instruction from the system bus.
     *
     * This simulates real CPU behavior where data is transferred over a shared
     * bus rather than directly between components.
     */
    public void loadFromBus(Bus bus) {
        this.instruction = bus.read() & 0xFFFF;
    }

    /**
     * Returns the full 16-bit instruction.
     */
    public int getInstruction() {
        return instruction;
    }

    /**
     * Extracts the opcode (top 5 bits).
     *
     * Example: [ opcode | rest of instruction ]
     *
     * We shift right to remove lower bits.
     */
    public int getOpcode() {
        return (instruction >> 11) & 0b11111;
    }

    /**
     * Extracts the destination register index.
     *
     * Example format (R-type): [ opcode (5) | dest (3) | src (3) | unused (5) ]
     */
    public int getDest() {
        return (instruction >> 8) & 0b111;
    }

    /**
     * Extracts the source register index.
     */
    public int getSrc() {
        return (instruction >> 5) & 0b111;
    }

    /**
     * Extracts an 8-bit immediate value.
     *
     * Used for instructions like LOADI.
     */
    public int getImmediate() {
        return instruction & 0xFF;
    }

    /**
     * Extracts an 11-bit address.
     *
     * Used for jump instructions.
     */
    public int getAddress() {
        return instruction & 0x7FF;
    }
}
