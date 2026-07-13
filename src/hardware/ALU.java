package hardware;

public class ALU {
    // ADD
    public int add(int a, int b) {
        return (a + b) & 0xFFFF; // Ensure only the lower 16 bits are returned
    }

    // SUB
    public int sub(int a, int b) {
        return (a - b) & 0xFFFF; // Ensure only the lower 16 bits are returned
    }

    // AND
    public int and(int a, int b) {
        return a & b;
    }

    // OR
    public int or(int a, int b) {
        return a | b;
    }

    // NOT
    public int not(int a) {
        return (~a) & 0xFFFF; // Ensure only the lower 16 bits are returned
    }

    // ISZERO
    public boolean isZero(int a) {
        return a == 0;
    }
}