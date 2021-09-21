package fr.ensimag.pseudocode.ima;

import java.io.PrintStream;

import fr.ensimag.pseudocode.AbstractInstruction;

/**
 * IMA instruction.
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public abstract class Instruction extends AbstractInstruction {
    String getName() {
        return this.getClass().getSimpleName();
    }
    public abstract void displayOperands(PrintStream s);
    public void display(PrintStream s) {
        s.print(getName());
        displayOperands(s);
    }
}
