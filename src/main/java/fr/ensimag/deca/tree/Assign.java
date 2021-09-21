package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GCMP;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GSTR;
import fr.ensimag.pseudocode.ima.instructions.BEQ;
import fr.ensimag.pseudocode.ima.instructions.BNE;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.CMP;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.POP;
import fr.ensimag.pseudocode.ima.instructions.PUSH;
import fr.ensimag.pseudocode.ima.instructions.STORE;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.codegen.VirtualRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Assignment, i.e. lvalue = expr.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class Assign extends AbstractBinaryExpr {

    @Override
    public AbstractLValue getLeftOperand() {
        // The cast succeeds by construction, as the leftOperand has been set
        // as an AbstractLValue by the constructor.
        return (AbstractLValue)super.getLeftOperand();
    }

    public Assign(AbstractLValue leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {

        Type lType = this.getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        AbstractExpr convFloat = this.getRightOperand().verifyRValue(compiler, localEnv, currentClass, lType);

        if(convFloat instanceof ConvFloat)
            this.setRightOperand(convFloat);
      //  else if (convFloat != null)
     //       throw new InternalError("RValue's verification returned an AbstractExpr that is not a ConvFloat");
        this.setType(lType);
        return lType;
    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
		// compiler.getSOVH().useRegisterUntil(lowerReg);
		VirtualDVal result;

    	if(this.getLeftOperand().getType().isBoolean()) {

			Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
    		Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
    		
    		BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);
			
			
			this.getRightOperand().codeGenBoolIMA(compiler, flow);
			
			result = compiler.getRegistersHandler().getNewRegister(compiler);
    		compiler.addInstruction(new LOAD(new ImmediateInteger(1), result.getVRegister(compiler).getRegister(compiler, false)));
    		compiler.addInstruction(new BRA(endLbl));
    		compiler.addLabel(falseLbl);
    		compiler.addInstruction(new LOAD(new ImmediateInteger(0), result.getVRegister(compiler).getRegister(compiler, false)));
			compiler.addLabel(endLbl);

			// // TODOC : voir une facon de factoriser ce genre de code (push/pop)
			// if(lowerReg == higherReg) {
			// 	compiler.getSOVH().push();
			// 	compiler.addInstruction(new PUSH(GPRegister.getR(lowerReg)));
			// 	DAddr loc = getLeftOperand().codeGenAssign(compiler, lowerReg, higherReg);
			// 	compiler.getSOVH().pop();
			// 	compiler.addInstruction(new POP(GPRegister.R0));
			// 	compiler.addInstruction(new STORE(GPRegister.R0, loc));
			// } else {
			// 	compiler.addInstruction(new STORE(GPRegister.getR(lowerReg), getLeftOperand().codeGenAssign(compiler, lowerReg + 1, higherReg)));
			// }

		} else {
	    	result = this.getRightOperand().codeGenExprIMA(compiler).getVRegister(compiler);
			// DAddr loc = this.getLeftOperand().codeGenAssign(compiler, lowerReg + 1, higherReg);
	    	// compiler.addInstruction(new STORE(reg, loc));
		}

		VirtualRegister resultvRegister = result.getVRegister(compiler);
		VirtualDAddr vLoc = getLeftOperand().codeGenAssign(compiler);

		GPRegister resultRegister;
		if(vLoc.isRegisterOffset())
			resultRegister = resultvRegister.getRegisterSwapIfNecessary(compiler, ((VirtualRegisterOffset)vLoc).getOffsetRegister(compiler));
		else 
			resultRegister = resultvRegister.getRegister(compiler, false);


		compiler.addInstruction(new STORE(resultRegister, vLoc.getDAddr(compiler, true)));
		
		vLoc.free(compiler);
		return result;
    }
	
    @Override
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
    	VirtualRegister exprReg = this.codeGenExprIMA(compiler).getVRegister(compiler);
    	compiler.addInstruction(new CMP(new ImmediateInteger(0), exprReg.getRegister(compiler, true)));
		exprReg.free(compiler);
		
    	if(flow.getBranchCond())
    		compiler.addInstruction(new BNE(flow.getBranchLabel()));
    	else
			compiler.addInstruction(new BEQ(flow.getBranchLabel()));
    }
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
		VirtualDVal result;

    	if(this.getLeftOperand().getType().isBoolean()) {

			Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
    		Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
    		
    		BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);
			
			this.getRightOperand().codeGenBoolGBA(compiler, flow);
			
			result = compiler.getRegistersHandler().getNewRegister(compiler);
    		compiler.addInstruction(new GMOV(result.getVRegister(compiler).getRegister(compiler, false), GOp2.convertOp2(1)));
    		compiler.addInstruction(new GB(endLbl));
    		compiler.addLabel(falseLbl);
    		compiler.addInstruction(new GMOV(result.getVRegister(compiler).getRegister(compiler, false), GOp2.convertOp2(0)));
			compiler.addLabel(endLbl);
		} else {
	    	result = this.getRightOperand().codeGenExprGBA(compiler).getVRegister(compiler);
		}

		VirtualRegister resultvRegister = result.getVRegister(compiler);
		VirtualDAddr vLoc = getLeftOperand().codeGenAssign(compiler);

		GPRegister resultRegister;
		if(vLoc.isRegisterOffset())
			resultRegister = resultvRegister.getRegisterSwapIfNecessary(compiler, ((VirtualRegisterOffset)vLoc).getOffsetRegister(compiler));
		else 
			resultRegister = resultvRegister.getRegister(compiler, false);


		compiler.addInstruction(new GSTR(resultRegister, vLoc.getDAddr(compiler, true)));
		
		vLoc.free(compiler);
		return result;
    }
    
    @Override
    protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
    	VirtualRegister exprReg = this.codeGenExprGBA(compiler).getVRegister(compiler);
    	compiler.addInstruction(new GCMP(exprReg.getRegister(compiler, true), GOp2.convertOp2(0)));
		exprReg.free(compiler);
		
    	if(flow.getBranchCond())
    		compiler.addInstruction(new GB(flow.getBranchLabel(), GCondition.NE));
    	else
			compiler.addInstruction(new GB(flow.getBranchLabel(), GCondition.EQ));
    }


    @Override
    protected String getOperatorName() {
        return "=";
    }

}
