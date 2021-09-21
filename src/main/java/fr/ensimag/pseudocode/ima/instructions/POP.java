package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.UnaryInstructionToReg;

/**
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class POP extends UnaryInstructionToReg {

    public POP(GPRegister op) {
        super(op);
    }

}
