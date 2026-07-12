package hardware;

public class Register {
    
    private int value;

    public Register() {
        value = 0;
    }

    // Store a 16-bit value in the register
    public void store(int value) {
        this.value = value & 0xFFFF; // Ensure only the lower 16 bits are stored
    }

    // Read stored value from the register
    public int read() {
        return value;
    }
}
