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
    private int register;
    private int value;

    public Instruction(String opcode, int register, int value) {
        this.opcode = opcode;
        this.register = register;
        this.value = value;
    }

    public String getOpcode() {
        return opcode;
    }

    public int getRegister() {
        return register;
    }

    public int getValue() {
        return value;
    }
}
