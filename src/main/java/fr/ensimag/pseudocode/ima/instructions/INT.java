package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.BinaryInstructionDValToReg;

/**
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class INT extends BinaryInstructionDValToReg {

    public INT(DVal op1, GPRegister op2) {
        super(op1, op2);
    }

}
