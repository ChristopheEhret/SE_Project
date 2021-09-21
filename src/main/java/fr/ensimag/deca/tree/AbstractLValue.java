package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.pseudocode.DAddr;

/**
 * Left-hand side value of an assignment.
 * 
 * @author gl27
 * @date 01/01/2021
 */
public abstract class AbstractLValue extends AbstractExpr {
	protected abstract VirtualDAddr codeGenAssign(DecacCompiler compiler);
}
