package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BinaryExprDVals;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.codegen.VirtualRegisterOffset;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GADD;
import fr.ensimag.pseudocode.gba.instructions.GSUB;
import fr.ensimag.pseudocode.ima.instructions.BOV;
import fr.ensimag.pseudocode.ima.instructions.SUB;

/**
 * @author gl27
 * @date 01/01/2021
 */
public class Minus extends AbstractOpArith {
    public Minus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "-";
    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
        BinaryExprDVals operands = this.preCodeGenExprIMA(compiler);
		GPRegister lRegister;

		if(operands.getROperand().isRegister()) {
			lRegister = operands.getLOperand().getRegisterSwapIfNecessary(compiler, (VirtualRegister) operands.getROperand());
		} else if (operands.getROperand().isRegisterOffset()) {
			lRegister = operands.getLOperand().getRegisterSwapIfNecessary(compiler, ((VirtualRegisterOffset) operands.getROperand()).getOffsetRegister(compiler));
		} else {
			lRegister = operands.getLOperand().getRegister(compiler, false);
        }
        
    	compiler.addInstruction(new SUB(operands.getROperand().getDVal(compiler, true), lRegister));
    	if(this.getType().isFloat() && !compiler.getCompilerOptions().getNoCkeck())
            compiler.addInstruction(new BOV(compiler.getErrorManager().getErrorLabel("FloatOverflow")));
            
        operands.getROperand().free(compiler);
    	return operands.getLOperand();
    }
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
        BinaryExprDVals operands = this.preCodeGenExprGBA(compiler);
		GPRegister lRegister;

		if(operands.getROperand().isRegister()) {
			lRegister = operands.getLOperand().getRegisterSwapIfNecessary(compiler, (VirtualRegister) operands.getROperand());
		} else if(operands.getROperand().isRegisterOffset()) {
			throw new InternalError("VirtualRegisterOffset must have been loaded in a free register before.");
		} else {
			lRegister = operands.getLOperand().getRegister(compiler, false);
        }
        
    	compiler.addInstruction(new GSUB(lRegister, lRegister, new GOp2(operands.getROperand().getDVal(compiler, true))));
            
        operands.getROperand().free(compiler);
    	return operands.getLOperand();
    }
    
}
