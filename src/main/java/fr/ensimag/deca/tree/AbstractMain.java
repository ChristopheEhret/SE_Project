package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;

/**
 * Main block of a Deca program.
 *
 * @author gl27
 * @date 01/01/2021
 */
public abstract class AbstractMain extends Tree {

    protected abstract void codeGenMainIMA(DecacCompiler compiler, int offsetGB);
    
    protected abstract void codeGenMainGBA(DecacCompiler compiler, int offsetGB);

    /**
     * Implements non-terminal "main" of [SyntaxeContextuelle] in pass 3 
     */
    protected abstract void verifyMain(DecacCompiler compiler) throws ContextualError;
}
