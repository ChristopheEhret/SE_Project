package fr.ensimag.pseudocode;

import java.io.PrintStream;

/**
 * Line of code in an asm program.
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public abstract class Line extends AbstractLine {
    public Line(Label label, AbstractInstruction instruction, String comment) {
        super();
        checkComment(comment);
        this.label = label;
        this.instruction = instruction;
        this.comment = comment;
    }

    public Line(AbstractInstruction instruction) {
        super();
        this.instruction = instruction;
    }

    public Line(String comment) {
        super();
        checkComment(comment);
        this.comment = comment;
    }

    public Line(Label label) {
        super();
        this.label = label;
    }

    private void checkComment(final String s) {
        if (s == null) {
            return;
        }
        if (s.contains("\n")) {
            throw new InternalError("Comment '" + s + "'contains newline character");
        }
        if (s.contains("\r")) {
            throw new InternalError("Comment '" + s + "'contains carriage return character");
        }
    }
    protected AbstractInstruction instruction;
    protected String comment;
    protected Label label;

    public abstract void display(PrintStream s);

    public void setInstruction(AbstractInstruction instruction) {
        this.instruction = instruction;
    }

    public AbstractInstruction getInstruction() {
        return instruction;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }
}
