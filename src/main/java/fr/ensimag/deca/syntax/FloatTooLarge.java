package fr.ensimag.deca.syntax;

import org.antlr.v4.runtime.ParserRuleContext;

public class FloatTooLarge extends DecaRecognitionException {
	
	public FloatTooLarge(DecaParser recognizer, ParserRuleContext ctx) {
        super(recognizer, ctx);
    }
    
    @Override
    public String getMessage() {
    	return "The float read is too large for the machine (32 bits)";
    }
}
