package fr.ensimag.pseudocode.gba;

import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.AbstractInstruction;
import fr.ensimag.pseudocode.AsmProgram;
import fr.ensimag.pseudocode.GBALine;
import fr.ensimag.pseudocode.Label;

/**
 * Abstract representation of a GBA program, i.e. set of Lines.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GBAProgram extends AsmProgram {

	@Override
    public void addComment(String s) {
        lines.add(new GBALine(s));
    }
	
	@Override
	public void addLabel(Label l) {
    	lines.add(new GBALine(l));
    }

	@Override
	public void addInstruction(AbstractInstruction i) {
		Validate.isTrue(i instanceof GAsm);
        lines.add(new GBALine(i));
    }
    
    @Override
    public void addFirst(AbstractInstruction i) {
        addFirst(new GBALine(i));
    }
    
    @Override
    public void addFirst(AbstractInstruction i, String comment) {
        addFirst(new GBALine(null, i, comment));
    }
    
    @Override
    public void addInstruction(AbstractInstruction i, String comment) {
    	Validate.notNull(i);
    	Validate.notNull(comment);
    	lines.add(new GBALine(null, i, comment));
    }
    
    @Override
    public void addEmptyLine() {
    	lines.add(new GBALine(null, null, null));
    }
    
}
