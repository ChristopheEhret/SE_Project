package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.Register;

/**
 * Base class for instructions with 2 operands, the first being a
 * register, and the second an Op2.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GBinaryInstructionRdRs extends GBinaryInstruction {
	private boolean updateCPSR;

    public GBinaryInstructionRdRs(Register rd, Register rs, GCondition cond, boolean updateCPSR) {
        super(rd, rs, cond);
        this.updateCPSR = updateCPSR;
    }

    @Override
	String getName() {
        return this.getClass().getSimpleName().substring(1)
        		+ (cond!=GCondition.AL ? cond.name() : "")
        		+ (updateCPSR ? "s" : "");
    }
    
}
