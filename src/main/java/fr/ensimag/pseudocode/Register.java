package fr.ensimag.pseudocode;

/**
 * Register operand (including special registers like SP).
 * 
 * @author Ensimag
 * @date 01/01/2021
 */
public class Register extends DVal {
	
    private String name;
    public Register(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean isRegister() {
    	return true;
    }
}
