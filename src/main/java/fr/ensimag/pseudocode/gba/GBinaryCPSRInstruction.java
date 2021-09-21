package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.Operand;

public abstract class GBinaryCPSRInstruction extends GBinaryInstruction {
	private boolean updateCPSR;
	
	protected GBinaryCPSRInstruction(Operand op1, Operand op2, GCondition cond, boolean updateCPSR) {
		super(op1, op2, cond);
		this.updateCPSR = updateCPSR;
	}
	
	

}
