package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BinaryExprDVals;
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
public abstract class AbstractOpCmp extends AbstractBinaryExpr {

    public AbstractOpCmp(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
    	Type left_type = this.getLeftOperand().verifyExpr(compiler,localEnv,currentClass);
    	Type right_type = this.getRightOperand().verifyExpr(compiler, localEnv, currentClass);
		String operator = this.getOperatorName();

		if(left_type.isVoid() || right_type.isVoid()) 
			throw new ContextualError("Cannot compare void types.", this.getLocation());
		if(left_type.isString() || right_type.isString()) 
			throw new ContextualError("Cannot compare string types.", this.getLocation());
		if(left_type.isClass() && right_type.isClass()) { // On peut comparer deux objets de classes A et B quelconque
			if(!(operator.equals("==") || operator.equals("!="))) {
				throw new ContextualError("The types are incompatible for a Inequality Operation",this.getLocation());    		    			
			}

		}
		else if(!(left_type.sameType(right_type))) {    		 
    		if((left_type.isInt() && right_type.isFloat()) || (left_type.isFloat() && right_type.isInt()) ) {    	   			
    			ConvFloat conversion;    			
    			if(left_type.isInt()) { 
    				conversion = new ConvFloat(this.getLeftOperand());
   			 	 	this.setLeftOperand(conversion);    		    		
    			}
    			else { // left_type = float and right_type = float
    				conversion = new ConvFloat(this.getRightOperand());
    				this.setRightOperand(conversion);    				   		
    			}
    			Type convType = conversion.verifyExpr(compiler, localEnv, currentClass);  // just for verify  		    		
    		}    		
    		else {
           		throw new ContextualError("The types are incompatible for a Comparaison Operation",this.getLocation());    		    			
    		}   		
    	}
    	else {
    		if(left_type.isBoolean()) {
    			if(!(operator.equals("==") || operator.equals("!="))) {
    				throw new ContextualError("The types are incompatible for a Inequality Operation",this.getLocation());    		    			
    			}
    		}
    		else if(!(left_type.isInt() || left_type.isFloat())) {
           		throw new ContextualError("The types are incompatible for a Comparaison Operation",this.getLocation());    		    		    			
    		}
    	}
    	Type booleanType = compiler.getType(compiler.getOrAddSymbol("boolean"));
		this.setType(booleanType);
		return booleanType;
    }


    protected BinaryExprDVals prepareCodeGenBoolIMA(DecacCompiler compiler) {
    	// met l'opérande dans un registre si nécessaire
    	return super.preCodeGenExprIMA(compiler);
    }
    
    protected VirtualDVal prepareCodeGenExprIMA(DecacCompiler compiler) {
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
    
    
    protected BinaryExprDVals prepareCodeGenBoolGBA(DecacCompiler compiler) {
    	// met l'opérande dans un registre si nécessaire
    	return super.preCodeGenExprGBA(compiler);
    }
    
    protected VirtualDVal prepareCodeGenExprGBA(DecacCompiler compiler) {
    	Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
		Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
    	BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);
    	this.codeGenBoolGBA(compiler, flow);
		
		VirtualRegister resultReg = compiler.getRegistersHandler().getNewRegister(compiler);

    	compiler.addInstruction(new GMOV(resultReg.getRegister(compiler, false), GOp2.convertOp2(1)));
		compiler.addInstruction(new GB(endLbl));
		compiler.addLabel(falseLbl);
		compiler.addInstruction(new GMOV(resultReg.getRegister(compiler, false), GOp2.convertOp2(0)));
		compiler.addLabel(endLbl);
		
    	return resultReg;
    }
}
