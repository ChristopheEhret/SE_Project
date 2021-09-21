package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Operand;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GCMP;
import fr.ensimag.pseudocode.gba.instructions.GEOR;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.instructions.CMP;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.SEQ;
import fr.ensimag.pseudocode.ima.instructions.SNE;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public class Not extends AbstractUnaryExpr {

    public Not(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        Type opType = this.getOperand().verifyExpr(compiler, localEnv, currentClass);
        if(!opType.isBoolean()) 
            throw new ContextualError("Not operator must be applied on a boolean expression (current type : " + opType.getName() + ")", this.getLocation());
            
        this.setType(opType);
        return opType;
    }


    @Override
    protected String getOperatorName() {
        return "!";
    }
    
    @Override
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
    	flow.not();
    	this.getOperand().codeGenBoolIMA(compiler, flow);
        flow.not();
    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	VirtualRegister operandReg = this.getOperand().codeGenExprIMA(compiler).getVRegister(compiler);
    	
        compiler.addInstruction(new CMP(new ImmediateInteger(0), operandReg.getRegister(compiler, true)));
        operandReg.free(compiler);
        
        VirtualRegister resReg = compiler.getRegistersHandler().getNewRegister(compiler);
    	compiler.addInstruction(new SEQ(resReg.getRegister(compiler, false)));
    	return resReg;
    }
    
    
    @Override
    protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
    	flow.not();
        this.getOperand().codeGenBoolGBA(compiler, flow);
        flow.not();
    }
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
    	// we take advantage of the fact that the value of operand is either 0 or 1
    	final VirtualRegister operandReg = this.getOperand().codeGenExprGBA(compiler).getVRegister(compiler);
    	VirtualDVal vreg;
    	
    	if(operandReg.isRegister()) {
    		vreg = operandReg;
    		final GPRegister reg = operandReg.getRegister(compiler, false);
    		compiler.addInstruction(new GEOR(reg, reg, GOp2.convertOp2(1)));
    	} else { // IMMEDIATE
    		vreg = compiler.getRegistersHandler().getNewRegister(compiler);
    		final GPRegister reg = vreg.getVRegister(compiler).getRegister(compiler, false);
    		compiler.addInstruction(new GEOR(reg, reg, GOp2.convertOp2(1)));
    		operandReg.free(compiler);
    	}
        
    	return vreg;
    }
    
}
