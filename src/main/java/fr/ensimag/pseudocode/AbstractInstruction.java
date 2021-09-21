package fr.ensimag.pseudocode;

import java.io.PrintStream;

public abstract class AbstractInstruction {
	public abstract void display(PrintStream p);
	public abstract void displayOperands(PrintStream s);
}
