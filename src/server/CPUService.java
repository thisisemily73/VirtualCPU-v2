package server;

import hardware.CPU;
import hardware.RAM;

public class CPUService {
    private RAM ram;
    private CPU cpu;
    private boolean halted = false;
    private int lastLine = 0;

    public CPUService() {
        reset();
    }

    public synchronized void reset() {
        ram = new RAM(4096);
        cpu = new CPU(ram);
        halted = false;
        lastLine = 0;
    }

    public synchronized void loadProgram(int[] program) {
        reset();
        for (int i = 0; i < program.length; i++) {
            ram.write(i, program[i]);
        }
        halted = false;
    }

    public synchronized CPUStateDTO step() {
        if (halted) {
            return getState();
        }

        int pcBefore = cpu.getProgramCounter().get();
        boolean running = cpu.step();
        int pcAfter = cpu.getProgramCounter().get();

        halted = !running;
        lastLine = pcBefore;

        return buildState(pcAfter);
    }

    public synchronized CPUStateDTO getState() {
        int pc = cpu.getProgramCounter().get();
        return buildState(pc);
    }

    private CPUStateDTO buildState(int pc) {
        CPUStateDTO s = new CPUStateDTO();
        s.pc = pc;

        for (int i = 0; i < 8; i++) {
            s.registers[i] = cpu.getRegisters().read(i);
        }

        s.alu = 0;
        s.zeroFlag = false;
        s.carryFlag = false;
        s.negativeFlag = false;

        s.currentInstruction = cpu.getInstructionRegister().getInstruction();
        s.currentLine = lastLine;
        s.halted = halted;

        return s;
    }
}