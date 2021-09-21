package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.ima.BinaryInstruction;

/**
 * @author Ensimag
 * @date 01/01/2021
 */
public class STORE extends BinaryInstruction {
    public STORE(Register op1, DAddr op2) {
        super(op1, op2);
    }
}
