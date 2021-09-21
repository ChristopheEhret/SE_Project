package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GRegListInstruction;

public class GPOP extends GRegListInstruction {
	
	public GPOP(Register[] regNumbers, GCondition cond) {
    	super(regNumbers, cond);
    }
	
	public GPOP(Register regNumber) {
    	super(regNumber, GCondition.AL);
    }

}
