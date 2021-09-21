package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.gba.GLabelOperand;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.ima.ImmediateString;
import fr.ensimag.pseudocode.ima.instructions.WSTR;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * String literal
 *
 * @author gl27
 * @date 01/01/2021
 */
public class StringLiteral extends AbstractStringLiteral {

    @Override
    public String getValue() {
        return value;
    }

    private String value;

    public StringLiteral(String value) {
        Validate.notNull(value);
        this.value = value;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        LOG.debug("Verify StringLiteral");

        Type stringType = compiler.getType(compiler.getOrAddSymbol("string"));
        if(stringType == null)
                throw new InternalError("Le type string n'est pas défini dans le DecacCompiler.");

        this.setType(stringType);
        return this.getType();
    }

    @Override
    protected void codeGenPrintIMA(DecacCompiler compiler, boolean hex) {
        compiler.addInstruction(new WSTR(new ImmediateString(value)));
    }
    
    @Override
    protected void codeGenPrintGBA(DecacCompiler compiler) {
        compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GLabelOperand(compiler.getDataManager().addString(value))));
        compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("printstr")));
    }

    @Override
    public void decompile(IndentPrintStream s) {
    	s.print("\"" + value + "\"");  	
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
        return "StringLiteral (" + value + ")";
    }

}
