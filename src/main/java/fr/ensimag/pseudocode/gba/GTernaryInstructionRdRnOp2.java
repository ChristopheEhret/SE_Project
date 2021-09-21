package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.Register;

/**
 * Base class for instructions with 3 operands, the firsts being a
 * Registers, and the thrid an Op2 (register or imm8).
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GTernaryInstructionRdRnOp2 extends GTernaryInstruction {
    private boolean updateCPSR;
	
	public GTernaryInstructionRdRnOp2(Register rd, Register rn, GOp2 op, GCondition cond, boolean updateCPSR) {
        super(rd, rn, op, cond);
        this.updateCPSR = updateCPSR;
    }
    
    @Override
	String getName() {
        return this.getClass().getSimpleName().substring(1)
        		+ (cond!=GCondition.AL ? cond.name() : "")
        		+ (updateCPSR ? "s" : "");
    }
    
}
