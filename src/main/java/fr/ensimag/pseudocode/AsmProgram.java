package fr.ensimag.pseudocode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

/**
 * Abstract representation of an ASM program, i.e. set of Lines.
 *
 * @author gl27
 * @date 01/01/2021
 */
public abstract class AsmProgram {
    protected final LinkedList<AbstractLine> lines = new LinkedList<AbstractLine>();

    public void add(AbstractLine line) {
        lines.add(line);
    }
    
    public abstract void addLabel(Label l);
    
    public abstract void addComment(String s);
    
    public abstract void addInstruction(AbstractInstruction i);
    
    public abstract void addFirst(AbstractInstruction i);
    
    public abstract void addFirst(AbstractInstruction i, String comment);
    
    public abstract void addInstruction(AbstractInstruction i, String comment);

    /**
     * Append the content of program p to the current program. The new program
     * and p may or may not share content with this program, so p should not be
     * used anymore after calling this function.
     */
    public void append(AsmProgram p) {
        lines.addAll(p.lines);
    }
    
    /**
     * Remove the last line added (for optimization purposes)
     */
    public void removeLast() {
    	lines.removeLast();
    }
    
    /**
     * Add a line at the front of the program.
     */
    public void addFirst(Line l) {
        lines.addFirst(l);
    }

    /**
     * Display the program in a textual form readable by IMA to stream s.
     */
    public void display(PrintStream s) {
        for (AbstractLine l: lines) {
            l.display(s);
        }
    }
    
    public abstract void addEmptyLine();

    /**
     * Return the program in a textual form readable by IMA as a String.
     */
    public String display() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream s = new PrintStream(out);
        display(s);
        return out.toString();
    }

}
