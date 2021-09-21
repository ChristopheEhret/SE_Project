package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.Register;

/**
 * Base class for instructions with 2 registers.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GTernaryInstructionRdRmRs extends GTernaryInstruction {
	private boolean updateCPSR;

    public GTernaryInstructionRdRmRs(Register rd, Register rm, Register rs, GCondition cond, boolean updateCPSR) {
        super(rd, rm, rs, cond);
        this.updateCPSR = updateCPSR;
    }
    
    @Override
	String getName() {
        return this.getClass().getSimpleName().substring(1)
        		+ (cond!=GCondition.AL ? cond.name() : "")
        		+ (updateCPSR ? "s" : "");
    }
    

}
