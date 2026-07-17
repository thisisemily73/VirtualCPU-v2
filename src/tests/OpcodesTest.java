package tests;

import control.Opcodes;

public class OpcodesTest {

    public static void main(String[] args) {

        System.out.println(
            "LOADI = "
            + Integer.toBinaryString(
                Opcodes.getOpcode("LOADI")
            )
        );


        System.out.println(
            "ADD = "
            + Integer.toBinaryString(
                Opcodes.getOpcode("ADD")
            )
        );
    }
}