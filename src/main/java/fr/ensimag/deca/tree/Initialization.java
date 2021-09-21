package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.DVal;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * @author gl27
 * @date 01/01/2021
 */
public class Initialization extends AbstractInitialization {
	@Override
    public AbstractExpr getExpression() {
        return expression;
    }

    private AbstractExpr expression;

    public void setExpression(AbstractExpr expression) {
        Validate.notNull(expression);
        this.expression = expression;
    }

    public Initialization(AbstractExpr expression) {
        Validate.notNull(expression);
        this.expression = expression;
    }

    @Override
    protected void verifyInitialization(DecacCompiler compiler, Type t,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        AbstractExpr convFloat = this.expression.verifyRValue(compiler, localEnv, currentClass, t); 
        if(convFloat instanceof ConvFloat)
            this.setExpression(convFloat);
      //  else if (convFloat != null)
     //       throw new InternalError("RValue's verification returned an AbstractExpr that is not a ConvFloat");
    }


    @Override
    public void decompile(IndentPrintStream s) {
    	s.print("= ");
    	expression.decompile(s);
    }

    @Override
    protected
    void iterChildren(TreeFunction f) {
        expression.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrint(s, prefix, true);
    }
    
    @Override
    public VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	return expression.codeGenExprIMA(compiler);
    }

	@Override
	protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
		expression.codeGenBoolIMA(compiler, flow);
	}

	@Override
	protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
		return expression.codeGenExprGBA(compiler);
	}

	@Override
	protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
		expression.codeGenBoolGBA(compiler, flow);
	}
 
}
