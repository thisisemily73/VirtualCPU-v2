package tests;

import assembler.Tokenizer;
import java.util.List;

public class TokenizerTest {

    public static void main(String[] args) {

        String program =
                "LOADI R1, 10\n" +
                "ADD R1, R2\n" +
                "HALT";


        Tokenizer tokenizer = new Tokenizer();

        List<String[]> result = tokenizer.tokenize(program);


        for (String[] instruction : result) {

            for (String token : instruction) {
                System.out.print(token + " ");
            }

            System.out.println();
        }
    }
}