package tests;

import assembler.*;

import java.util.List;


public class ParserTest {


    public static void main(String[] args) {


        String program =
                "LOADI R1, 10\n" +
                "ADD R1, R2\n" +
                "HALT";


        Tokenizer tokenizer = new Tokenizer();

        List<String[]> tokens =
                tokenizer.tokenize(program);



        Parser parser = new Parser();

        List<Instruction> instructions =
                parser.parse(tokens);



        for(Instruction instruction : instructions) {

            System.out.println(
                instruction.getOpcode()
                + " "
                + instruction.getRegister()
                + " "
                + instruction.getValue()
            );
        }
    }
}