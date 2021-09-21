package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GBranchLabelInstruction;
import fr.ensimag.pseudocode.gba.GCondition;

public class GBL extends GBranchLabelInstruction {

	public GBL(Label op, GCondition cond) {
		super(op, cond);
	}
	
	public GBL(Label op) {
		super(op, GCondition.AL);
	}

}
