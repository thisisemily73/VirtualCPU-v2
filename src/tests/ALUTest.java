package tests;

import hardware.ALU;

public class ALUTest {

    public static void main(String[] args) {

        ALU alu = new ALU();

        System.out.println("ADD: " + alu.add(5, 3));
        System.out.println("SUB: " + alu.sub(5, 3));
        System.out.println("AND: " + alu.and(5, 3));
        System.out.println("OR: " + alu.or(5, 3));
        System.out.println("NOT: " + alu.not(5));

        System.out.println("Is Zero (0): " + alu.isZero(0));
        System.out.println("Is Zero (5): " + alu.isZero(5));
    }
}