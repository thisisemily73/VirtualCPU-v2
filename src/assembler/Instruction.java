package assembler;

/**
 * Instruction
 *
 * Represents a parsed assembly instruction.
 *
 * Example:
 *
 * LOADI R1, 10
 *
 * becomes:
 *
 * opcode = LOADI
 * register = 1
 * value = 10
 */

public class Instruction {
    private String opcode;

    private int registerA;
    private int registerB;

    private int immediate;
    private int address;

    // Creates an instruction object
    // The paser fills in only thee fields needed for that instruction type.
    public Instruction(String opcode, int registerA, 
        int registerB, int immediate, int address) {
        this.opcode = opcode;
        this.registerA = registerA;
        this.registerB = registerB;
        this.immediate = immediate;
        this.address = address;
    }

    public String getOpcode() {
        return opcode;
    }

    public int getRegisterA() {
        return registerA;
    }

    public int getRegisterB() {
        return registerB;
    }

    public int getImmediate() {
        return immediate;
    }

    public int getAddress() {
        return address;
    }
}
