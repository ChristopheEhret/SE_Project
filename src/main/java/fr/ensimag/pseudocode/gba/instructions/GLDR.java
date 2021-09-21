package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GBinaryInstructionRdAddr;
import fr.ensimag.pseudocode.gba.GCondition;

public class GLDR extends GBinaryInstructionRdAddr {

	public GLDR(Register r, DAddr op, GCondition cond) {
		super(r, op, cond);
	}
	
	public GLDR(Register r, DAddr op) {
		super(r, op, GCondition.AL);
	}

}
