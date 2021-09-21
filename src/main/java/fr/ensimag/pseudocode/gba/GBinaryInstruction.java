package fr.ensimag.pseudocode.gba;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.Operand;

/**
 * Base class for instructions with 2 operands.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GBinaryInstruction extends GInstruction {
    private Operand operand1, operand2;

    public Operand getOperand1() {
        return operand1;
    }

    public Operand getOperand2() {
        return operand2;
    }

    @Override
    public void displayOperands(PrintStream s) {
        s.print(" ");
        s.print(operand1);
        s.print(", ");
        s.print(operand2);
    }

    protected GBinaryInstruction(Operand op1, Operand op2, GCondition cond) {
    	super(cond);
        Validate.notNull(op1);
        Validate.notNull(op2);
        this.operand1 = op1;
        this.operand2 = op2;
    }
}
