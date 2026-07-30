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
                ir,
                this
        );
    }

    public CPUState getState() {
        return new CPUState(
                pc.get(),
                ir.getInstruction(),
                registers.getAll(),
                ram.getAll(),
                flags.isZeroFlag()
        );
    }

    public boolean step() {
        int address = pc.get();
        int instruction = ram.read(address);

        bus.send(instruction);
        ir.loadFromBus(bus);

        pc.increment();

        return controlUnit.execute(); // <-- THIS MATTERS
    }

    public static class ALUSnapshot {

        public int inputA;
        public int inputB;
        public int output;
        public String operation;
    }

    private ALUSnapshot lastALU = new ALUSnapshot();

    public ALUSnapshot getLastALU() {
        return lastALU;
    }

    // ADD OTHER PARTS
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

    public ALU getALU() {
        return alu;
    }

    public RAM getRAM() {
        return ram;
    }

    public void updateALU(int a, int b, int result, String op) {
        lastALU.inputA = a;
        lastALU.inputB = b;
        lastALU.output = result;
        lastALU.operation = op;
    }

}
