package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualFloatImmediate;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.ima.ImmediateFloat;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.WFLOAT;
import fr.ensimag.pseudocode.ima.instructions.WFLOATX;

import java.io.PrintStream;

import org.apache.commons.lang.Validate;

/**
 * Single precision, floating-point literal
 *
 * @author gl27
 * @date 01/01/2021
 */
public class FloatLiteral extends AbstractExpr {

    public float getValue() {
        return value;
    }

    private float value;

    public FloatLiteral(float value) {
        Validate.isTrue(!Float.isInfinite(value),
                "literal values cannot be infinite");
        Validate.isTrue(!Float.isNaN(value),
                "literal values cannot be NaN");
        this.value = value;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        LOG.debug("Verify FloatLiteral");
        Type floatType = compiler.getType(compiler.getOrAddSymbol("float"));
        if(floatType == null)
                throw new InternalError("Le type float n'est pas défini dans le DecacCompiler.");

        this.setType(floatType);
        return this.getType();
    }
    
    
//    @Override
//    protected void codeGenPrint(DecacCompiler compiler) {
//    	compiler.addInstruction(new LOAD(value, Register.getR(1)));
//    	if(hexMode)
//    		compiler.addInstruction(new WFLOATX());
//    	else
//    		compiler.addInstruction(new WFLOAT());
//    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	return new VirtualFloatImmediate(compiler.getRegistersHandler(), value);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(java.lang.Float.toHexString(value));
    }

    @Override
    String prettyPrintNode() {
        return "Float (" + getValue() + ")";
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
