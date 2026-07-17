package hardware;

/**
 * Multiplexer
 *
 * Selects one of two 16-bit inputs based on a control signal.
 *
 * selector = 0 → input A
 * selector = 1 → input B
 */

public class Multiplexer {
    public int select(int inputA, int inputB, int selector) {
        if (selector == 0) {
            return inputA;
        } else if (selector == 1) {
            return inputB;
        } else {
            throw new IllegalArgumentException(
                "Invalid selector: " + selector
                + ". Selector must be 0 or 1."
            );
        }
    }
}
