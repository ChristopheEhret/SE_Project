package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.Operand;

/**
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class GBranchInstruction extends GUnaryInstruction {

    public GBranchInstruction(Operand op, GCondition cond) {
        super(op, cond);
    }

}
