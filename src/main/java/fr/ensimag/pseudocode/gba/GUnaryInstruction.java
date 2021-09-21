package fr.ensimag.pseudocode.gba;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.Operand;

/**
 * Instruction with a single operand.
 *
 * @author gl27
 * @date 01/01/2021
 */
public abstract class GUnaryInstruction extends GInstruction {
    private Operand operand;

    @Override
    public void displayOperands(PrintStream s) {
        s.print(" ");
        s.print(operand);
    }

    protected GUnaryInstruction(Operand operand, GCondition cond) {
    	super(cond);
        Validate.notNull(operand);
        this.operand = operand;
    }

    public Operand getOperand() {
        return operand;
    }

}
