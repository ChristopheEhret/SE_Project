package fr.ensimag.pseudocode.gba;

import java.io.PrintStream;

import org.apache.commons.lang.Validate;

/**
 * GBA instruction.
 *
 * @author GL27
 * @date 01/01/2021
 */
public abstract class GInstruction extends GAsm {
	protected GCondition cond;
	
	public GInstruction(GCondition cond) {
		Validate.notNull(cond);
		this.cond = cond;
	}
	
    String getName() {
    	// to avoid class name conflicts, we add a G in front of each GBA instruction (we then have to remove it)
        return this.getClass().getSimpleName().substring(1)
        		+ (cond!=GCondition.AL ? cond.name() : ""); 
    }
    
    @Override
    public void display(PrintStream s) {
        s.print(getName());
        displayOperands(s);
    }
}
