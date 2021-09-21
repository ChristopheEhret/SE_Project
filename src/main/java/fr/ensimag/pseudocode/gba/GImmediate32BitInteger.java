package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.DAddr;

/**
 * Immediate operand representing a 32 bit integer.
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class GImmediate32BitInteger extends DAddr {
    private int value;

    public GImmediate32BitInteger(int value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "=" + value;
    }
}
