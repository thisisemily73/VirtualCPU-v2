package tests;

import hardware.Bus;

public class BusTest {

    public static void main(String[] args) {

        Bus bus = new Bus();


        bus.send(42);

        System.out.println(
            "Bus value = " + bus.read()
        );


        bus.send(70000);

        System.out.println(
            "16-bit Bus value = " + bus.read()
        );
    }
}