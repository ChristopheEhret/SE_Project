package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualBooleanImmediate;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.instructions.BRA;

import java.io.PrintStream;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public class BooleanLiteral extends AbstractExpr {
    private boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {

        LOG.debug("Verify BooleanLiteral");
        Type booleanType = compiler.getType(compiler.getOrAddSymbol("boolean"));
        if(booleanType == null)
                throw new InternalError("Le type boolean n'est pas défini dans le DecacCompiler.");

        this.setType(booleanType);
        return this.getType();
    }
    
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	return new VirtualBooleanImmediate(compiler.getRegistersHandler(),  value ? 1 : 0);
    }
    
    @Override
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
    	if((flow.getBranchCond() && value) || (!flow.getBranchCond() && !value)) {
            compiler.addInstruction(new BRA(flow.getBranchLabel()));
        }
    }
    
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
    	return new VirtualBooleanImmediate(compiler.getRegistersHandler(),  value ? 1 : 0);
    }
    
    @Override
    protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
    	if((flow.getBranchCond() && value) || (!flow.getBranchCond() && !value))
    		compiler.addInstruction(new GB(flow.getBranchLabel()));
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(Boolean.toString(value));
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
    String prettyPrintNode() {
        return "BooleanLiteral (" + value + ")";
    }
    
}
