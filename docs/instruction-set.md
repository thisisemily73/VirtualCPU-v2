## Instruction Set Architecture (ISA)

## Overview

The CPU uses a 16-bit instruction architecture. Instructions define operations performed by the Control Unit and executed by hardware components.

---

# Data Movement Instructions

## LOAD

Format:
LOAD destination, value

Purpose:
Loads an immediate value into a register

Example: LOAD R1, 10

## STORE

Format:
STORE source, address

Purpose:
Stores register contents into memory

Example:
STORE R1, 100

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


## JNZ

Format:
JNZ address

Purpose:
Jumps if the zero flag is not set.

---

# Stack Instructions

## PUSH

Format:
PUSH register

Purpose:
Stores a register value onto the stack.

## POP

Format:
POP register

Purpose:
Restores a value from the stack.

---

# System Instructions

## HALT

Format:
HALT

Purpose:
Stops CPU execution.