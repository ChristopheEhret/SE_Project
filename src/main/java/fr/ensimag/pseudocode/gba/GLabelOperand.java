package fr.ensimag.pseudocode.gba;

import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.Label;

/**
 * Label used as operand
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GLabelOperand extends DAddr {
    public Label getLabel() {
        return label;
    }

    private Label label;
    
    public GLabelOperand(Label label) {
        super();
        Validate.notNull(label);
        this.label = label;
    }

    @Override
    public String toString() {
        return "="+label.toString();
    }

}
