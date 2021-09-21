package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;

public class ListDeclField extends TreeList<AbstractDeclField> {
	@Override
    public void decompile(IndentPrintStream s) {
		for (AbstractDeclField c : getList()) {
            c.decompile(s);
            s.println();
        }
   	}
	public void verifyListDeclField(DecacCompiler compiler,
            ClassDefinition currentClass,ClassDefinition superClass) throws ContextualError {
		EnvironmentExp environmentExp = currentClass.getMembers();
		// Initialisation du nombre de champ de la classe
		currentClass.setNumberOfFields(superClass.getNumberOfFields());
		for(AbstractDeclField field : this.getList()) {
            field.verifyDeclField(compiler,environmentExp,currentClass);
        }
	}
	public void verifyListFieldBody(DecacCompiler compiler,ClassDefinition currentClass) throws ContextualError {
		for(AbstractDeclField field : this.getList()) {
            field.verifyFieldBody(compiler,currentClass);
        }
	}
}

