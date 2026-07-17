package assembler;

import java.util.*;

/**
 * Tokenizer
 *
 * Breaks assembly code into individual tokens.
 *
 * Example:
 *
 * LOADI R1, 10
 *
 * becomes:
 *
 * ["LOADI", "R1", "10"]
 */

public class Tokenizer {
    
    public List<String[]> tokenize(String program) {
        List<String[]> instructions = new ArrayList<>();
        String[] lines = program.split("\n");
        
        for (String line : lines) {
            line = line.trim();

            // Skip empty lines and comments
            if (line.isEmpty() || line.startsWith("//")) {
                continue;
            }

            line = line.replace (",", ""); // Replace commas with blanks for easier splitting
            
            String[] tokens = line.split("\\s+");
            instructions.add(tokens);
        }
        
        return instructions;
    }
}