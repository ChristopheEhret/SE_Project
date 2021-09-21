package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.UnaryInstructionImmInt;

/**
 * Add a value to stack pointer.
 * 
 * @author Ensimag
 * @date 01/01/2021
 */
public class ADDSP extends UnaryInstructionImmInt {

    public ADDSP(ImmediateInteger operand) {
        super(operand);
    }

    public ADDSP(int i) {
        super(i);
    }

}
