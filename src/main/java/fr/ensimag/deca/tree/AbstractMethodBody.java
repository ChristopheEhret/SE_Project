package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;

public abstract class AbstractMethodBody extends Tree {

	public abstract void decompile(IndentPrintStream s);


	protected  abstract void prettyPrintChildren(PrintStream s, String prefix) ;
	
	
	protected abstract void iterChildren(TreeFunction f);
	
	protected abstract void verifyMethodBody(DecacCompiler compiler,
            EnvironmentExp localEnv,EnvironmentExp envExpParam ,ClassDefinition currentClass, Type returnType)
            throws ContextualError;

	protected abstract void codeGenBodyIMA(DecacCompiler compiler, AbstractIdentifier typeReturned, String methodName);

	protected abstract void codeGenBodyGBA(DecacCompiler compiler, AbstractIdentifier typeReturned, String methodName);
}
