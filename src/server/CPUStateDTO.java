package server;

public class CPUStateDTO {

    public int pc;

    public int[] registers = new int[8];

    public ALUStateDTO alu = new ALUStateDTO();

    public boolean zeroFlag;
    public boolean carryFlag;
    public boolean negativeFlag;

    public int currentInstruction;

    public int currentLine;

    public boolean halted;

    public static class ALUStateDTO {
        public int inputA;
        public int inputB;
        public int output;
        public String operation;
    }
}