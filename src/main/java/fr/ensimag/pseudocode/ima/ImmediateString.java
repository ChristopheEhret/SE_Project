package fr.ensimag.pseudocode.ima;

import fr.ensimag.pseudocode.Operand;

/**
 * Immediate operand representing a string.
 * 
 * @author Ensimag
 * @date 01/01/2021
 */
public class ImmediateString extends Operand {
    private String value;

    public ImmediateString(String value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
