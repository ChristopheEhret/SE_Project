package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.GRegisterOffset;
import fr.ensimag.pseudocode.gba.instructions.GADD;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GPOP;
import fr.ensimag.pseudocode.gba.instructions.GPUSH;
import fr.ensimag.pseudocode.gba.instructions.GSTR;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.BOV;
import fr.ensimag.pseudocode.ima.instructions.BSR;
import fr.ensimag.pseudocode.ima.instructions.LEA;
import fr.ensimag.pseudocode.ima.instructions.NEW;
import fr.ensimag.pseudocode.ima.instructions.POP;
import fr.ensimag.pseudocode.ima.instructions.PUSH;
import fr.ensimag.pseudocode.ima.instructions.STORE;

public class New extends AbstractExpr{
	private AbstractIdentifier ident;
	
	public New(AbstractIdentifier ident) {
		super();
		this.ident=ident;
	}
	@Override
    public void decompile(IndentPrintStream s) {
		s.print("new ");
		ident.decompile(s);
		s.print("()");
	}
    @Override
    protected
    void iterChildren(TreeFunction f) {
    	ident.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    	ident.prettyPrint(s, prefix,true);
    }
  
    public Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
    	Type identType = ident.verifyType(compiler);
    	if(!identType.isClass()) {
    		throw new ContextualError("Cannot instantiate anything other than a class ",this.getLocation());
    	}
    	this.setType(identType);
    	return identType;
    	
    } 	
	public AbstractIdentifier getIdent() {
		return ident;
	}	

	@Override
	protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
		ClassDefinition classDef = ident.getClassDefinition();
		
		VirtualRegister objectRegister = compiler.getRegistersHandler().getNewRegister(compiler);
		compiler.addInstruction(new NEW(classDef.getNumberOfFields()+1, objectRegister.getRegister(compiler, false)));
		if(!compiler.getCompilerOptions().getNoCkeck())
			compiler.addInstruction(new BOV(compiler.getErrorManager().getErrorLabel("HeapOverflow")));

		compiler.addInstruction(new LEA(new RegisterOffset(classDef.getOffsetGB(), compiler.getRegisterSet().GB), compiler.getRegisterSet().R0));
		compiler.addInstruction(new STORE(compiler.getRegisterSet().R0, new RegisterOffset(0, objectRegister.getRegister(compiler, false))));

		compiler.getRegistersHandler().pushInStack(3);
		compiler.addInstruction(new PUSH(objectRegister.getRegister(compiler, false)));
		compiler.addInstruction(new BSR(classDef.getInitMethod()));
		compiler.addInstruction(new POP(objectRegister.getRegister(compiler, false)));
		compiler.getRegistersHandler().popFromStack(3);
		
		return objectRegister;
	}

	@Override
	protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
		ClassDefinition classDef = ident.getClassDefinition();
		
		GOp2 nbVars = GOp2.convertOp2(classDef.getNumberOfFields()+1);
		if(nbVars == null) {
			compiler.addInstruction(new GLDR(compiler.getRegisterSet().R0, new GImmediate32BitInteger(classDef.getNumberOfFields()+1))); 
		} else {
			compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, nbVars));
		}
		compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("new")));


		VirtualRegister objectRegister = compiler.getRegistersHandler().getNewRegister(compiler);
		compiler.addInstruction(new GMOV(objectRegister.getRegister(compiler, false), compiler.getRegisterSet().R0));

		GOp2 offset = GOp2.convertOp2(classDef.getOffsetGB());
		if(offset == null) {
			 compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GImmediate32BitInteger(classDef.getOffsetGB()))); 
			offset = new GOp2(compiler.getRegisterSet().R1);
		}
		compiler.addInstruction(new GADD(compiler.getRegisterSet().R0, compiler.getRegisterSet().GB, offset));	
		compiler.addInstruction(new GSTR(compiler.getRegisterSet().R0, new GRegisterOffset(objectRegister.getRegister(compiler, false), 0)));

		compiler.getRegistersHandler().pushInStack(3);
		compiler.addInstruction(new GPUSH(objectRegister.getRegister(compiler, false)));
		compiler.addInstruction(new GBL(classDef.getInitMethod()));
		compiler.addInstruction(new GPOP(objectRegister.getRegister(compiler, false)));
		compiler.getRegistersHandler().popFromStack(3);
		
		return objectRegister;
	}
}
