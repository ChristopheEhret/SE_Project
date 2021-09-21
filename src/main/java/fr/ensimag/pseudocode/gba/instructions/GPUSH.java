package fr.ensimag.pseudocode.gba.instructions;

import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GRegListInstruction;

public class GPUSH extends GRegListInstruction {
	
	public GPUSH(Register[] regNumbers, GCondition cond) {
    	super(regNumbers, cond);
    }
	
	public GPUSH(Register regNumber) {
    	super(regNumber, GCondition.AL);
    }

}
