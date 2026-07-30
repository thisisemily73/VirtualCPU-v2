package assembler;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser
 *
 * Converts tokenized assembly instructions into Instruction objects.
 *
 * The Tokenizer separates text. The Parser gives that text meaning.
 *
 * Example:
 *
 * LOADI R1, 10
 *
 * becomes:
 *
 * opcode = LOADI registerA = 1 immediate = 10
 */
public class Parser {

    /**
     * Converts token arrays into Instruction objects.
     */
    public List<Instruction> parse(List<String[]> tokens) {

        List<Instruction> instructions = new ArrayList<>();

        for (String[] token : tokens) {

            String opcode = token[0];

            switch (opcode) {

                case "NOP":
                    instructions.add(new Instruction(opcode, 0, 0, 0, 0));
                    break;

                // DATA
                case "LOADI": {
                    int reg = parseRegister(token[1]);
                    int imm = Integer.parseInt(token[2]);
                    instructions.add(new Instruction(opcode, reg, 0, imm, 0));
                    break;
                }

                case "LOAD": {
                    int reg = parseRegister(token[1]);
                    int addr = parseNumber(token[2]);
                    instructions.add(new Instruction(opcode, reg, 0, addr, 0));
                    break;
                }

                case "STORE": {
                    int reg = parseRegister(token[1]);
                    int addr = parseNumber(token[2]);
                    instructions.add(new Instruction(opcode, reg, 0, addr, 0));
                    break;
                }

                // ALU
                case "ADD": {
                    int dest = parseRegister(token[1]);
                    int src = parseRegister(token[2]);
                    instructions.add(new Instruction(opcode, dest, src, 0, 0));
                    break;
                }

                case "SUB": {
                    int dest = parseRegister(token[1]);
                    int src = parseRegister(token[2]);
                    instructions.add(new Instruction(opcode, dest, src, 0, 0));
                    break;
                }

                // CONTROL FLOW
                case "JMP": {
                    int addr = parseNumber(token[1]);
                    instructions.add(new Instruction(opcode, 0, 0, addr, 0));
                    break;
                }

                case "JZ": {
                    int reg = parseRegister(token[1]);
                    int addr = parseNumber(token[2]);
                    instructions.add(new Instruction(opcode, reg, 0, addr, 0));
                    break;
                }

                case "JNZ": {
                    int reg = parseRegister(token[1]);
                    int addr = parseNumber(token[2]);
                    instructions.add(new Instruction(opcode, reg, 0, addr, 0));
                    break;
                }

                // STACK
                case "PUSH": {
                    int reg = parseRegister(token[1]);
                    instructions.add(new Instruction(opcode, reg, 0, 0, 0));
                    break;
                }

                case "POP": {
                    int reg = parseRegister(token[1]);
                    instructions.add(new Instruction(opcode, reg, 0, 0, 0));
                    break;
                }

                // LOGIC
                case "AND": {
                    int dest = parseRegister(token[1]);
                    int src = parseRegister(token[2]);
                    instructions.add(new Instruction(opcode, dest, src, 0, 0));
                    break;
                }

                case "OR": {
                    int dest = parseRegister(token[1]);
                    int src = parseRegister(token[2]);
                    instructions.add(new Instruction(opcode, dest, src, 0, 0));
                    break;
                }

                case "XOR": {
                    int dest = parseRegister(token[1]);
                    int src = parseRegister(token[2]);
                    instructions.add(new Instruction(opcode, dest, src, 0, 0));
                    break;
                }

                case "NOT": {
                    int reg = parseRegister(token[1]);
                    instructions.add(new Instruction(opcode, reg, 0, 0, 0));
                    break;
                }

                // SYSTEM
                case "HALT":
                    instructions.add(new Instruction(opcode, 0, 0, 0, 0));
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unknown instruction: " + opcode
                    );
            }
        }

        return instructions;
    }

    /**
     * Converts register text into a register ID.
     *
     * Example:
     *
     * R3 → 3
     */
    private int parseRegister(String register) {

        // Remove the "R" prefix
        return Integer.parseInt(
                register.substring(1)
        );
    }

    /**
     * Converts a numeric string into an integer value.
     *
     * Supports both decimal and hexadecimal formats.
     *
     * Examples:
     *
     * 10 → 10 0x10 → 16 0XFF → 255
     */
    private int parseNumber(String value) {

        // supports hex like 0x100
        if (value.startsWith("0x") || value.startsWith("0X")) {
            return Integer.parseInt(value.substring(2), 16);
        }

        return Integer.parseInt(value);
    }
}
