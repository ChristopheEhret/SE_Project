package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.Register;

/**
 * Base class for instructions with 2 operands, the first being a
 * register, and the second an address.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GBinaryInstructionRdAddr extends GBinaryInstruction {

    public GBinaryInstructionRdAddr(Register op1, DAddr op2, GCondition cond) {
        super(op1, op2, cond);
    }

}
