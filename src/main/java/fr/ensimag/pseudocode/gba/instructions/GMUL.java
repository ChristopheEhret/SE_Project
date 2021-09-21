package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GBARegisterSet;
import fr.ensimag.pseudocode.gba.GTernaryInstructionRdRmRs;

public class GMUL extends GTernaryInstructionRdRmRs {

	public GMUL(Register rd, Register rm, Register rs, GCondition cond, boolean updateCPSR) {
		super(rd, rm, rs, cond, true);
	}
	
	public GMUL(Register rd, Register rm, Register rs) {
		super(rd, rm, rs, GCondition.AL, false);
	}

}
