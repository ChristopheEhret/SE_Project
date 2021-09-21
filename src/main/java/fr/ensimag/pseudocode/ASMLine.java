package fr.ensimag.pseudocode;

import java.io.PrintStream;

public class ASMLine extends AbstractLine {
    String asmCode = "";

    public ASMLine(String asmCode) {
        this.asmCode = asmCode;
    }

    public void display(PrintStream stream) {
        if(this.asmCode.length() > 0) {
            stream.println(this.asmCode);

            return;
        } 
        
        throw new InternalError("ASMLine declared with no code in it.");
    }
}
