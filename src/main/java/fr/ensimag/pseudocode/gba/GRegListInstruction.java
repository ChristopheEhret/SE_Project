package fr.ensimag.pseudocode.gba;

import java.io.PrintStream;

import fr.ensimag.pseudocode.Register;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public abstract class GRegListInstruction extends GInstruction {
	Register[] registers;

    public GRegListInstruction(Register regNumber, GCondition cond) {
    	super(cond);
    	registers = new Register[1];
    	registers[0] = regNumber;
    }
    
    public GRegListInstruction(Register[] regNumbers, GCondition cond) {
    	super(cond);
    	this.registers = regNumbers;
    }
    
    @Override
    public String toString() {
    	String retStr = " {";
    	retStr += registers[0];
    	for(int i=1; i<registers.length; i++)
    		retStr += ", "+registers[i];
    	retStr += "}";
    	return retStr;
    }

	@Override
	public void displayOperands(PrintStream s) {
		s.print(this);
	}

}
