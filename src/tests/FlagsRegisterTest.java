package tests;

import hardware.FlagsRegister;

public class FlagsRegisterTest {

    public static void main(String[] args) {

        FlagsRegister flags = new FlagsRegister();

        // Initial state
        System.out.println("Initial Z: " + flags.isZeroFlag());
        System.out.println("Initial C: " + flags.isCarryFlag());
        System.out.println("Initial N: " + flags.isNegativeFlag());

        // Set values
        flags.setZeroFlag(true);
        flags.setCarryFlag(true);
        flags.setNegativeFlag(true);

        System.out.println("After setting:");
        System.out.println("Z: " + flags.isZeroFlag());
        System.out.println("C: " + flags.isCarryFlag());
        System.out.println("N: " + flags.isNegativeFlag());
    }
}