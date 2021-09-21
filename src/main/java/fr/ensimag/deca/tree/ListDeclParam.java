package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.EnvironmentExp.DoubleDefException;


public class ListDeclParam extends TreeList<AbstractDeclParam> {
	@Override
    public void decompile(IndentPrintStream s) {
		int counter=1,size=this.getList().size();
    	for(AbstractDeclParam e : this.getList()) {
    		e.decompile(s);
    		if(counter!=size) {
    			s.print(",");
    		}
    		counter ++;
    	}		
   	}
	/**
     * Pass 2 , rule 2.8 of [SyntaxeContextuelle] 
     */
	public Signature verifyListDeclParam(DecacCompiler compiler) throws ContextualError { 
               Signature sig = new Signature();
               for(AbstractDeclParam param : this.getList()) {
                   sig.add(param.verifyDeclParam(compiler));
               }
               return sig;
	}
	/**
     * Pass 3 , rule 3.12 of [SyntaxeContextuelle] 
     */
	
	public EnvironmentExp verifyListParamBody(DecacCompiler compiler) throws ContextualError{ 
		EnvironmentExp envExpParam = new EnvironmentExp(null);
		for(AbstractDeclParam param : this.getList()) {
			try {
	            envExpParam.declare(param.getParamName().getName(),param.verifyParamBody(compiler));
	        } catch (DoubleDefException e) {
	            throw new ContextualError("Parameter  " + param.getParamName().getName().getName() + " has been duplicated", this.getLocation());
	        }
        }
		return envExpParam;
	}
	
	
}
