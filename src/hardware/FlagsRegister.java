package hardware;

/**
 * Represents the flags register in the CPU.
 * Stores condition flags that reflect the result of ALU operations.
 * These flags are used by the Control Unit for conditional branching and other operations.
 * 
 * Z (Zero Flag): Set if the result of an operation is zero.
 * C (Carry Flag): Set if arithmetic overflow occurs.
 * N (Negative Flag): Set if the result of an operation is negative (for signed operations).
 * 
 * Interacts with:
 * - ALU: Updates flags based on the result of operations.
 * - Control Unit: Uses flags to determine the flow of execution (e.g., branching).
 */
public class FlagsRegister {
    
    // True if last ALU result was zero, false otherwise
    private boolean zeroFlag;
    
    // True if last ALU operation resulted in a carry, false otherwise
    private boolean carryFlag;

    // True if last ALU result was negative, false otherwise
    private boolean negativeFlag;

    public FlagsRegister() {
        this.zeroFlag = false;
        this.carryFlag = false;
        this.negativeFlag = false;
    }

    // --- ZERO FLAG ---
    public boolean isZeroFlag() {
        return zeroFlag;
    }

    public void setZeroFlag(boolean zeroFlag) {
        this.zeroFlag = zeroFlag;
    }

    // --- CARRY FLAG ---
    public boolean isCarryFlag() {
        return carryFlag;
    }

    public void setCarryFlag(boolean carryFlag) {
        this.carryFlag = carryFlag;
    }

    // --- NEGATIVE FLAG ---
    public boolean isNegativeFlag() {
        return negativeFlag;
    }

    public void setNegativeFlag(boolean negativeFlag) {
        this.negativeFlag = negativeFlag;
    }
}