package fr.ensimag.pseudocode.gba;

import java.io.PrintStream;

public class GUnaryDirective extends GDirective {
	private String parameter;
	
	public GUnaryDirective(String parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public void displayOperands(PrintStream s) {
		s.print(" ");
		s.print(parameter);
	}
}
