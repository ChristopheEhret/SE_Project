package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.Operand;

/**
 * Immediate operand representing a 24 bit integer (for SWI)
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class GImmediate24BitInteger extends Operand {
    private int value;

    public GImmediate24BitInteger(int value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "#" + value;
    }
}
