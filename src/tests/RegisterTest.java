package tests;

import hardware.Register;

public class RegisterTest {

    public static void main(String[] args) {

        Register r1 = new Register();

        r1.store(42);

        System.out.println("Register value: " + r1.read());

        r1.store(70000);

        System.out.println("16-bit value: " + r1.read());
    }
}