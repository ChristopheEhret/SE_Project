package fr.ensimag.pseudocode;

/**
 * Operand that contains a value.
 * 
 * @author Ensimag
 * @date 01/01/2021
 */
public abstract class DVal extends Operand {
	public boolean isRegister() {
		return false;
	}
}
