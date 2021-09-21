package fr.ensimag.pseudocode.ima.instructions;

import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.ima.LabelOperand;
import fr.ensimag.pseudocode.ima.UnaryInstruction;

/**
 * @author Ensimag
 * @date 01/01/2021
 */
public class BSR extends UnaryInstruction {

    public BSR(DVal operand) {
        super(operand);
    }
    
    public BSR(Label target) {
        super(new LabelOperand(target));
    }

}
