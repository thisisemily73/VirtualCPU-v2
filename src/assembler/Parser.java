package assembler;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser
 *
 * Role:
 * Converts tokenized assembly instructions into structured
 * Instruction objects that the assembler can understand.
 *
 * The Tokenizer only separates text:
 *
 *      LOADI R1, 10
 *
 * becomes:
 *
 *      ["LOADI", "R1", "10"]
 *
 * The Parser gives those tokens meaning:
 *
 *      opcode = LOADI
 *      register = 1
 *      value = 10
 *
 * This keeps the InstructionEncoder from having to
 * interpret raw text.
 */

public class Parser {


    /**
     * Converts a list of token arrays into Instruction objects.
     *
     * Example:
     *
     * Input:
     * ["LOADI", "R1", "10"]
     *
     * Output:
     * Instruction(
     *      opcode = LOADI,
     *      register = 1,
     *      value = 10
     * )
     */
    public List<Instruction> parse(List<String[]> tokens) {


        // Stores all parsed instructions from the program
        List<Instruction> instructions = new ArrayList<>();


        // Process each assembly instruction individually
        for (String[] token : tokens) {


            // The first token is always the instruction name
            // Example: "LOADI", "ADD", "HALT"
            String opcode = token[0];


            /*
             * Different instructions have different formats.
             *
             * Example:
             *
             * LOADI R1, 10
             *      opcode register immediate
             *
             * ADD R1, R2
             *      opcode register register
             *
             * HALT
             *      opcode only
             *
             * The parser handles these differences here.
             */

            switch(opcode) {


                case "LOADI":

                    /*
                     * Convert register name:
                     *
                     * "R1" → 1
                     *
                     * Convert immediate value:
                     *
                     * "10" → 10
                     */

                    int register = parseRegister(token[1]);
                    int value = Integer.parseInt(token[2]);


                    // Create a structured instruction object
                    instructions.add(
                        new Instruction(opcode, register, value)
                    );

                    break;



                case "ADD":

                    /*
                     * ADD uses two registers:
                     *
                     * ADD R1, R2
                     *
                     * destination = R1
                     * source = R2
                     *
                     * The Instruction class stores
                     * these values in register and value.
                     */

                    int reg1 = parseRegister(token[1]);

                    int reg2 = parseRegister(token[2]);


                    instructions.add(
                        new Instruction(opcode, reg1, reg2)
                    );

                    break;



                case "HALT":

                    /*
                     * HALT has no operands.
                     *
                     * The register and value fields are unused,
                     * so they are set to 0.
                     */

                    instructions.add(
                        new Instruction(opcode, 0, 0)
                    );

                    break;



                default:

                    /*
                     * Prevent invalid instructions from silently
                     * entering the assembler.
                     *
                     * Example:
                     *
                     * LOADD R1, 10
                     *
                     * would be rejected here.
                     */

                    throw new IllegalArgumentException(
                        "Unknown instruction: " + opcode
                    );
            }
        }


        // Return all parsed instructions
        return instructions;
    }



    /**
     * Converts a register string into its numeric ID.
     *
     * Example:
     *
     * "R3" → 3
     *
     * This matches the CPU's register architecture:
     * R0-R7 are represented using 3 bits.
     */
    private int parseRegister(String register) {


        // Remove the "R" prefix
        // Then convert the remaining number into an integer
        return Integer.parseInt(
            register.substring(1)
        );
    }
}