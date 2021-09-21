package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.BinaryInstructionDValToReg;
import fr.ensimag.pseudocode.ima.ImmediateInteger;

/**
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class CMP extends BinaryInstructionDValToReg {

    public CMP(DVal op1, GPRegister op2) {
        super(op1, op2);
    }

    public CMP(int val, GPRegister op2) {
        this(new ImmediateInteger(val), op2);
    }

}