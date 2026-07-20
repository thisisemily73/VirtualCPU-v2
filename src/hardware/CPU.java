package hardware;

import control.*;

public class CPU {

    private RAM ram;
    private Bus bus;

    private ProgramCounter pc;
    private InstructionRegister ir;
    private RegisterFile registers;
    private ALU alu;
    private FlagsRegister flags;

    private ControlUnit controlUnit;

    public CPU(RAM ram) {
        this.ram = ram;

        // Core hardware
        this.bus = new Bus();
        this.pc = new ProgramCounter();
        this.ir = new InstructionRegister();

        // Execution hardware
        this.registers = new RegisterFile();
        this.alu = new ALU();
        this.flags = new FlagsRegister();

        this.controlUnit = new ControlUnit(
            registers,
            ram,
            alu,
            pc,
            flags,
            ir
        );
    }

    public boolean step() {

        // FETCH
        int address = pc.get();
        int instruction = ram.read(address);

        bus.send(instruction);
        ir.loadFromBus(bus);

        pc.increment();

        // EXECUTE
        return controlUnit.execute();
    }

    public RegisterFile getRegisters() {
        return registers;
    }
}