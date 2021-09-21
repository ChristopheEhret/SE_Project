package fr.ensimag.deca.codegen;

import java.util.HashMap;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.Label;

public class ErrorManager {
	private HashMap<String, AsmError> errors;
	
	public ErrorManager() {
		errors = new HashMap<String, AsmError>();
	}
	
	public void addError(String id, String msg) {
		assert(!errors.containsKey(id));
		errors.put(id, new AsmError("ERR."+id, msg));
	}
	
	public void codeGenError(DecacCompiler compiler) {
		compiler.addComment("Erreurs :");
		for(HashMap.Entry<String, AsmError> err : errors.entrySet())
			err.getValue().codeGenError(compiler);
	}
	
	public Label getErrorLabel(String id) {
		assert(errors.containsKey(id));
		return errors.get(id).getErrorLabel();
	}
}
