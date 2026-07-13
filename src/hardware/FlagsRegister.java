package hardware;

public class FlagsRegister {
    private boolean zeroFlag;
    private boolean carryFlag;
    private boolean negativeFlag;

    public FlagsRegister() {
        this.zeroFlag = false;
        this.carryFlag = false;
        this.negativeFlag = false;
    }

    public boolean isZeroFlag() {
        return zeroFlag;
    }

    public void setZeroFlag(boolean zeroFlag) {
        this.zeroFlag = zeroFlag;
    }

    public boolean isCarryFlag() {
        return carryFlag;
    }

    public void setCarryFlag(boolean carryFlag) {
        this.carryFlag = carryFlag;
    }

    public boolean isNegativeFlag() {
        return negativeFlag;
    }

    public void setNegativeFlag(boolean negativeFlag) {
        this.negativeFlag = negativeFlag;
    }
}