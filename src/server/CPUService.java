package server;

import java.util.List;

import assembler.Assembler;
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

        System.out.println("PROGRAM LENGTH: " + program.length);

        for (int i = 0; i < program.length; i++) {
            System.out.println(i + ": " + program[i]);
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

        s.alu = new CPUStateDTO.ALUStateDTO();

        s.pc = pc;

        int[] regs = cpu.getState().registers;
        System.arraycopy(regs, 0, s.registers, 0, regs.length);

        CPU.ALUSnapshot aluSnap = cpu.getLastALU();

        if (aluSnap != null) {
            s.alu.inputA = aluSnap.inputA;
            s.alu.inputB = aluSnap.inputB;
            s.alu.output = aluSnap.output;
            s.alu.operation = aluSnap.operation;
        } else {
            s.alu.inputA = 0;
            s.alu.inputB = 0;
            s.alu.output = 0;
            s.alu.operation = "IDLE";
        }

        s.zeroFlag = false;
        s.carryFlag = false;
        s.negativeFlag = false;

        s.currentInstruction = cpu.getInstructionRegister().getInstruction();
        s.currentLine = lastLine;
        s.halted = halted;

        return s;
    }

    public synchronized CPUStateDTO loadAssembly(String code) {
        // remove comment lines
        StringBuilder cleaned = new StringBuilder();

        for (String line : code.split("\n")) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }
            if (line.startsWith(";")) {
                continue;
            }

            cleaned.append(line).append("\n");
        }

        code = cleaned.toString();

        System.out.println("RECEIVED ASSEMBLY:");
        System.out.println(code);

        Assembler assembler = new Assembler();

        List<Integer> machineCode;

        try {
            machineCode = assembler.assemble(code);
        } catch (Exception e) {
            System.out.println("ASSEMBLER CRASHED:");
            e.printStackTrace();
            machineCode = new java.util.ArrayList<>();
        }

        System.out.println("MACHINE CODE:");
        System.out.println(machineCode);

        reset();

        for (int i = 0; i < machineCode.size(); i++) {
            System.out.println("RAM[" + i + "] = " + machineCode.get(i));
            ram.write(i, machineCode.get(i));
        }

        return getState();
    }
}
