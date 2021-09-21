package fr.ensimag.deca.syntax;

import fr.ensimag.deca.tree.Location;
import fr.ensimag.deca.tree.LocationException;
import java.io.PrintStream;
import org.antlr.v4.runtime.IntStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;


public class IntegerTooLarge extends DecaRecognitionException {
        
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntegerTooLarge(DecaParser recognizer, ParserRuleContext ctx) {
        super(recognizer, ctx);
    }
    
    @Override
    public String getMessage() {
    	return "The integer read is too large for the machine (32 bits)";
    }
}
