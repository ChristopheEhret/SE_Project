package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualStaticRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.GLabelOperand;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GADD;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GPOP;
import fr.ensimag.pseudocode.gba.instructions.GPUSH;
import fr.ensimag.pseudocode.gba.instructions.GSUB;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.ImmediateString;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.ERROR;
import fr.ensimag.pseudocode.ima.instructions.POP;
import fr.ensimag.pseudocode.ima.instructions.PUSH;
import fr.ensimag.pseudocode.ima.instructions.RTS;
import fr.ensimag.pseudocode.ima.instructions.SUBSP;
import fr.ensimag.pseudocode.ima.instructions.WNL;
import fr.ensimag.pseudocode.ima.instructions.WSTR;

public class MethodBody extends AbstractMethodBody {

	private ListDeclVar listDeclVar;
	private ListInst listInst;
	
	public MethodBody(ListDeclVar listDeclVar, ListInst listInst) {
		super();
		this.listDeclVar = listDeclVar;
		this.listInst = listInst;
	}

	@Override
	protected void verifyMethodBody(DecacCompiler compiler, EnvironmentExp localEnv,EnvironmentExp envExpParam ,ClassDefinition currentClass,Type returnType)
			throws ContextualError {
			EnvironmentExp envStack = new EnvironmentExp(localEnv);
			envStack.setEnvMap(envExpParam.getEnvMap());
			listDeclVar.verifyListDeclVariable(compiler, envStack, currentClass);
			listInst.verifyListInst(compiler, envStack, currentClass, returnType);
	}

	@Override
	protected void codeGenBodyIMA(DecacCompiler compiler, AbstractIdentifier typeReturned, String methodName) {
		compiler.switchNewBlock(false);
		compiler.getSOVH().setNbVars(listDeclVar.size());

        int offsetVar = 1;
        for(AbstractDeclVar avar : listDeclVar.getList()) {
			compiler.getRegistersHandler().resetRegisters();
			avar.codeGenDeclIMA(compiler, new VirtualStaticRegisterOffset(compiler.getRegistersHandler(), compiler.getRegisterSet().LB,  offsetVar++));
		}
        
        listInst.codeGenListInstIMA(compiler);
        if(!typeReturned.getType().isVoid()) {
			compiler.addInstruction(new WSTR(new ImmediateString("Erreur : Sortie de la fonction " + methodName + " sans instruction de retour.")));
			compiler.addInstruction(new WNL());
			compiler.addInstruction(new ERROR());
			// TODOC : check si il faudrait pas mettre ca plus bas par hasard
	        compiler.addLabel(compiler.getCurrentReturnLbl());
        }
        for(int regi=2; regi<=compiler.getRegistersHandler().getMaxLowerReg(); regi++) {
        	compiler.getCurrentBlock().addFirst(new PUSH(compiler.getRegisterSet().getR(regi)));
        	compiler.addInstruction(new POP(compiler.getRegisterSet().getR(regi)));
        }
        compiler.addInstruction(new SUBSP(new ImmediateInteger(listDeclVar.size())));
		compiler.addInstruction(new RTS());
		
		// A mettre surement dans DeclMethod
		compiler.addBlockChecks();
		compiler.appendCurrentBlock();
	}
	
	@Override
	protected void codeGenBodyGBA(DecacCompiler compiler, AbstractIdentifier typeReturned, String methodName) {
		compiler.switchNewBlock(false);
		GOp2 nbVars = GOp2.convertOp2(listDeclVar.size());
		if(nbVars == null) {
			compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GImmediate32BitInteger(listDeclVar.size()))); 
		    nbVars = new GOp2(compiler.getRegisterSet().R1);
	   	}
		compiler.addInstruction(new GSUB(compiler.getRegisterSet().SP, compiler.getRegisterSet().SP, nbVars));
		
        int offsetVar = 1;
        for(AbstractDeclVar avar : listDeclVar.getList()) {
			compiler.getRegistersHandler().resetRegisters();
			avar.codeGenDeclGBA(compiler, new VirtualStaticRegisterOffset(compiler.getRegistersHandler(), compiler.getRegisterSet().LB,  offsetVar++));
		}
        
        listInst.codeGenListInstGBA(compiler);
        if(!typeReturned.getType().isVoid()) {
			Label lblErr = compiler.getDataManager().addString("Erreur : Pas de valeur de retour");
			compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GLabelOperand(lblErr)));
			compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("printstr")));
			Label endLoopInf = new Label("noReturn.n" + compiler.getCounterManager().addOne("NoReturn")+".loopInf");
	        compiler.addLabel(endLoopInf);
	        compiler.addInstruction(new GB(endLoopInf));

			compiler.addLabel(compiler.getCurrentReturnLbl());
		}
		
        for(int regi=2; regi<=compiler.getRegistersHandler().getMaxLowerReg(); regi++) {
        	compiler.getCurrentBlock().addFirst(new GPUSH(compiler.getRegisterSet().getR(regi)));
        	compiler.addInstruction(new GPOP(compiler.getRegisterSet().getR(regi)));
		}
		
		nbVars = GOp2.convertOp2(listDeclVar.size());
		if(nbVars == null) {
			compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GImmediate32BitInteger(listDeclVar.size()))); 
		    nbVars = new GOp2(compiler.getRegisterSet().R1);
	   	}
		compiler.addInstruction(new GADD(compiler.getRegisterSet().SP, compiler.getRegisterSet().SP, nbVars));
		
		compiler.getCurrentBlock().addFirst(new GMOV(compiler.getRegisterSet().LB, compiler.getRegisterSet().SP));
		compiler.getCurrentBlock().addFirst(new GPUSH(compiler.getRegisterSet().LR));
		compiler.addInstruction(new GPOP(compiler.getRegisterSet().LR));

		compiler.getCurrentBlock().addFirst(new GPUSH(compiler.getRegisterSet().LB));
		compiler.addInstruction(new GPOP(compiler.getRegisterSet().LB));

        compiler.addInstruction(new GMOV(compiler.getRegisterSet().PC, compiler.getRegisterSet().LR));
		
		compiler.addBlockChecks();
		compiler.appendCurrentBlock();
	}
	
	public void decompile(IndentPrintStream s) {
		s.println(" {");
		s.indent();
		listDeclVar.decompile(s);
		listInst.decompile(s);
		s.unindent();
		s.println("}");
	}
	public void prettyPrintChildren(PrintStream s, String prefix) {
		listDeclVar.prettyPrint(s,prefix,false);
		listInst.prettyPrint(s,prefix,true);
		
		
	}
	public void iterChildren(TreeFunction f) {
		listDeclVar.iter(f);
		listInst.iter(f);
	}
}
