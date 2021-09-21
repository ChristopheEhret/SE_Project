package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.ima.IMAInternalError;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * Print statement (print, println, ...).
 *
 * @author gl27
 * @date 01/01/2021
 */
public class Display extends AbstractInst {
	private AbstractExpr str;
	private AbstractExpr posy;
	private AbstractExpr posx;
  
    public Display(AbstractExpr str, AbstractExpr posy, AbstractExpr posx) {
    	Validate.notNull(str);
        Validate.notNull(posy);
        Validate.notNull(posx);
        this.str = str;
        this.posy = posy;
        this.posx = posx;
    }

    @Override
    public void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        LOG.debug("Verify Display");
        str.verifyExpr(compiler, localEnv, currentClass);
        posy.verifyExpr(compiler, localEnv, currentClass);
        posx.verifyExpr(compiler, localEnv, currentClass);
            
        if(!str.getType().isString() || !posy.getType().isInt() || !posx.getType().isInt())
        	throw new IMAInternalError("Display prend en paramètre des arguments de type (String, int, int).");
    }

    @Override
    protected void codeGenInstIMA(DecacCompiler compiler) {
        throw new IMAInternalError("Display n'est pas supporté par IMA. Utilisez print.");
    }
    
    @Override
    protected void codeGenInstGBA(DecacCompiler compiler) {
    	final VirtualRegister vlRegister = posy.codeGenExprGBA(compiler).getVRegister(compiler);
		final VirtualDVal vrDVal = posx.codeGenExprGBA(compiler);
		VirtualDVal rOperand;
		GPRegister lRegister;
		
		if(vrDVal.isRegister()) {
			rOperand = vrDVal.getVRegister(compiler);
			compiler.addInstruction(new GMOV(compiler.getRegisterSet().R1, ((VirtualRegister)rOperand).getRegister(compiler, true)));
			lRegister = vlRegister.getRegisterSwapIfNecessary(compiler, (VirtualRegister)rOperand);
		} else if(vrDVal.isRegisterOffset()) {
			throw new InternalError("VirtualRegisterOffset must have been loaded in a free register before.");
		} else { // Immediate
			compiler.addInstruction(new GMOV(compiler.getRegisterSet().R1, (GOp2)vrDVal.getDVal(compiler, true)));
			lRegister = vlRegister.getRegister(compiler, false);
			rOperand = vrDVal;
		}
		
		compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, lRegister));
		compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("setcursor")));
		
        rOperand.free(compiler);
        vlRegister.free(compiler);
        str.codeGenPrintGBA(compiler);
    }


    @Override
    public void decompile(IndentPrintStream s) {
    	s.print("display(");
		str.decompile(s);
		s.print(", ");
		posy.decompile(s);
		s.print(", ");
		posx.decompile(s);
		s.print(");");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        str.iter(f);
        posy.iter(f);
        posx.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        str.prettyPrint(s, prefix, false);
        posy.prettyPrint(s, prefix, false);
        posx.prettyPrint(s, prefix, true);
    }

}
