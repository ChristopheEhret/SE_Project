package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.gba.GBinaryInstructionROp2;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.ShiftType;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GBARegisterSet;

public class GMOV extends GBinaryInstructionROp2 {

	public GMOV(Register r, GOp2 op, GCondition cond) {
		super(r, op, cond, true);
	}
	
	public GMOV(Register r, GOp2 op) {
		super(r, op, GCondition.AL, true);
	}
	
	public GMOV(Register rd, Register rn) {
		super(rd, new GOp2(rn, ShiftType.NONE, 0), GCondition.AL, true);
	}

}
