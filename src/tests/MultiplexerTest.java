package tests;

import hardware.Multiplexer;

public class MultiplexerTest {

    public static void main(String[] args) {

        Multiplexer mux = new Multiplexer();

        int inputA = 100;
        int inputB = 200;

        // Test selecting input A
        int resultA = mux.select(inputA, inputB, 0);
        System.out.println("Selector 0: " + resultA);

        // Test selecting input B
        int resultB = mux.select(inputA, inputB, 1);
        System.out.println("Selector 1: " + resultB);

        // Test 16-bit value handling
        int largeValue = mux.select(0xFFFF, 0, 0);
        System.out.println("16-bit value: " + largeValue);

        // Test invalid selector
        try {
            mux.select(inputA, inputB, 2);
            System.out.println("ERROR: Invalid selector accepted");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid selector correctly rejected");
        }
    }
}
