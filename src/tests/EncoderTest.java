package tests;

import assembler.*;
import util.BinaryFormatter;

public class EncoderTest {

    public static void main(String[] args) {


        InstructionEncoder encoder =
                new InstructionEncoder();


        Instruction load =
                new Instruction(
                    "LOADI",
                    1,
                    0,
                    10,
                    0
                );


        Instruction add =
                new Instruction(
                    "ADD",
                    1,
                    2,
                    0,
                    0
                );


        Instruction halt =
                new Instruction(
                    "HALT",
                    0,
                    0,
                    0,
                    0
                );



        System.out.println(
            BinaryFormatter.toBinaryString(
                encoder.encode(load)
            )
        );


        System.out.println(
            BinaryFormatter.toBinaryString(
                encoder.encode(add)
            )
        );


        System.out.println(
            BinaryFormatter.toBinaryString(
                encoder.encode(halt)
            )
        );
    }
}