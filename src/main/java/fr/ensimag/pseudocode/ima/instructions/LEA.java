package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.BinaryInstructionDAddrToReg;

/**
 * @author Ensimag
 * @date 01/01/2021
 */
public class LEA extends BinaryInstructionDAddrToReg {

    public LEA(DAddr op1, GPRegister op2) {
        super(op1, op2);
    }

}
