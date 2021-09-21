package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * Print statement (print, println, ...).
 *
 * @author gl27
 * @date 01/01/2021
 */
public abstract class AbstractPrint extends AbstractInst {

    private boolean printHex;
    private ListExpr arguments = new ListExpr();
    
    abstract String getSuffix();

    public AbstractPrint(boolean printHex, ListExpr arguments) {
        Validate.notNull(arguments);
        this.arguments = arguments;
        this.printHex = printHex;
        if(printHex)
        	for(AbstractExpr expr : arguments.getList())
        		expr.setHexMode();
    }

    public ListExpr getArguments() {
        return arguments;
    }

    @Override
    public void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        LOG.debug("Verify AbstractPrint");
        for(AbstractExpr e : arguments.getList()) {
            e.verifyExpr(compiler, localEnv, currentClass);
            
            if(!e.getType().isInt())
                if(!e.getType().isFloat())
                    if(!e.getType().isString())
                        throw new ContextualError("Cannot print anything that is not an int, a float or a string.", this.getLocation());
        }
    }

    @Override
    protected void codeGenInstIMA(DecacCompiler compiler) {
        for (AbstractExpr a : getArguments().getList()) {
            a.codeGenPrintIMA(compiler, printHex);
        }
    }
    
    @Override
    protected void codeGenInstGBA(DecacCompiler compiler) {
        for (AbstractExpr a : getArguments().getList()) {
            a.codeGenPrintGBA(compiler);
        }
    }

    private boolean getPrintHex() {
        return printHex;
    }

    @Override
    public void decompile(IndentPrintStream s) {
    	if(getPrintHex()) { 
    		s.print("print" + this.getSuffix() + "x(");
    	}
    	else {
    		s.print("print" + this.getSuffix() + "(");
    	}
		getArguments().decompile(s);
		s.print(");");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        arguments.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        arguments.prettyPrint(s, prefix, true);
    }

}
