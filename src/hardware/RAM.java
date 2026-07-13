package hardware;

public class RAM {

    private int[] memory;

    public RAM() {
        memory = new int[4096];
    }

    public void write(int address, int value) {
        if (address < 0 || address >= memory.length) {
            throw new IllegalArgumentException("Address out of bounds");
        }
        memory[address] = value & 0xFFFF;
    }

    

    public int read(int address) {
        if (address < 0 || address >= memory.length) {
            throw new IllegalArgumentException("Address out of bounds");
        }
        return memory[address] & 0xFFFF;
    }
}