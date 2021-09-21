package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.VariableDefinition;
import fr.ensimag.deca.context.VoidType;
import fr.ensimag.deca.context.EnvironmentExp.DoubleDefException;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GSTR;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.STORE;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * @author gl27
 * @date 01/01/2021
 */
public class DeclVar extends AbstractDeclVar {

    
    final private AbstractIdentifier type;
    final private AbstractIdentifier varName;
    final private AbstractInitialization initialization;

    public DeclVar(AbstractIdentifier type, AbstractIdentifier varName, AbstractInitialization initialization) {
        Validate.notNull(type);
        Validate.notNull(varName);
        Validate.notNull(initialization);
        this.type = type;
        this.varName = varName;
        this.initialization = initialization;
    }

    @Override
    protected void verifyDeclVar(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        Type varType = type.verifyType(compiler);
        if(varType instanceof VoidType)
            throw new ContextualError("Cannot declare a variable of type void", this.getLocation());
        
        if(currentClass == null)
            throw new InternalError("Variable Declaration in a class not yet implemented");

        initialization.verifyInitialization(compiler, varType, localEnv, currentClass);

        VariableDefinition variableDefinition = new VariableDefinition(varType, this.getLocation());
        
        // Symbol varSymbol = compiler.getOrAddSymbol(varName.getName().getName());
        try {
            localEnv.declare(varName.getName(), variableDefinition);
            // localEnv.declare(varName.getName(), variableDefinition);
        } catch (DoubleDefException e) {
            throw new ContextualError("Variable " + varName.getName() + " was already defined", this.getLocation());
        }
        varName.setDefinition(variableDefinition);
    }
    
    @Override
    public void codeGenDeclIMA(DecacCompiler compiler, VirtualDAddr operand) {
        if(operand.isRegisterOffset())
            // Impact massif sur le code
            throw new InternalError("Une variable ne devrait pas avoir une addresse basée sur un offset dynamique. A vérifier et à prendre en compte si ca peut être le cas");

    	varName.getVariableDefinition().setOperand(operand);
    	if(initialization instanceof NoInitialization)
    		return;
    	
    	if(type.getType().isBoolean()) {
    		Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
    		Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
    		BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);

    		initialization.codeGenBoolIMA(compiler, flow);
    		compiler.addInstruction(new LOAD(new ImmediateInteger(1), compiler.getRegisterSet().getR(0)));
    		compiler.addInstruction(new BRA(endLbl));
    		compiler.addLabel(falseLbl);
    		compiler.addInstruction(new LOAD(new ImmediateInteger(0), compiler.getRegisterSet().getR(0)));
    		compiler.addLabel(endLbl);
            compiler.addInstruction(new STORE(compiler.getRegisterSet().getR(0), operand.getDAddr(compiler, false)));
    	} else {
            VirtualDVal result = initialization.codeGenExprIMA(compiler);
        	assert(result!=null);
            
            compiler.addInstruction(new STORE(result.getVRegister(compiler).getRegister(compiler, true), operand.getDAddr(compiler, false)));
            result.free(compiler);
    	}	
    }
    
    @Override
    public void codeGenDeclGBA(DecacCompiler compiler, VirtualDAddr operand) {
        if(operand.isRegisterOffset())
            // Impact massif sur le code
            throw new InternalError("Une variable ne devrait pas avoir une addresse basée sur un offset dynamique. A vérifier et à prendre en compte si ca peut être le cas");

    	varName.getVariableDefinition().setOperand(operand);
    	if(initialization instanceof NoInitialization)
    		return;
    	
    	if(type.getType().isBoolean()) {
    		Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
    		Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
    		BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);

    		initialization.codeGenBoolGBA(compiler, flow);
    		compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, GOp2.convertOp2(1)));
    		compiler.addInstruction(new GB(endLbl));
    		compiler.addLabel(falseLbl);
    		compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, GOp2.convertOp2(0)));
    		compiler.addLabel(endLbl);
            compiler.addInstruction(new GSTR(compiler.getRegisterSet().R0, operand.getDAddr(compiler, false)));
    	} else {
            VirtualDVal result = initialization.codeGenExprGBA(compiler);
        	assert(result!=null);
            
            compiler.addInstruction(new GSTR(result.getVRegister(compiler).getRegister(compiler, true), operand.getDAddr(compiler, false)));
            result.free(compiler);
    	}	
    }

    
    @Override
    public void decompile(IndentPrintStream s) {
    	type.decompile(s);
    	s.print(" ");
    	varName.decompile(s);
    	initialization.decompile(s);
    	s.print(";");
    }

    @Override
    protected
    void iterChildren(TreeFunction f) {
        type.iter(f);
        varName.iter(f);
        initialization.iter(f);
    }
    
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        varName.prettyPrint(s, prefix, false);
        initialization.prettyPrint(s, prefix, true);
    }
}
