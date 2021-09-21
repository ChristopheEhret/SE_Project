package fr.ensimag.pseudocode.gba;

import java.io.PrintStream;

public abstract class GDirective extends GAsm {
	
	String getName() {
        return this.getClass().getSimpleName().substring(1); // to avoid class name conflicts, we add a G in front of each GBA instruction (we then have to remove it)
    }
	
	@Override
	public void display(PrintStream s) {
        s.print("."+getName());
        displayOperands(s);
    }

}
