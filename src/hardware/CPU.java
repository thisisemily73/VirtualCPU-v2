package hardware;

import control.ControlUnit;

public class CPU {

    private RAM ram;
    private Bus bus;
    private ProgramCounter pc;
    private InstructionRegister ir;
    private FlagsRegister flags;
    private RegisterFile registers;
    private ALU alu;
    private ControlUnit controlUnit;

    public CPU(RAM ram) {
        this.ram = ram;

        this.bus = new Bus();
        this.pc = new ProgramCounter();
        this.ir = new InstructionRegister();

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

    public ProgramCounter getProgramCounter() {
        return pc;
    }

    public InstructionRegister getInstructionRegister() {
        return ir;
    }

    public FlagsRegister getFlags() {
        return flags;
    }

    public RegisterFile getRegisters() {
        return registers;
    }

    public boolean step() {
        int address = pc.get();
        int instruction = ram.read(address);

        bus.send(instruction);
        ir.loadFromBus(bus);

        pc.increment();

        return controlUnit.execute();
    }
}