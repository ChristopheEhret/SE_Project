package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GNEG;
import fr.ensimag.pseudocode.ima.instructions.OPP;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * @author gl27
 * @date 01/01/2021
 */
public class UnaryMinus extends AbstractUnaryExpr {

    public UnaryMinus(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        Type opType = this.getOperand().verifyExpr(compiler, localEnv, currentClass);
        if(!(opType.isInt() || opType.isFloat()))
                throw new ContextualError("Unary minus must be applied to an expression of type int or float (current type : " + opType.getName() + ").", this.getLocation());

        this.setType(opType);
        return opType;
    }


    @Override
    protected String getOperatorName() {
        return "-";
    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
        VirtualDVal operand = super.codeGenExprIMA(compiler);
        if(operand.isRegister()) {
            VirtualRegister opReg = operand.getVRegister(compiler);
            compiler.addInstruction(new OPP(opReg.getRegister(compiler, false), opReg.getRegister(compiler, false)));
            return opReg;
        } else {
            VirtualRegister resReg = compiler.getRegistersHandler().getNewRegister(compiler);
            compiler.addInstruction(new OPP(operand.getDVal(compiler, true), resReg.getRegister(compiler, false)));
            operand.free(compiler);
            return resReg;
        }
    }
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
        VirtualDVal operand = super.codeGenExprGBA(compiler);
        if(operand.isRegister()) {
            VirtualRegister opReg = operand.getVRegister(compiler);
            compiler.addInstruction(new GNEG(opReg.getRegister(compiler, false), opReg.getRegister(compiler, false)));
            return opReg;
        } else {
            VirtualRegister resReg = compiler.getRegistersHandler().getNewRegister(compiler);
            compiler.addInstruction(new GMOV(resReg.getRegister(compiler, false), new GOp2(operand.getDVal(compiler, true))));
            compiler.addInstruction(new GNEG(resReg.getRegister(compiler, false), resReg.getRegister(compiler, false)));
            operand.free(compiler);
            return resReg;
        }
    }
}
