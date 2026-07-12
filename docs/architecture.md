## Architecture Overview
This project implements a 16-bit virtual CPU designed to simulate the core components of a real processor at a conceptual level.

The system is divided into two primary subsystems: hardware and control.

## Hardware Components

The hardware layer models the physical elements of a CPU:

- RegisterFile: Stores general purpose registers used for computation.
- Special Registers: Includes the Program Counter (PC), Stack Pointer (SP), and Instruction Register (IR).
- RAM: Represents system memory; stores both instructions and data.
- Flags Register: Stores conditions on flags such as zero; used for branching decisions.

All components operate on 16-bit values, providing a balance between simplicity and scalability.

## Control Components

The control layer manages instruction execution:

- ControlUnit: Coordinates the fetch-decode-execute cycle.
- Decoder: Interprets instructions into readable signals for the ALU.
- Opcodes: Defines the instruction set architecture (ISA).

## Design Rationale

A 16-bit architecture was chosen to balance implementation simplicity with sufficient expression of more complex programs.

Separating hardware and control components reflects real-world CPU design principles and improves modularity, allowing individual substems to be tested and extended independently.

## Future Extensions

Planned improvements include:
- Stack-based operations
- Expanded instruction formats
- Performance measurement (cycle counting)
- A simple assembler for program input

## Notes

This architecture is inentionally simplified compared to real-world processors, but it preserves the key ideas needed to understand how instructions are executed and how components interact.