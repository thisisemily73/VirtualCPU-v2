package assembler;

/**
 * InstructionEncoder
 *
 * Converts parsed Instruction objects into 16-bit machine code.
 *
 * The output format follows the CPU ISA:
 *
 * R-Type: [ opcode (5) | regA (3) | regB (3) | unused (5) ]
 *
 * I-Type: [ opcode (5) | regA (3) | immediate (8) ]
 *
 * J-Type: [ opcode (5) | address (11) ]
 */
import control.Opcodes;

public class InstructionEncoder {

    // Convertts and instruction into a 16-bit integer
    public int encode(Instruction instruction) {
        int opcode = Opcodes.getOpcode(instruction.getOpcode());

        switch (instruction.getOpcode()) {

            case "LOADI":

                /*
                 * LOADI format:
                 *
                 * opcode | register | immediate
                 *
                 * 5 bits | 3 bits | 8 bits
                 */
                return (opcode << 11)
                        | (instruction.getRegisterA() << 8)
                        | instruction.getImmediate();

            case "ADD":

                /*
                 * ADD format:
                 *
                 * opcode | regA | regB | unused
                 *
                 * 5 | 3 | 3 | 5
                 */
                return (opcode << 11)
                        | (instruction.getRegisterA() << 8)
                        | (instruction.getRegisterB() << 5);

            case "HALT":

                /*
                 * HALT has only an opcode.
                 */
                return (opcode << 11);

            default:

                throw new IllegalArgumentException(
                        "Unsupported instruction: "
                        + instruction.getOpcode()
                );
        }
    }
}
