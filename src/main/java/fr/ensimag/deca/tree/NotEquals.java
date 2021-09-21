package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BinaryExprDVals;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GCMP;
import fr.ensimag.pseudocode.ima.instructions.BEQ;
import fr.ensimag.pseudocode.ima.instructions.BNE;
import fr.ensimag.pseudocode.ima.instructions.CMP;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public class NotEquals extends AbstractOpExactCmp {

    public NotEquals(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "!=";
    }
    
    @Override
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
    	BinaryExprDVals operands = super.prepareCodeGenBoolIMA(compiler);
    	compiler.addInstruction(new CMP(operands.getROperand().getDVal(compiler, false), operands.getLOperand().getRegister(compiler, true)));
        operands.getROperand().free(compiler);
        operands.getLOperand().free(compiler);

        if(flow.getBranchCond())
    		compiler.addInstruction(new BNE(flow.getBranchLabel()));
    	else
            compiler.addInstruction(new BEQ(flow.getBranchLabel()));
    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	return prepareCodeGenExprIMA(compiler);
    }
    
    
    @Override
    protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
    	BinaryExprDVals operands = super.prepareCodeGenBoolGBA(compiler);
    	compiler.addInstruction(new GCMP(operands.getLOperand().getRegister(compiler, true), new GOp2(operands.getROperand().getDVal(compiler, false))));
        operands.getROperand().free(compiler);
        operands.getLOperand().free(compiler);

        if(flow.getBranchCond())
    		compiler.addInstruction(new GB(flow.getBranchLabel(), GCondition.NE));
    	else
            compiler.addInstruction(new GB(flow.getBranchLabel(), GCondition.EQ));
            
    }
    
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
    	return prepareCodeGenExprGBA(compiler);
    }

}
