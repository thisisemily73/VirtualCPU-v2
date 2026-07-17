package assembler;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser
 *
 * Converts tokenized assembly instructions into
 * Instruction objects.
 *
 * The Tokenizer separates text.
 * The Parser gives that text meaning.
 *
 * Example:
 *
 * LOADI R1, 10
 *
 * becomes:
 *
 * opcode = LOADI
 * registerA = 1
 * immediate = 10
 */

public class Parser {


    /**
     * Converts token arrays into Instruction objects.
     */
    public List<Instruction> parse(List<String[]> tokens) {


        List<Instruction> instructions = new ArrayList<>();


        for (String[] token : tokens) {


            String opcode = token[0];


            switch(opcode) {


                case "LOADI":

                    /*
                     * Format:
                     *
                     * LOADI register, immediate
                     *
                     * Example:
                     *
                     * LOADI R1, 10
                     */

                    int loadRegister = parseRegister(token[1]);

                    int immediate = Integer.parseInt(token[2]);


                    instructions.add(
                        new Instruction(
                            opcode,
                            loadRegister,
                            0,
                            immediate,
                            0
                        )
                    );

                    break;



                case "ADD":

                    /*
                     * Format:
                     *
                     * ADD destination, source
                     *
                     * Example:
                     *
                     * ADD R1, R2
                     */

                    int destination = parseRegister(token[1]);

                    int source = parseRegister(token[2]);


                    instructions.add(
                        new Instruction(
                            opcode,
                            destination,
                            source,
                            0,
                            0
                        )
                    );

                    break;



                case "HALT":

                    /*
                     * HALT has no operands.
                     */

                    instructions.add(
                        new Instruction(
                            opcode,
                            0,
                            0,
                            0,
                            0
                        )
                    );

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
}