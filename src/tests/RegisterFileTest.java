package tests;

import hardware.RegisterFile;

public class RegisterFileTest {

    public static void main(String[] args) {

        RegisterFile registers = new RegisterFile();

        registers.store(1, 100);

        System.out.println(
            "R1 = " + registers.read(1)
        );

        registers.store(7, 255);

        System.out.println(
            "R7 = " + registers.read(7)
        );
    }
}