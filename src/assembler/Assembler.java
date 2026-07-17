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
 * The output of this stage is a 16-bit machine code
 * compatible with the CPU's memory system.
 */

public class Assembler {
    
    private Tokenizer tokenizer;
    private Parser parser;
    private InstructionEncoder encoder;

    public Assembler() {
        tokenizer = new Tokenizer();
        parser = new Parser();
        encoder = new InstructionEncoder();
    }

    // Converts assembly text into 16-bit machine code.

    public List<Integer> assemble(String program) {
        // Break source code into tokens
        List<String[]> tokens = tokenizer.tokenize(program);
        
        // Convert tokens into instructions
        List<Instruction> instructions = parser.parse(tokens);

        // Encode instructions into binary machine code
        List<Integer> machineCode = new ArrayList<>();
        for (Instruction instruction : instructions) {
            machineCode.add(encoder.encode(instruction));
        }

        return machineCode;
    }
}