package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GImmediate24BitInteger;
import fr.ensimag.pseudocode.gba.GUnaryInstruction;

public class GSWI extends GUnaryInstruction {
	
	public GSWI(GImmediate24BitInteger syscall) {
		super(syscall, GCondition.AL);
	}
	
	public GSWI(GImmediate24BitInteger syscall, GCondition cond) {
		super(syscall, cond);
	}
	
}
