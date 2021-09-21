package fr.ensimag.deca.tree;

import java.io.PrintStream;
import fr.ensimag.deca.tools.IndentPrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

public abstract class AbstractDeclMethod extends Tree {


 	 protected abstract void prettyPrintChildren(PrintStream s, String prefix) ;
	
	 protected abstract void iterChildren(TreeFunction f) ;

     public abstract void decompile(IndentPrintStream s) ;
	 
     protected abstract void verifyDeclMethod(DecacCompiler compiler,
	            EnvironmentExp localEnv, ClassDefinition currentClass)
	            throws ContextualError;
	
	 public abstract void verifyMethodBody(DecacCompiler compiler,EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError;
    
	public abstract String getMethodName();
	
	public abstract void buildMethodLabels(String className);
	
	public abstract void codeGenMethodIMA(DecacCompiler compiler);
	
	public abstract void codeGenMethodGBA(DecacCompiler compiler);

}
