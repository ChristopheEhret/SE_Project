package fr.ensimag.deca.tree;

import java.io.PrintStream;
import java.rmi.server.ExportException;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.NullOperand;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.ADDSP;
import fr.ensimag.pseudocode.ima.instructions.BEQ;
import fr.ensimag.pseudocode.ima.instructions.BNE;
import fr.ensimag.pseudocode.ima.instructions.BSR;
import fr.ensimag.pseudocode.ima.instructions.CMP;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.STORE;
import fr.ensimag.pseudocode.ima.instructions.SUBSP;

import java.util.List;


public class MethodCall extends AbstractExpr {
	
	private AbstractExpr expr; 
	private AbstractIdentifier methodIdentifier; 
	private ListExpr parameters;
	
	public MethodCall(AbstractExpr expr,AbstractIdentifier methodIdentifier,ListExpr parameters) {
		this.expr=expr;
		this.methodIdentifier=methodIdentifier;
		this.parameters=parameters;
	}	
	@Override
	public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass)
			throws ContextualError {
		// Règle 3.71
		Type class2Type = expr.verifyExpr(compiler, localEnv, currentClass);
		if(!class2Type.isClass()) {
			throw new ContextualError("The object on which the method is called is not a class",this.getLocation());			
		}
		
		ClassType classType2 = (ClassType) class2Type;
		EnvironmentExp envExp2 = classType2.getDefinition().getMembers();
		ExpDefinition expDef = envExp2.get(methodIdentifier.getName()); 
		if(expDef == null) {
			throw new ContextualError("The method " + methodIdentifier.getName().getName() + " is not defined for the class "
					+ class2Type.getName().getName() ,this.getLocation());			
		}
		if(!expDef.isMethod()) {
			throw new ContextualError("The method " + methodIdentifier.getName().getName() + " is not defined for the class "
					+ class2Type.getName().getName() ,this.getLocation());	
		}
		
		MethodDefinition methodDef = (MethodDefinition) expDef;
		Signature sig = methodDef.getSignature();
		int sigSize = sig.size();
		int paramSize = parameters.size();
		if(paramSize> sigSize) {
			throw new ContextualError("Too many parameters have been given. Expected : " + sig.size() 
				+ "  given : "	+ parameters.size(),parameters.getLocation());			
		}
		if(paramSize < sigSize) {
			throw new ContextualError("Too few parameters have been given. Expected : " + sig.size() 
			+ "  given : "	+ parameters.size(),this.getLocation());
		}
		List<AbstractExpr> paramList = parameters.getList();
		for(int i = 0; i< sigSize ; i++) {
			AbstractExpr expr = paramList.get(i).verifyRValue(compiler, localEnv, currentClass, sig.paramNumber(i));
			parameters.set(i, expr);
		}
		this.setType(methodDef.getType());
		methodIdentifier.setDefinition(methodDef);
		return methodDef.getType();
	}
	@Override
	public void decompile(IndentPrintStream s) {
		if(!expr.isImplicit()) {
			expr.decompile(s);
			s.print('.');
		}
		methodIdentifier.decompile(s);
		s.print("(");
		parameters.decompile(s);
		s.print(")");
	}

	@Override
	protected void prettyPrintChildren(PrintStream s, String prefix) {
		expr.prettyPrint(s,prefix,false);
		methodIdentifier.prettyPrint(s,prefix,false);
		parameters.prettyPrint(s,prefix,true);
	}

	@Override
	protected void iterChildren(TreeFunction f) {
		expr.iter(f);
		methodIdentifier.iter(f);
		parameters.iter(f);
		
	}

	public AbstractExpr getExpr() {
		return expr;
	}

	public AbstractIdentifier getMethodIdentifier() {
		return methodIdentifier;
	}

	public ListExpr getParameters() {
		return parameters;
	}	

	@Override
	public void codeGenInstIMA(DecacCompiler compiler) {
		VirtualRegister exprReg = expr.codeGenExprIMA(compiler).getVRegister(compiler);
		
		compiler.addInstruction(new ADDSP(parameters.size() + 1));
		compiler.getRegistersHandler().pushInStack(parameters.size() + 1);
		compiler.addInstruction(new STORE(exprReg.getRegister(compiler, true), new RegisterOffset(0, compiler.getRegisterSet().SP)));
		exprReg.free(compiler);

		for(int i = 0; i < parameters.size(); i++) {
			VirtualRegister paramReg = parameters.getList().get(i).codeGenExprIMA(compiler).getVRegister(compiler);
			compiler.addInstruction(new STORE(paramReg.getRegister(compiler, true), new RegisterOffset(-i - 1, compiler.getRegisterSet().SP)));
			paramReg.free(compiler);
		}
		
		compiler.addInstruction(new LOAD(new RegisterOffset(0, compiler.getRegisterSet().SP), compiler.getRegisterSet().R0));
		if(!compiler.getCompilerOptions().getNoCkeck()) {
			compiler.addInstruction(new CMP(new NullOperand(), compiler.getRegisterSet().R0));
			compiler.addInstruction(new BEQ(compiler.getErrorManager().getErrorLabel("NullPointer")));
		}


		compiler.addInstruction(new LOAD(new RegisterOffset(0, compiler.getRegisterSet().R0), compiler.getRegisterSet().R0));
		compiler.getRegistersHandler().pushInStack(2);
		compiler.addInstruction(new BSR(new RegisterOffset(methodIdentifier.getMethodDefinition().getIndex()/*+1*/, compiler.getRegisterSet().R0)));
		compiler.getRegistersHandler().popFromStack(2);

		compiler.addInstruction(new SUBSP(parameters.size() + 1));
		compiler.getRegistersHandler().popFromStack(parameters.size() + 1);
	}

	@Override
	public VirtualRegister codeGenExprIMA(DecacCompiler compiler) {
		this.codeGenInstIMA(compiler);

		VirtualRegister returnReg = compiler.getRegistersHandler().getNewRegister(compiler);
		compiler.addInstruction(new LOAD(compiler.getRegisterSet().R0, returnReg.getRegister(compiler, false)));

		return returnReg;
	}

	@Override
	public void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
		this.codeGenInstIMA(compiler);

		compiler.addInstruction(new CMP(new ImmediateInteger(0) ,compiler.getRegisterSet().R0));
    	if(flow.getBranchCond())
    		compiler.addInstruction(new BNE(flow.getBranchLabel()));
    	else
            compiler.addInstruction(new BEQ(flow.getBranchLabel()));
	}
}