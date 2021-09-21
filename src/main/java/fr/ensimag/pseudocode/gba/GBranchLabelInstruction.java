package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.Label;

/**
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class GBranchLabelInstruction extends GBranchInstruction {

    public GBranchLabelInstruction(Label lbl, GCondition cond) {
        super(lbl, cond);
    }

}
