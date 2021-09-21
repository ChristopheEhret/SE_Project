package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public abstract class AbstractOpBool extends AbstractBinaryExpr {

    public AbstractOpBool(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
        ClassDefinition currentClass) throws ContextualError {
    	Type left_type = this.getLeftOperand().verifyExpr(compiler,localEnv,currentClass);
    	Type right_type = this.getRightOperand().verifyExpr(compiler, localEnv, currentClass);
    	if(left_type.isBoolean() && right_type.isBoolean()) {
    		this.setType(left_type);
    		return left_type;
    	}
    	else {
       		throw new ContextualError("The types are incompatible for a Boolean Operation",this.getLocation());    		
    	}    	
    }
    
}
