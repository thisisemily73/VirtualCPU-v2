package server;

public class CPUStateDTO {
    public int pc;
    public int[] registers = new int[8];
    public int alu;
    public boolean zeroFlag;
    public boolean carryFlag;
    public boolean negativeFlag;
    public int currentInstruction;
    public int currentLine;
    public boolean halted;
}