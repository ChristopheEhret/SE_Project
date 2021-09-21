package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualIntImmediate;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.WINT;

import java.io.PrintStream;

/**
 * Integer literal
 *
 * @author gl27
 * @date 01/01/2021
 */
public class IntLiteral extends AbstractExpr {
    public int getValue() {
        return value;
    }

    private int value;

    public IntLiteral(int value) {
        this.value = value;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        LOG.debug("Verify IntLiteral");

        Type intType = compiler.getType(compiler.getOrAddSymbol("int"));
        if(intType == null)
            throw new InternalError("Le type int n'est pas défini dans le DecacCompiler.");

        this.setType(intType);
        return this.getType();
    }


    @Override
    String prettyPrintNode() {
        return "Int (" + getValue() + ")";
    }
    
//    @Override
//    protected void codeGenPrint(DecacCompiler compiler) {
//    	compiler.addInstruction(new LOAD(value, Register.getR(1)));
//        compiler.addInstruction(new WINT());
//    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	return new VirtualIntImmediate(compiler.getRegistersHandler(), value);
    }
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
    	GOp2 imm = GOp2.convertOp2(value);
    	if(imm==null) {
    		VirtualRegister vval = compiler.getRegistersHandler().getNewRegister(compiler);
    		compiler.addInstruction(new GLDR(vval.getRegister(compiler, false), new GImmediate32BitInteger(value)));
    		return vval;
    	} else {
    		return new VirtualIntImmediate(compiler.getRegistersHandler(), value);
    	}
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(Integer.toString(value));
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

}
