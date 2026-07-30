package hardware;

public class CPUState {
    public int pc;
    public int instruction;
    public int[] registers;
    public int[] memory;
    public boolean zeroFlag;

    public CPUState(int pc, int instruction, int[] registers, int[] memory, boolean zeroFlag) {
        this.pc = pc;
        this.instruction = instruction;
        this.registers = registers;
        this.memory = memory;
        this.zeroFlag = zeroFlag;
    }
}