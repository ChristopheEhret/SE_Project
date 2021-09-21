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
import fr.ensimag.pseudocode.ima.instructions.BOV;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.RFLOAT;

import java.io.PrintStream;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public class ReadFloat extends AbstractReadExpr {

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        this.setType(compiler.getType(compiler.getOrAddSymbol("float")));
        return this.getType();
    }


    @Override
    public void decompile(IndentPrintStream s) {
        s.print("readFloat()");
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
        compiler.addInstruction(new RFLOAT());
        if(!compiler.getCompilerOptions().getNoCkeck())
            compiler.addInstruction(new BOV(compiler.getErrorManager().getErrorLabel("IOError")));

        VirtualRegister resReg = compiler.getRegistersHandler().getNewRegister(compiler);
        compiler.addInstruction(new LOAD(compiler.getRegisterSet().R1, resReg.getRegister(compiler, false)));
        return resReg;
    }
}
