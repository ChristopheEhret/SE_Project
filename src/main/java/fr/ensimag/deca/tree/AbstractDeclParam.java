package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.ParamDefinition;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.context.Type;


public abstract class AbstractDeclParam extends Tree {


	protected abstract void prettyPrintChildren(PrintStream s, String prefix) ;
	
	protected abstract void iterChildren(TreeFunction f) ;

    public abstract void decompile(IndentPrintStream s) ;
	
    protected abstract Type verifyDeclParam(DecacCompiler compiler)
	            throws ContextualError;

	public abstract void codeGenDeclIMA(DecacCompiler compiler, VirtualDAddr operand);
	public abstract void codeGenDeclGBA(DecacCompiler compiler, VirtualDAddr operand);
	protected abstract ParamDefinition verifyParamBody(DecacCompiler compiler)
				throws ContextualError ;

	public abstract AbstractIdentifier getParamName();
}
