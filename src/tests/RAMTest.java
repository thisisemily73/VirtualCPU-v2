package tests;

import hardware.RAM;

public class RAMTest {

    public static void main(String[] args) {

        RAM ram = new RAM(4096);

        ram.write(100, 42);

        System.out.println(
            "Memory[100] = " + ram.read(100)
        );


        ram.write(4095, 65535);

        System.out.println(
            "Memory[4095] = " + ram.read(4095)
        );
    }
}