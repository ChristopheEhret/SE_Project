package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GBinaryInstructionRdRs;
import fr.ensimag.pseudocode.gba.GCondition;

public class GNEG extends GBinaryInstructionRdRs {
	
	public GNEG(Register rd, Register rs) {
		super(rd, rs, GCondition.AL, true);
	}
	
	public GNEG(Register rd, Register rs, GCondition cond) {
		super(rd, rs, cond, true);
	}
	
}
