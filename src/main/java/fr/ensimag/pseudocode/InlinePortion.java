package fr.ensimag.pseudocode;

import java.io.PrintStream;

/**
 * Portion of assembly code to be dumped verbatim into the
 * generated code.
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public class InlinePortion extends AbstractLine {
    private final String asmCode;
    
    public InlinePortion(String asmCode) {
        super();
        this.asmCode = asmCode;
    }
    
    @Override
    public void display(PrintStream s) {
        s.println(asmCode);
    }

}
