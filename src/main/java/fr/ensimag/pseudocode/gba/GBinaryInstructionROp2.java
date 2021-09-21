package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.Register;

/**
 * Base class for instructions with 2 operands, the first being a
 * register, and the second an Op2.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GBinaryInstructionROp2 extends GBinaryInstruction {
	private boolean updateCPSR;

    public GBinaryInstructionROp2(Register op1, GOp2 op2, GCondition cond, boolean updateCPSR) {
        super(op1, op2, cond);
        this.updateCPSR = updateCPSR;
    }

    @Override
	String getName() {
        return this.getClass().getSimpleName().substring(1)
        		+ (cond!=GCondition.AL ? cond.name() : "")
        		+ (updateCPSR ? "s" : "");
    }
    
}
