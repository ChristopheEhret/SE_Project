package fr.ensimag.pseudocode.ima;

import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.AbstractInstruction;
import fr.ensimag.pseudocode.AsmProgram;
import fr.ensimag.pseudocode.Label;

/**
 * Abstract representation of an IMA program, i.e. set of Lines.
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class IMAProgram extends AsmProgram {

	@Override
    public void addComment(String s) {
        lines.add(new IMALine(s));
    }
	
	@Override
	public void addLabel(Label l) {
    	lines.add(new IMALine(l));
    }

	@Override
	public void addInstruction(AbstractInstruction i) {
		Validate.isTrue(i instanceof Instruction);
        lines.add(new IMALine(i));
    }

    @Override
    public void addFirst(AbstractInstruction i) {
        addFirst(new IMALine(i));
    }
    
    @Override
    public void addFirst(AbstractInstruction i, String comment) {
        addFirst(new IMALine(null, i, comment));
    }
    
    @Override
    public void addInstruction(AbstractInstruction i, String comment) {
    	Validate.notNull(i);
    	Validate.notNull(comment);
    	lines.add(new IMALine(null, i, comment));
    }
    
    @Override
    public void addEmptyLine() {
    	lines.add(new IMALine(null, null, null));
    }
    
}
