package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;

public abstract class AbstractDeclField extends Tree {


	protected abstract void prettyPrintChildren(PrintStream s, String prefix) ;
	
	protected abstract void iterChildren(TreeFunction f) ;

    public abstract void decompile(IndentPrintStream s) ;
	
	protected abstract void verifyDeclField(DecacCompiler compiler,EnvironmentExp localEnv,ClassDefinition currentClass)
            throws ContextualError;
	protected abstract void verifyFieldBody(DecacCompiler compiler,ClassDefinition currentClass) throws ContextualError ;


	public abstract void codeGenFieldPreDecl(DecacCompiler compiler, GPRegister objectAddrReg, GPRegister initReg);
	public abstract void codeGenFieldDecl(DecacCompiler compiler, boolean fullDecl);

	public abstract void codeGenFieldPreDeclGBA(DecacCompiler compiler, GPRegister objectAddrReg, GPRegister initReg);
	public abstract void codeGenFieldDeclGBA(DecacCompiler compiler, boolean fullDecl);
}
