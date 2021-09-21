package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GBinaryInstructionRdAddr;
import fr.ensimag.pseudocode.gba.GCondition;

public class GSTR extends GBinaryInstructionRdAddr {

	public GSTR(Register r, DAddr op, GCondition cond) {
		super(r, op, cond);
	}
	
	public GSTR(Register r, DAddr op) {
		super(r, op, GCondition.AL);
	}

}
