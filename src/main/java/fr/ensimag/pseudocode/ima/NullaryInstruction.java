package fr.ensimag.pseudocode.ima;

import java.io.PrintStream;

/**
 * Instruction without operand.
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public abstract class NullaryInstruction extends Instruction {
    @Override
    public void displayOperands(PrintStream s) {
        // no operand
    }
}
