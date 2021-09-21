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

/**
 *
 * @author gl27
 * @date 01/01/2021
 */
public class While extends AbstractInst {
    private AbstractExpr condition;
    private ListInst body;

    public AbstractExpr getCondition() {
        return condition;
    }

    public ListInst getBody() {
        return body;
    }

    public While(AbstractExpr condition, ListInst body) {
        Validate.notNull(condition);
        Validate.notNull(body);
        this.condition = condition;
        this.body = body;
    }

    @Override
    protected void codeGenInstIMA(DecacCompiler compiler) {
    	LOG.trace("CodeGen: IfThenElse");
    	Label debLbl = new Label("debWhile.n"+Integer.toString(compiler.getCounterManager().addOne("while")));
    	Label condLbl = new Label("condWhile.n"+Integer.toString(compiler.getCounterManager().last("while")));
    	
    	compiler.addInstruction(new BRA(condLbl));
    	compiler.addLabel(debLbl);
    	body.codeGenListInstIMA(compiler);
    	compiler.addLabel(condLbl);
    	
    	BoolCtrlFlow flow = new BoolCtrlFlow(true, debLbl);
    	condition.codeGenBoolIMA(compiler, flow);
    }
    
    @Override
    protected void codeGenInstGBA(DecacCompiler compiler) {
    	LOG.trace("CodeGen: IfThenElse");
    	Label debLbl = new Label("debWhile.n"+Integer.toString(compiler.getCounterManager().addOne("while")));
    	Label condLbl = new Label("condWhile.n"+Integer.toString(compiler.getCounterManager().last("while")));
    	
    	compiler.addInstruction(new GB(condLbl));
    	compiler.addLabel(debLbl);
    	body.codeGenListInstGBA(compiler);
    	compiler.addLabel(condLbl);
    	
    	BoolCtrlFlow flow = new BoolCtrlFlow(true, debLbl);
    	condition.codeGenBoolGBA(compiler, flow);
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
                
        condition.verifyCondition(compiler, localEnv, currentClass);
        body.verifyListInst(compiler, localEnv, currentClass, returnType);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("while (");
        getCondition().decompile(s);
        s.println(") {");
        s.indent();
        getBody().decompile(s);
        s.unindent();
        s.print("}");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        condition.iter(f);
        body.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        condition.prettyPrint(s, prefix, false);
        body.prettyPrint(s, prefix, true);
    }

}
