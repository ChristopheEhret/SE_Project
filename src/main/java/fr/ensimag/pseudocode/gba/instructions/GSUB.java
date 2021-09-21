package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GBARegisterSet;
import fr.ensimag.pseudocode.gba.GTernaryInstructionRdRnOp2;

public class GSUB extends GTernaryInstructionRdRnOp2 {

	public GSUB(Register rd, Register rn, GOp2 op, GCondition cond, boolean updateCPSR) {
		super(rd, rn, op, cond, updateCPSR);
	}
	
	public GSUB(Register rd, Register rn, GOp2 op) {
		super(rd, rn, op, GCondition.AL, true);
	}

}
