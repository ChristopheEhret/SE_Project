package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;

public class ListDeclMethod extends TreeList<AbstractDeclMethod> {
	@Override
    public void decompile(IndentPrintStream s) {
		for (AbstractDeclMethod c : getList()) {
            c.decompile(s);
            s.println();
        }
   	}
	/**
     * Pass 2 , rule 2.6 of [SyntaxeContextuelle] 
     */	
	public void verifyListDeclMethod(DecacCompiler compiler,ClassDefinition currentClass) throws ContextualError {
		EnvironmentExp environmentExp = currentClass.getMembers();
		// Initialisation du nombre de champ de la classe
		currentClass.setNumberOfMethods(currentClass.getSuperClass().getNumberOfMethods());
		for(AbstractDeclMethod method : this.getList()) {
            method.verifyDeclMethod(compiler,environmentExp,currentClass);
        }
    }

	/**
     * Pass 3 , rule 3.10 of [SyntaxeContextuelle] 
     */	
	public void verifyListMethodBody(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError { 
			for(AbstractDeclMethod method : this.getList()) {
				method.verifyMethodBody(compiler,localEnv,currentClass);        	
			}
		
	}
}
