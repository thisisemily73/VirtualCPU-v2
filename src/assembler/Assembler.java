package assembler;

import java.util.*;

/**
 * Assembler
 * 
 * Coordinates the assembly.
 * 
 * The assembly text goes through the tokenizer,
 * followed by the parser, before becoming legible
 * instruction objects.
 * 
 * The output of this stage will later be converted
 * into binary machine code.
 */

public class Assembler {
    
    private Tokenizer tokenizer;
    private Parser parser;

    public Assembler() {
        tokenizer = new Tokenizer();
        parser = new Parser();
    }

    // Converts the assembly text into a list of instructions.

    public List<Instruction> assemble(String program) {
        // Break source code into tokens
        List<String[]> tokens = tokenizer.tokenize(program);
        
        // Convert tokens into instructions
        List<Instruction> instructions = parser.parse(tokens);

        return instructions;
    }
}
