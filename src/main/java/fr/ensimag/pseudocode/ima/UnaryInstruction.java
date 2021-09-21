package fr.ensimag.pseudocode.ima;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.Operand;

/**
 * Instruction with a single operand.
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public abstract class UnaryInstruction extends Instruction {
    private Operand operand;

    @Override
    public void displayOperands(PrintStream s) {
        s.print(" ");
        s.print(operand);
    }

    protected UnaryInstruction(Operand operand) {
        Validate.notNull(operand);
        this.operand = operand;
    }

    public Operand getOperand() {
        return operand;
    }

}
