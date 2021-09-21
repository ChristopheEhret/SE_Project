package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.instructions.FLOAT;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.codegen.VirtualRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.IntType;

/**
 * Conversion of an int into a float. Used for implicit conversions.
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class ConvFloat extends AbstractUnaryExpr {
    public ConvFloat(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) {

        if(!(this.getOperand().getType().isInt()))
            throw new InternalError("ConvFloat does not have an IntType expression");
        
            
        this.setType(compiler.getType(compiler.getOrAddSymbol("float")));
        return this.getType();
    }


    @Override
    protected String getOperatorName() {
        return "/* conv float */";
    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
        VirtualDVal res = super.codeGenExprIMA(compiler);
        VirtualRegister resRegister;

        if(res.isRegister()) {
            resRegister = res.getVRegister(compiler);
            compiler.addInstruction(new FLOAT(resRegister.getDVal(compiler, false), resRegister.getRegister(compiler, false)));
            return resRegister;
        } else if (res.isRegisterOffset()) {
            resRegister = ((VirtualRegisterOffset) res).getOffsetRegister(compiler);
            compiler.addInstruction(new FLOAT(res.getDVal(compiler, false), resRegister.getRegister(compiler, false)));
            return resRegister;
        } else {
            resRegister = compiler.getRegistersHandler().getNewRegister(compiler);
            compiler.addInstruction(new FLOAT(res.getDVal(compiler, true), resRegister.getRegister(compiler, false)));
            res.free(compiler);
            return resRegister;
        }
    }

}
