package hardware;

public class RegisterFile {
    
    private Register[] registers;

    public RegisterFile() {
        registers = new Register[8];
        for (int i = 0; i < 8; i++) {
            registers[i] = new Register();
        }
    }

    // Store a value in the specified register
    public void store(int index, int value) {
        if (index >= 0 && index < registers.length) {
            registers[index].store(value);
        } else {
            throw new IndexOutOfBoundsException("Register index out of bounds");
        }
    }

    // Read the value from the specified register
    public int read(int index) {
        if (index >= 0 && index < registers.length) {
            return registers[index].read();
        } else {
            throw new IndexOutOfBoundsException("Register index out of bounds");
        }
    }
}