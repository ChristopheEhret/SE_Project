package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.ima.ImmediateString;
import fr.ensimag.pseudocode.ima.UnaryInstruction;

/**
 * @author Ensimag
 * @date 01/01/2021
 */
public class WSTR extends UnaryInstruction {
    public WSTR(ImmediateString op) {
        super(op);
    }
    
    public WSTR(String message) {
        super(new ImmediateString(message));
    }
    
}
