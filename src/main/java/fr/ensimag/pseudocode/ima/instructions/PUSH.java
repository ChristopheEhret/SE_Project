package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.ima.UnaryInstruction;

/**
 * @author Ensimag
 * @date 01/01/2021
 */
public class PUSH extends UnaryInstruction {
    public PUSH(Register op1) {
        super(op1);
    }
}
