package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GBranchLabelInstruction;
import fr.ensimag.pseudocode.gba.GCondition;

public class GB extends GBranchLabelInstruction {

	public GB(Label op, GCondition cond) {
		super(op, cond);
	}
	
	public GB(Label op) {
		super(op, GCondition.AL);
	}

}
