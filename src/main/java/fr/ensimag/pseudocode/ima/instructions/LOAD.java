package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.BinaryInstructionDValToReg;
import fr.ensimag.pseudocode.ima.ImmediateFloat;
import fr.ensimag.pseudocode.ima.ImmediateInteger;

/**
 * @author Ensimag
 * @date 01/01/2021
 */
public class LOAD extends BinaryInstructionDValToReg {

    public LOAD(DVal op1, GPRegister op2) {
        super(op1, op2);
    }

    public LOAD(int i, GPRegister r) {
        this(new ImmediateInteger(i), r);
    }

    public LOAD(float f, GPRegister r) {
        this(new ImmediateFloat(f), r);
    }
}
