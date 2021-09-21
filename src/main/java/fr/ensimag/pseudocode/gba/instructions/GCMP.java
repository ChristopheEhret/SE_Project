package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.gba.GBinaryInstructionROp2;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GBARegisterSet;

public class GCMP extends GBinaryInstructionROp2 {

	public GCMP(Register r, GOp2 op, GCondition cond) {
		super(r, op, cond, false);
	}
	
	public GCMP(Register r, GOp2 op) {
		super(r, op, GCondition.AL, false);
	}

	public GCMP(Register rd, Register rn) {
		super(rd, new GOp2(rn), GCondition.AL, false);
	}

}
