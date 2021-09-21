package fr.ensimag.pseudocode.ima;

import fr.ensimag.pseudocode.DVal;

/**
 * Immediate operand representing an integer.
 * 
 * @author Ensimag
 * @date 01/01/2021
 */
public class ImmediateInteger extends DVal {
    private int value;

    public ImmediateInteger(int value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "#" + value;
    }
}
