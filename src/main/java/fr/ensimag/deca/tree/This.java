package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualStaticRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;

public class This extends AbstractExpr{
	
	private boolean implicite;
	
	public This(boolean implicite) {
		super();
		this.implicite=implicite;
	}

	public boolean isImplicite() {
		return implicite;
	}



	@Override
    public void decompile(IndentPrintStream s) {
		if(!implicite) {
			s.print("this");
		}
	}
    @Override
    protected
    void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
   }
    /**
     * Pass 3 , rule 3.43 of [SyntaxeContextuelle] 
     */
    public Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
    	if(currentClass == null) {
            throw new InternalError("Cannot use this in undefined class ");

    	}
    	if(currentClass.getType().getName().getName() == "0" && this.implicite == false) {
    		throw new ContextualError("Cannot use this in main",this.getLocation());
    	}    	this.setType(currentClass.getType());
    		return this.getType();
    } 		

    @Override
    public VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
        return new VirtualStaticRegisterOffset(compiler.getRegistersHandler(), compiler.getRegisterSet().LB, -2);
    }
}
