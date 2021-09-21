package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.ima.instructions.BRA;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

/**
 * Full if/else if/else statement.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class IfThenElse extends AbstractInst {
    
	private static final Logger LOG = Logger.getLogger(IfThenElse.class);
    private final AbstractExpr condition; 
    private final ListInst thenBranch;
    private ListInst elseBranch;

    public IfThenElse(AbstractExpr condition, ListInst thenBranch, ListInst elseBranch) {
        Validate.notNull(condition);
        Validate.notNull(thenBranch);
        Validate.notNull(elseBranch);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }
    
    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        condition.verifyCondition(compiler, localEnv, currentClass);
        
        thenBranch.verifyListInst(compiler, localEnv, currentClass, returnType);
        elseBranch.verifyListInst(compiler, localEnv, currentClass, returnType);
    }

    @Override
    protected void codeGenInstIMA(DecacCompiler compiler) {
    	LOG.trace("CodeGen: IfThenElse");
        Label finLbl = new Label("finIf.n"+Integer.toString(compiler.getCounterManager().addOne("if")));
        Label elseLbl = new Label("elseIf.n"+Integer.toString(compiler.getCounterManager().last("if")));
    	
        BoolCtrlFlow flow = new BoolCtrlFlow(false, elseBranch.isEmpty() ? finLbl : elseLbl);
    	condition.codeGenBoolIMA(compiler, flow);
    	
    	thenBranch.codeGenListInstIMA(compiler);
    	if(!elseBranch.isEmpty()) {
    		compiler.addInstruction(new BRA(finLbl));
    		compiler.addLabel(elseLbl);
    		elseBranch.codeGenListInstIMA(compiler);
    	}        
        compiler.addLabel(finLbl);
    }
    
    @Override
    protected void codeGenInstGBA(DecacCompiler compiler) {
    	LOG.trace("CodeGen: IfThenElse");
        Label finLbl = new Label("finIf.n"+Integer.toString(compiler.getCounterManager().addOne("if")));
        Label elseLbl = new Label("elseIf.n"+Integer.toString(compiler.getCounterManager().last("if")));
    	
        BoolCtrlFlow flow = new BoolCtrlFlow(false, elseBranch.isEmpty() ? finLbl : elseLbl);
    	condition.codeGenBoolGBA(compiler, flow);
    	
    	thenBranch.codeGenListInstGBA(compiler);
    	if(!elseBranch.isEmpty()) {
    		compiler.addInstruction(new GB(finLbl));
    		compiler.addLabel(elseLbl);
    		elseBranch.codeGenListInstGBA(compiler);
    	}        
        compiler.addLabel(finLbl);
    }

    @Override
    public void decompile(IndentPrintStream s) {
    	s.print("if (");
    	condition.decompile(s);
    	s.println(") { ");
    	s.indent();
    	thenBranch.decompile(s);
    	s.unindent();
    	if(!elseBranch.isEmpty() ) {
    		s.println("} else	{");
    		s.indent();
        	elseBranch.decompile(s);
    		s.unindent();
        	s.print("}");	
    	}     	
    	else {
    		s.println("} else	{");
        	s.print("}");	

    	}
   
   
    }

    @Override
    protected
    void iterChildren(TreeFunction f) {
        condition.iter(f);
        thenBranch.iter(f);
        elseBranch.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        condition.prettyPrint(s, prefix, false);
        thenBranch.prettyPrint(s, prefix, false);
        elseBranch.prettyPrint(s, prefix, true);
    }

	public ListInst getElseBranch() {
		return elseBranch;
	}

	public void setElseBranch(ListInst elseBranch) {
		this.elseBranch = elseBranch;
	}
	
    
}
