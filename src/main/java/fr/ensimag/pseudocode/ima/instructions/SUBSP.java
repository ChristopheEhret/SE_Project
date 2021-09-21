package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.UnaryInstructionImmInt;

/**
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class SUBSP extends UnaryInstructionImmInt {

    public SUBSP(ImmediateInteger operand) {
        super(operand);
    }

    public SUBSP(int i) {
        super(i);
    }

}
