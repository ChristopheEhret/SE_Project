package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.instructions.BNE;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public class Or extends AbstractOpBool {

    public Or(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "||";
    }

//    @Override
//    protected DVal codeGenExpr(DecacCompiler compiler, int lowerReg, int higherReg) {
//    	/* On appelle pas le parent car on ne doit pas évaluer les 2 opérandes en même temps */
//    	Label finOr = new Label("finOr.n"+compiler.getCounterManager().addOne("or"));
//        this.getLeftOperand().codeGenExpr(compiler, lowerReg, higherReg);
//        compiler.addInstruction(new BNE(finOr));
//        this.getRightOperand().codeGenExpr(compiler, lowerReg, higherReg);
//        compiler.addLabel(finOr);
//        return null;
//    }
	
    @Override
	protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
		if(flow.getBranchCond()) {
			this.getLeftOperand().codeGenBoolIMA(compiler, flow);
			this.getRightOperand().codeGenBoolIMA(compiler, flow);
		} else {
			Label finOr = new Label("finOr.n"+compiler.getCounterManager().addOne("or"));
			Label tmp = flow.getBranchLabel();
			flow.setBranchLabel(finOr);
			flow.not();
			this.getLeftOperand().codeGenBoolIMA(compiler, flow);
			flow.not();
			flow.setBranchLabel(tmp);
			this.getRightOperand().codeGenBoolIMA(compiler, flow);
			compiler.addLabel(finOr);
		}
	}
    
    @Override
	protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
		if(flow.getBranchCond()) {
			this.getLeftOperand().codeGenBoolGBA(compiler, flow);
			this.getRightOperand().codeGenBoolGBA(compiler, flow);
		} else {
			Label finOr = new Label("finOr.n"+compiler.getCounterManager().addOne("or"));
			Label tmp = flow.getBranchLabel();
			flow.setBranchLabel(finOr);
			flow.not();
			this.getLeftOperand().codeGenBoolGBA(compiler, flow);
			flow.not();
			flow.setBranchLabel(tmp);
			this.getRightOperand().codeGenBoolGBA(compiler, flow);
			compiler.addLabel(finOr);
		}
	}
	
	@Override
	protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
		Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
    	BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);
    	this.codeGenBoolIMA(compiler, flow);
		
		VirtualRegister resultReg = compiler.getRegistersHandler().getNewRegister(compiler);

    	compiler.addInstruction(new LOAD(new ImmediateInteger(1), resultReg.getRegister(compiler, false)));
		compiler.addInstruction(new BRA(endLbl));
		compiler.addLabel(falseLbl);
		compiler.addInstruction(new LOAD(new ImmediateInteger(0), resultReg.getRegister(compiler, false)));
		compiler.addLabel(endLbl);
		
    	return resultReg;
    }
}
