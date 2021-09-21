package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.ima.IMAInternalError;
import fr.ensimag.pseudocode.ima.instructions.BOV;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.RINT;

import java.io.PrintStream;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GetKey extends AbstractReadExpr {

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        this.setType(compiler.getType(compiler.getOrAddSymbol("int")));
        return this.getType();
    }


    @Override
    public void decompile(IndentPrintStream s) {
        s.print("getKey()");
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
        throw new IMAInternalError("La fonction getKey n'est pas supportée par IMA.");
    }
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
    	compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("getkey")));
        VirtualRegister resReg = compiler.getRegistersHandler().getNewRegister(compiler);
        compiler.addInstruction(new GMOV(resReg.getRegister(compiler, false), compiler.getRegisterSet().R1));
        return resReg;
    }
}