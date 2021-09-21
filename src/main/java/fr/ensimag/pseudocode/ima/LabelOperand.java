package fr.ensimag.pseudocode.ima;

import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.Label;

/**
 * Label used as operand
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class LabelOperand extends DVal {
    public Label getLabel() {
        return label;
    }

    private Label label;
    
    public LabelOperand(Label label) {
        super();
        Validate.notNull(label);
        this.label = label;
    }

    @Override
    public String toString() {
        return label.toString();
    }

}
