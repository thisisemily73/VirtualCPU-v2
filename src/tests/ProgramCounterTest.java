package tests;

import hardware.ProgramCounter;

public class ProgramCounterTest {

    public static void main(String[] args) {

        ProgramCounter pc = new ProgramCounter();

        System.out.println("PC = " + pc.get());

        pc.increment();
        pc.increment();

        System.out.println("PC after increment = " + pc.get());

        pc.set(1000);

        System.out.println("PC after set = " + pc.get());
    }
}