package tests;

import assembler.*;
import java.util.List;


public class AssemblerTest {


    public static void main(String[] args) {


        String program =
                "LOADI R1, 10\n" +
                "ADD R1, R2\n" +
                "HALT";


        Assembler assembler = new Assembler();


        List<Instruction> instructions =
                assembler.assemble(program);



        for(Instruction instruction : instructions) {


            System.out.println(
                instruction.getOpcode()
                + " "
                + instruction.getRegisterA()
                + " "
                + instruction.getImmediate()
                + " "
                + instruction.getRegisterB()
                + " "
                + instruction.getAddress()
            );
        }
    }
}