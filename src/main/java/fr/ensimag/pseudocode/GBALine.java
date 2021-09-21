package fr.ensimag.pseudocode;

import java.io.PrintStream;

public class GBALine extends Line {
	
	public GBALine(Label label, AbstractInstruction instruction, String comment) {
        super(label, instruction, comment);
    }

    public GBALine(AbstractInstruction instruction) {
        super(instruction);
    }

    public GBALine(String comment) {
        super(comment);
    }

    public GBALine(Label label) {
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
            s.print("@ " + comment);
        }
        s.println();
    }
}
