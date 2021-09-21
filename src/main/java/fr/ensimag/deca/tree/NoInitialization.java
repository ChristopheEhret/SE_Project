package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;

import java.io.PrintStream;

/**
 * Absence of initialization (e.g. "int x;" as opposed to "int x =
 * 42;").
 *
 * @author gl27
 * @date 01/01/2021
 */
public class NoInitialization extends AbstractInitialization {

    @Override
    protected void verifyInitialization(DecacCompiler compiler, Type t,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        // Nothing
    }


    /**
     * Node contains no real information, nothing to check.
     */
    @Override
    protected void checkLocation() {
        // nothing
    }

    @Override
    public void decompile(IndentPrintStream s) {
        // nothing
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

	@Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
        // nothing
		return null;
	}


	@Override
	protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
		// nothing
	}
	@Override
	public AbstractExpr getExpression() {
        return null;
    }


	@Override
	protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
		// nothing
		return null;
	}


	@Override
	protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
		// nothing
	}
  
}
