## Instruction Set Architecture (ISA)

## Overview

The CPU uses a 16-bit instruction architecture. Instructions define operations performed by the Control Unit and executed by hardware components.

---

# Opcode Table

| Instruction | Opcode | Type |
|-------------|--------|------|
| NOP | 00000 | R |
| LOADI | 00001 | I |
| LOAD | 00010 | M |
| STORE | 00011 | M |
| ADD | 00100 | R |
| SUB | 00101 | R |
| JMP | 00110 | J |
| JZ | 00111 | J |
| JNZ | 01000 | J |
| PUSH | 01001 | R |
| POP | 01010 | R |
| AND | 01011 | R |
| OR | 01100 | R |
| NOT | 01101 | R |
| HALT | 11111 | R |

* Opcode assignments are provisional and may change during implementation.

---

# Data Movement Instructions

## LOADI

Format:
LOADI destination, immediate

Purpose:
Loads an immediate value into a register

Example: LOADI R1, 10

## LOAD

Format:
LOAD destination, memory_address

Purpose:
Loads a value from memory into a register

Example:
LOAD R1, [100]

## STORE

Format:
STORE source, memory_address

Purpose:
Stores register contents into memory

Example:
STORE R1, [100]

---

# Arithmetic Instructions

## ADD

Format:
ADD destination, source

Purpose:
Adds two register values.

Example:
ADD R1, R2


## SUB

Format:
SUB destination, source

Purpose:
Subtracts source register from destination register.

Example:
SUB R1, R2
---

# Control Flow Instructions

## JMP

Format:
JMP address

Purpose:
Changes the program counter to a new instruction address.

Example:
JMP 1000

## JNZ

Format:
JNZ address

Purpose:
Jumps if the zero flag is not set.

Example:
JZ 500

## JZ

Format:
JZ address

Purpose: Jumps if the zero flag is set.

Example:
JNZ 200

## NOP

Format:
NOP

Purpose:
Performs no operation.

Used for timing, alignment, and testing

---

# Stack Instructions

## PUSH

Format:
PUSH register

Purpose:
Stores a register value onto the stack.

The Stack Pointer (SP) is updated after storing the value.

## POP

Format:
POP register

Purpose:
Restores a value from the stack.

The Stack Pointer (SP) is updated after retrieving the variable.


---

# System Instructions

## HALT

Format:
HALT

Purpose:
Stops CPU execution.

---

# Logical Instructions

## AND

Format:
AND destination, source

Purpose:
Performs bitwise AND.

Example:
AND R1, R2


## OR

Format:
OR destination, source

Purpose:
Performs bitwise OR.


## NOT

Format:
NOT destination

Purpose:
Flips all bits in a register.