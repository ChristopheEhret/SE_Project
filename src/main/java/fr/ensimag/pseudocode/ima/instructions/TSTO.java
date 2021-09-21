package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.UnaryInstructionImmInt;

/**
 * @author Ensimag
 * @date 01/01/2021
 */
public class TSTO extends UnaryInstructionImmInt {
    public TSTO(ImmediateInteger i) {
        super(i);
    }

    public TSTO(int i) {
        super(i);
    }
}
