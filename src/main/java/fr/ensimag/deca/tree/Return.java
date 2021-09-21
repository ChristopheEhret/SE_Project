package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.VoidType;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

/** 
 *
 * @author gl27
 * @date 09/01/2021
 */
public class Return extends AbstractInst{
	private AbstractExpr expr;
	
	public Return(AbstractExpr expr) {
		super();
		this.expr=expr;
	}
	
	@Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        expr = expr.verifyRValue(compiler, localEnv, currentClass, returnType);
		if(expr.getType() instanceof VoidType)
            throw new ContextualError("The returnType cannot be a type void ", this.getLocation());
        
        if(currentClass == null)
            throw new InternalError("Class not defined ! Return is impossible");
	
    }

    @Override
    protected void codeGenInstIMA(DecacCompiler compiler) {
        // throw new UnsupportedOperationException("not yet implemented");
        // System.out.println(expr.getClass());
        VirtualDVal returnVal;
        if(expr.getType().isBoolean()) {
            Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
    		Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
    		
    		BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);
			
			this.expr.codeGenBoolIMA(compiler, flow);
            
            returnVal = compiler.getRegistersHandler().getNewRegister(compiler);
    		compiler.addInstruction(new LOAD(new ImmediateInteger(1), returnVal.getVRegister(compiler).getRegister(compiler, false)));
    		compiler.addInstruction(new BRA(endLbl));
    		compiler.addLabel(falseLbl);
    		compiler.addInstruction(new LOAD(new ImmediateInteger(0), returnVal.getVRegister(compiler).getRegister(compiler, false)));
            compiler.addLabel(endLbl);
            
        } else {
            returnVal = expr.codeGenExprIMA(compiler);
            
        }
        
        compiler.addInstruction(new LOAD(returnVal.getDVal(compiler, true), compiler.getRegisterSet().R0));
        returnVal.free(compiler);
        
        compiler.addInstruction(new BRA(compiler.getCurrentReturnLbl()));
    }

    @Override
    protected void codeGenInstGBA(DecacCompiler compiler) {
        // throw new UnsupportedOperationException("not yet implemented");
    }

    
    @Override
    public void decompile(IndentPrintStream s) {
    	s.print("return ");
    	expr.decompile(s);
    	s.print(";");
    }
    @Override
    protected
    void iterChildren(TreeFunction f) {
    	expr.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    	expr.prettyPrint(s, prefix,true);
    }

	public AbstractExpr getExpr() {
		return expr;
	}
}
