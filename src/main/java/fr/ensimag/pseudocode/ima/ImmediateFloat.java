package fr.ensimag.pseudocode.ima;

import fr.ensimag.pseudocode.DVal;

/**
 * Immediate operand containing a float value.
 * 
 * @author Ensimag
 * @date 01/01/2021
 */
public class ImmediateFloat extends DVal {
    private float value;

    public ImmediateFloat(float value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "#" + Float.toHexString(value);
    }
}
