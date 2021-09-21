package fr.ensimag.pseudocode.ima;

import java.io.PrintStream;

import fr.ensimag.pseudocode.AbstractInstruction;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.Line;

public class IMALine extends Line {
	
	public IMALine(Label label, AbstractInstruction instruction, String comment) {
        super(label, instruction, comment);
    }

    public IMALine(AbstractInstruction instruction) {
        super(instruction);
    }

    public IMALine(String comment) {
        super(comment);
    }

    public IMALine(Label label) {
        super(label);
    }

	@Override
    public void display(PrintStream s) {
        boolean tab = false;
        if (label != null) {
            s.print(label);
                        s.print(":");
            tab = true;
        }
        if (instruction != null) {
            s.print("\t");
            instruction.display(s);
            tab = true;
        }
        if (comment != null) {
            if (tab) {
                            s.print("\t");
                        }
            s.print("; " + comment);
        }
        s.println();
    }
}
