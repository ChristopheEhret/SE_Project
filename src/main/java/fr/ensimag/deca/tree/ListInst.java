package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;

/**
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class ListInst extends TreeList<AbstractInst> {

    /**
     * Implements non-terminal "list_inst" of [SyntaxeContextuelle] in pass 3
     * @param compiler contains "env_types" attribute
     * @param localEnv corresponds to "env_exp" attribute
     * @param currentClass 
     *          corresponds to "class" attribute (null in the main bloc).
     * @param returnType
     *          corresponds to "return" attribute (void in the main bloc).
     */    
    public void verifyListInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        LOG.debug("Verify ListInst");

        for(AbstractInst i : getList())
            i.verifyInst(compiler, localEnv, currentClass, returnType);
    }
    
    public void codeGenListInstIMA(DecacCompiler compiler) {
        for (AbstractInst i : getList()) {
        	compiler.getRegistersHandler().resetRegisters();
            i.codeGenInstIMA(compiler);
        }
    }

    public void codeGenListInstGBA(DecacCompiler compiler) {
        for (AbstractInst i : getList()) {
            compiler.getRegistersHandler().resetRegisters();
            i.codeGenInstGBA(compiler);
        }
    }

    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractInst i : getList()) {
            i.decompileInst(s);
            s.println();
        }
    }
}
