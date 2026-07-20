package hardware;

/**
 * Bus
 *
 * Represents the shared communication pathway
 * between CPU components.
 *
 * The CPU uses a 16-bit data bus, meaning
 * one transfer can carry one 16-bit word.
 */


public class Bus {
    
    private int data;

    public Bus() {
        data = 0;
    }

    // Places a value onto the bus
    public void send(int value) {
        data = value & 0xFFFF; // Ensure only 16 bits are stored
    }

    // Reads the current value on the bus
    public int read() {
        return data;
    }
}
