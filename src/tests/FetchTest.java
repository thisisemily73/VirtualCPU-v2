package tests;

import hardware.*;

public class FetchTest {

    public static void main(String[] args) {

        RAM ram = new RAM(4096);
        Bus bus = new Bus();

        ProgramCounter pc = new ProgramCounter();
        InstructionRegister ir = new InstructionRegister();


        // Put a fake instruction into memory
        ram.write(0, 12345);


        // ===== FETCH STAGE =====

        int address = pc.get();

        int instruction = ram.read(address);

        bus.send(instruction);

        ir.loadFromBus(bus);

        pc.increment();


        // =======================

        System.out.println("IR = " + ir.getInstruction());
        System.out.println("PC = " + pc.get());
    }
}