package hardware;

public class ProgramCounter {
    private int value;

    public ProgramCounter() {
        value = 0;
    }

    public void set(int value) {
        this.value = value & 0xFFFF; // Ensure only the lower 16 bits are stored
    }

    public int get() {
        return value;
    }

    public void increment() {
        value = (value + 1) & 0xFFFF; // Increment and wrap around at 16 bits
    }
}
