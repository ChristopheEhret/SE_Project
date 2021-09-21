package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Arithmetic binary operations (+, -, /, ...)
 * 
 * @author gl27
 * @date 01/01/2021
 */
public abstract class AbstractOpArith extends AbstractBinaryExpr {

    public AbstractOpArith(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
    	Type left_type = this.getLeftOperand().verifyExpr(compiler,localEnv,currentClass);
    	Type right_type = this.getRightOperand().verifyExpr(compiler, localEnv, currentClass);
    	
    	if( (left_type.isInt() && right_type.isFloat() ) || (left_type.isFloat() && right_type.isInt() ) ) {
    		Type convType;
    		ConvFloat conversion;
    		if(left_type.isFloat()) {
    			 conversion = new ConvFloat(this.getRightOperand());
    			 convType=conversion.verifyExpr(compiler, localEnv, currentClass);    			    				    			 
    			 this.setRightOperand(conversion);
    		}
    		else {
    			 conversion = new ConvFloat(this.getLeftOperand());
    			 convType=conversion.verifyExpr(compiler, localEnv, currentClass);    			    				
    			 this.setLeftOperand(conversion);    			
    		}
		    this.setType(convType);
		    return convType;    		
   		}
    	else if( (left_type.isInt() && right_type.isInt()) ||(left_type.isFloat() && right_type.isFloat())) {
    		this.setType(left_type);
    		return left_type; // or right_type 
    	}
    	else {
       		throw new ContextualError("The types are incompatible for a Arithmetic Operation",this.getLocation());
    	}    	
    }
    
}
