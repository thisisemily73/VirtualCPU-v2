package tests;

import assembler.*;
import java.util.List;
import util.BinaryFormatter;

public class AssemblerTest {

    public static void main(String[] args) {

        String program =
                "LOADI R1, 10\n"
                + "ADD R1, R2\n"
                + "HALT";


        Assembler assembler = new Assembler();


        List<Integer> machineCode =
                assembler.assemble(program);


        for (Integer code : machineCode) {

            System.out.println(
                    BinaryFormatter.toBinaryString(code)
            );

        }
    }
}