# Instruction Format

## Overview

The CPU uses a 16-bit instruction architecture with a 5-bit opcode field.

Most instructions fit within a single 16-bit word. Some instructions use additional memory words to store extended operands such as addresses or large immediate values.

The instruction format is designed to balance instruction variety, scalability, and simplicity.

Different instruction formats are used depending on the type of operation being performed.

---

Data width: 16 bits
Instruction size: 16 bits
Opcode: 5 bits
Registers: 8 (R0-R7)
Register ID: 3 bits
Memory: 4096 x 16-bit words
Address size: 12 bits
Architecture: Von Neumann

---

# Opcode Field

The first 5 bits of every instruction represent the opcode.

A 5-bit opcode allows for:

2^5 = 32 possible instructions

This provides enough space for future instruction expansion while maintaining decoding simplicity.

---

# Register Architecture

The CPU contains 8 general-purpose registers:

- R0
- R1
- R2
- R3
- R4
- R5
- R6
- R7

Each register is represented using a 3-bit identifier, allowing the CPU to address 8 unique registers.

---

# Memory Model

The CPU uses a Von Neumann architecture, where instructions and data share the same memory.

The memory system uses 12-bit addresses, allowing:

2^12 = 4096 addressable memory locations

Each memory location stores a 16-bit word, matching the CPU's data width.

This allows instructions and data to be accessed using the same memory system.

## Multi-Word Instructions

Some instructions require additional information that cannot fit within a single 16-bit instruction.

These instructions use additional memory words following the opcode.

Examples:
- Memory addresses
- Large immediate values
- Jump targets

The Control Unit determines whether an instruction requires additional words during decoding.

---

# Instruction Formats

## Register Type (R-Type)

Examples: 
ADD R1, R2
SUB R3, R4
AND R5, R6

Layout:
- OPCODE: 5 bits
- REG A: 3 bits
- REG B: 3 bits
- UNUSED: 5 bits

Example:
ADD R1, R2

becomes:

00100 001 010 00000

where:

00100 = ADD
001 = R1
010 = R2

## Immediate Type (I-Type)

Examples:
LOADI R1, 42
ADDI R2, 10

Layout:
- OPCODE: 5 bits
- REG A: 3 bits
- IMMEDIATE: 8 bits

The immediate field stores small constant values directly inside the instruction.

Because the field is 8 bits, immediate values range from:

0-255

Example:

LOADI R1, 42

becomes

00001 = LOADI
001 = R1
00101010 = 42

## Memory Type (M-Type)

Used for instructions that access memory.

Examples:
LOAD R1, [1000]
STORE R2, [2000]

Layout:
- OPCODE: 5 bits
- REG A: 3 bits
- ADDRESS: stored in following memory word

Because memory addresses require 12 bits, M-Type instructions use a second 16-bit word containing the full address.

Example:

LOAD R1, [1000]

Memory:

Instruction word:
00011 001 00000000

where:
00011 = LOAD
001 = R1

Next word:
0000 0011 1110 1000

where the lower 12 bits contain the address 1000

## Jump Type (J-Type)

Used for changing program execution flow.

Examples:
JMP 1000
JZ 500
JNZ 200

Layout:
- OPCODE: 5 bits
- ADDRESS: stored in following memory word

The additional word allows the CPU to access the full 12-bit memory address space.

Example:

JMP 1000

Memory:

Instruction word:
00010 00000000000

Next word:
0000001111101000

where:

00010 = JMP
0000001111101000 = address 1000

---

# Special Registers

In addition to general purpose registers, the CPU contains dedicated registers used for control and execution.

## Program Counter (PC)

Stores the address of the next instruction to execute.

## Instruction Register (IR)

Stores the currently fetched instruction.

## Stack Pointer (SP)

Stores the current location of the stack in memory.

## Flags Register

Stores condition information used by branching instructions.

Current flags include:
- Zero Flag (Z)
- Negative Flag (N)