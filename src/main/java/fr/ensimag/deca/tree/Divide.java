package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BinaryExprDVals;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.codegen.VirtualRegisterOffset;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GImmediate24BitInteger;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GPOP;
import fr.ensimag.pseudocode.gba.instructions.GPUSH;
import fr.ensimag.pseudocode.gba.instructions.GSWI;
import fr.ensimag.pseudocode.ima.instructions.BOV;
import fr.ensimag.pseudocode.ima.instructions.DIV;
import fr.ensimag.pseudocode.ima.instructions.QUO;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public class Divide extends AbstractOpArith {
    public Divide(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "/";
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

    	if(this.getType().isFloat()) {
    		compiler.addInstruction(new DIV(operands.getROperand().getDVal(compiler, true), lRegister));
    		if(!compiler.getCompilerOptions().getNoCkeck())
    			compiler.addInstruction(new BOV(compiler.getErrorManager().getErrorLabel("FloatOverflow")));
    	} else {
    		compiler.addInstruction(new QUO(operands.getROperand().getDVal(compiler, true), lRegister));
	    	if(!compiler.getCompilerOptions().getNoCkeck())
				compiler.addInstruction(new BOV(compiler.getErrorManager().getErrorLabel("DivideByZero")));
		}

		operands.getROperand().free(compiler);
    	return operands.getLOperand();
    }
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
        BinaryExprDVals operands = this.preCodeGenExprGBA(compiler);
		GPRegister lRegister;
		VirtualDVal rOperand;
		
		if(operands.getROperand().isRegister()) {
			rOperand = operands.getROperand().getVRegister(compiler);
			compiler.addInstruction(new GMOV(compiler.getRegisterSet().R1, ((VirtualRegister)rOperand).getRegister(compiler, true)));
			lRegister = operands.getLOperand().getRegisterSwapIfNecessary(compiler, (VirtualRegister)rOperand);
		} else if(operands.getROperand().isRegisterOffset()) {
			throw new InternalError("VirtualRegisterOffset must have been loaded in a free register before.");
		} else { // Immediate
			compiler.addInstruction(new GMOV(compiler.getRegisterSet().R1, (GOp2)operands.getROperand().getDVal(compiler, true)));
			lRegister = operands.getLOperand().getRegister(compiler, false);
			rOperand = operands.getROperand();
		}
		
		if(!compiler.getCompilerOptions().getNoCkeck())
			compiler.addInstruction(new GB(compiler.getErrorManager().getErrorLabel("DivideByZero"), GCondition.EQ));
		compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, lRegister));
		compiler.addInstruction(new GPUSH(compiler.getRegisterSet().getR(3)));
		compiler.addInstruction(new GSWI(new GImmediate24BitInteger(0x060000))); // DIV/MOD
		compiler.addInstruction(new GPOP(compiler.getRegisterSet().getR(3)));
		compiler.addInstruction(new GMOV(lRegister, compiler.getRegisterSet().R0));
		
        rOperand.free(compiler);
    	return operands.getLOperand();
    }

}
