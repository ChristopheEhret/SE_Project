package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.GLabelOperand;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.GRegisterOffset;
import fr.ensimag.pseudocode.gba.instructions.GADD;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GPOP;
import fr.ensimag.pseudocode.gba.instructions.GPUSH;
import fr.ensimag.pseudocode.gba.instructions.GSTR;
import fr.ensimag.pseudocode.ima.IMALine;
import fr.ensimag.pseudocode.ima.LabelOperand;
import fr.ensimag.pseudocode.ima.NullOperand;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.BSR;
import fr.ensimag.pseudocode.ima.instructions.LEA;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.POP;
import fr.ensimag.pseudocode.ima.instructions.PUSH;
import fr.ensimag.pseudocode.ima.instructions.RTS;
import fr.ensimag.pseudocode.ima.instructions.STORE;

import java.io.PrintStream;


/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class DeclClass extends AbstractDeclClass {
	
	private AbstractIdentifier className;
	private AbstractIdentifier superClassName;
	private ListDeclField listDeclField;
	private ListDeclMethod listDeclMethod;
	
	
    public DeclClass(AbstractIdentifier className, AbstractIdentifier superClassName, ListDeclField listDeclField,
			ListDeclMethod listDeclMethod) {
		super();
		this.className = className;
		this.superClassName = superClassName;
		this.listDeclField = listDeclField;
		this.listDeclMethod = listDeclMethod;
	}

	@Override
    public void decompile(IndentPrintStream s) {
		s.print("class ");
		className.decompile(s);
		s.print(" extends ");
		superClassName.decompile(s);
		s.println(" {");
		s.indent();
		listDeclField.decompile(s);
		listDeclMethod.decompile(s);
		s.unindent();
		s.println("}");
	}

    @Override
    protected void verifyClass(DecacCompiler compiler) throws ContextualError {
    	//Verication que la classe n'existe pas déjà
    	if(compiler.getEnvironmentTypes().containsKey(className.getName())) {
    		throw new ContextualError("The class  " + className.getName().getName() + " is already defined ", this.getLocation());
    	}
    	// Verification de l'existence de la superclass
    	TypeDefinition superClassType = compiler.getTypeDef(superClassName.getName());
    	if(superClassType == null) {
            throw new ContextualError("The class " + className.getName().getName() + " doesn't have a superclass or no exist",this.getLocation());
    	}
    	if(!superClassType.isClass()) {
            throw new ContextualError(className.getName().getName() + "'s superclass is not of type class ", this.getLocation());
    	}
    	superClassName.setDefinition(superClassType);
    	superClassName.setType(compiler.getType(superClassName.getName()));
    	Symbol symbol = compiler.getOrAddSymbol(className.getName().getName());
    	ClassDefinition superClassDefinition = (ClassDefinition) superClassType;
        ClassType newClassType = new ClassType(symbol,this.getLocation(),superClassDefinition);
        className.setType(newClassType);
        ClassDefinition newClassDef = newClassType.getDefinition();
        className.setDefinition(newClassDef);
        compiler.addClassdefinition(symbol,newClassDef);
    }

    @Override
    protected void verifyClassMembers(DecacCompiler compiler)
            throws ContextualError {
		listDeclField.verifyListDeclField(compiler, className.getClassDefinition(),superClassName.getClassDefinition());
		listDeclMethod.verifyListDeclMethod(compiler, className.getClassDefinition());
    }
    
    @Override
    protected void verifyClassBody(DecacCompiler compiler) throws ContextualError {
    	listDeclField.verifyListFieldBody(compiler, className.getClassDefinition());
		listDeclMethod.verifyListMethodBody(compiler,className.getClassDefinition().getMembers() ,className.getClassDefinition());    	    	
    }


    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    	className.prettyPrint(s,prefix,false);
    	superClassName.prettyPrint(s,prefix,false);
    	listDeclField.prettyPrint(s,prefix,false);
    	listDeclMethod.prettyPrint(s,prefix,true);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    	className.iterChildren(f);
    	superClassName.iterChildren(f);
    	listDeclField.iterChildren(f);
    	listDeclMethod.iterChildren(f);
    }
    
    public String getClassName() {
    	return className.decompile();
    }
    
	@Override
	public int buildMethodTableIMA(DecacCompiler compiler, int nextOffset) {
		// TODO : enlever le +1 qd Object est fait
		MethodDefinition[] methods = new MethodDefinition[className.getClassDefinition().getNumberOfMethods()]; 
		className.getClassDefinition().getMembers().getAllMethods(methods);
	
		className.getClassDefinition().setOffsetGB(nextOffset);
		if(superClassName.getClassDefinition() != compiler.getTypeDef(compiler.getOrAddSymbol("0")))
			compiler.addInstruction(new LEA(new RegisterOffset(superClassName.getClassDefinition().getOffsetGB(), compiler.getRegisterSet().GB), compiler.getRegisterSet().R0));
		else
			compiler.addInstruction(new LOAD(new NullOperand(), compiler.getRegisterSet().R0));

		compiler.addInstruction(new STORE(compiler.getRegisterSet().R0, new RegisterOffset(nextOffset, compiler.getRegisterSet().GB)));
		
		for(AbstractDeclMethod methodDecl : listDeclMethod.getList())
			methodDecl.buildMethodLabels(className.getName().getName());

		for(int i = 0; i < methods.length; i++) {
			compiler.addInstruction(new LOAD(new LabelOperand(methods[i].getCodeLabel()), compiler.getRegisterSet().R0));
			compiler.addInstruction(new STORE(compiler.getRegisterSet().R0, new RegisterOffset(nextOffset+i+1, compiler.getRegisterSet().GB)));
		}
		
		if(className.getClassDefinition() != compiler.getTypeDef(compiler.getOrAddSymbol("0")))
			className.getClassDefinition().setInitMethod(new Label("init."+getClassName()));

		return nextOffset+className.getClassDefinition().getNumberOfMethods()+1;
	}
	
	@Override
	public int buildMethodTableGBA(DecacCompiler compiler, int nextOffset) {
		MethodDefinition[] methods = new MethodDefinition[className.getClassDefinition().getNumberOfMethods()]; 
		className.getClassDefinition().getMembers().getAllMethods(methods);
	
		className.getClassDefinition().setOffsetGB(nextOffset);
		if(superClassName.getClassDefinition() != compiler.getTypeDef(compiler.getOrAddSymbol("0"))) {			
			compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, compiler.getRegisterSet().GB));	

			GOp2 offset = GOp2.convertOp2(superClassName.getClassDefinition().getOffsetGB());
			if(offset == null) {
			 	compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GImmediate32BitInteger(superClassName.getClassDefinition().getOffsetGB()))); 
				offset = new GOp2(compiler.getRegisterSet().R1);
			}

			compiler.addInstruction(new GADD(compiler.getRegisterSet().R0, compiler.getRegisterSet().R0, offset));	
		} else {
			compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, GOp2.convertOp2(0)));
		}

		compiler.addInstruction(new GSTR(compiler.getRegisterSet().R0, new GRegisterOffset(compiler.getRegisterSet().GB, nextOffset)));
				
		for(AbstractDeclMethod methodDecl : listDeclMethod.getList())
			methodDecl.buildMethodLabels(className.getName().getName());

		for(int i = 0; i < methods.length; i++) {
			compiler.addInstruction(new GLDR(compiler.getRegisterSet().R0, new GLabelOperand(methods[i].getCodeLabel())));
			compiler.addInstruction(new GSTR(compiler.getRegisterSet().R0, new GRegisterOffset(compiler.getRegisterSet().GB, nextOffset+i+1)));
		}
		
		if(className.getClassDefinition() != compiler.getTypeDef(compiler.getOrAddSymbol("0")))
			className.getClassDefinition().setInitMethod(new Label("init."+getClassName()));

		return nextOffset+className.getClassDefinition().getNumberOfMethods()+1;
	}
	
	
	@Override
	public void codeGenClassIMA(DecacCompiler compiler) {
		LOG.trace("CodeGen: Class "+getClassName());
		compiler.addComment("******************************");
		compiler.addComment(" New class: "+getClassName());

		for(AbstractDeclMethod method : listDeclMethod.getList())
			method.codeGenMethodIMA(compiler);
	}
	
	@Override
	public void codeGenClassGBA(DecacCompiler compiler) {
		LOG.trace("CodeGen: Class "+getClassName());
		compiler.addComment("******************************");
		compiler.addComment(" New class: "+getClassName());

		for(AbstractDeclMethod method : listDeclMethod.getList())
			method.codeGenMethodGBA(compiler);
	}
	
	@Override
	public void codeGenInitIMA(DecacCompiler compiler) {
		if(className.getClassDefinition() == compiler.getTypeDef(compiler.getOrAddSymbol("Object")))
			return;

		LOG.trace("CodeGen: Method class init");
		compiler.addComment("- - - - - - - -");
		compiler.addComment(" New method: init");
		compiler.switchNewBlock(false);
		
		final boolean haveSuperClass = className.getClassDefinition().getSuperClass() != compiler.getTypeDef(compiler.getOrAddSymbol("Object"));
		
		if(haveSuperClass) { 
			compiler.addInstruction(new LOAD(new RegisterOffset(-2, compiler.getRegisterSet().LB), compiler.getRegisterSet().getR(1)));
			for(AbstractDeclField field : listDeclField.getList())
				field.codeGenFieldPreDecl(compiler, compiler.getRegisterSet().getR(1), compiler.getRegisterSet().getR(0));
			
			compiler.getRegistersHandler().pushInStack(3);
			compiler.addInstruction(new PUSH(compiler.getRegisterSet().getR(1)));
			compiler.addInstruction(new BSR(className.getClassDefinition().getSuperClass().getInitMethod()));
			compiler.addInstruction(new POP(compiler.getRegisterSet().getR(1)));
			compiler.getRegistersHandler().popFromStack(3);
		}		

		for(AbstractDeclField field : listDeclField.getList()) {
			compiler.getRegistersHandler().resetRegisters();
			field.codeGenFieldDecl(compiler, !haveSuperClass);	
		}	

        for(int regi=2; regi<=compiler.getRegistersHandler().getMaxLowerReg(); regi++) {
        	compiler.getCurrentBlock().addFirst(new PUSH(compiler.getRegisterSet().getR(regi)));
        	compiler.addInstruction(new POP(compiler.getRegisterSet().getR(regi)));
        }
        compiler.addInstruction(new RTS());
        
		compiler.addBlockChecks();
		compiler.getCurrentBlock().addFirst(new IMALine(className.getClassDefinition().getInitMethod()));
		compiler.appendCurrentBlock();
	}
	
	@Override
	public void codeGenInitGBA(DecacCompiler compiler) {
		if(className.getClassDefinition() == compiler.getTypeDef(compiler.getOrAddSymbol("Object")))
			return;

		LOG.trace("CodeGen: Method class init");
		compiler.addComment("- - - - - - - -");
		compiler.addComment(" New method: init");
		compiler.switchNewBlock(false);
		
		final boolean haveSuperClass = className.getClassDefinition().getSuperClass() != compiler.getTypeDef(compiler.getOrAddSymbol("Object"));
		
		if(haveSuperClass) { 
			compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GRegisterOffset(compiler.getRegisterSet().LB, -2)));
			for(AbstractDeclField field : listDeclField.getList())
				field.codeGenFieldPreDeclGBA(compiler, compiler.getRegisterSet().getR(1), compiler.getRegisterSet().getR(0));
			
			compiler.getRegistersHandler().pushInStack(3);
			compiler.addInstruction(new GPUSH(compiler.getRegisterSet().getR(1)));
			compiler.addInstruction(new GBL(className.getClassDefinition().getSuperClass().getInitMethod()));
			compiler.addInstruction(new GPOP(compiler.getRegisterSet().getR(1)));
			compiler.getRegistersHandler().popFromStack(3);
		}		

		for(AbstractDeclField field : listDeclField.getList()) {
			compiler.getRegistersHandler().resetRegisters();
			field.codeGenFieldDeclGBA(compiler, !haveSuperClass);	
		}	

        for(int regi=2; regi<=compiler.getRegistersHandler().getMaxLowerReg(); regi++) {
        	compiler.getCurrentBlock().addFirst(new GPUSH(compiler.getRegisterSet().getR(regi)));
        	compiler.addInstruction(new GPOP(compiler.getRegisterSet().getR(regi)));
		}

		compiler.getCurrentBlock().addFirst(new GPUSH(compiler.getRegisterSet().LR));
		compiler.addInstruction(new GPOP(compiler.getRegisterSet().LR));

		compiler.getCurrentBlock().addFirst(new GPUSH(compiler.getRegisterSet().LB));
		compiler.getCurrentBlock().addFirst(new GMOV(compiler.getRegisterSet().LB, compiler.getRegisterSet().SP));
		compiler.addInstruction(new GPOP(compiler.getRegisterSet().LB));

        compiler.addInstruction(new GMOV(compiler.getRegisterSet().PC, compiler.getRegisterSet().LR));
        
		compiler.addBlockChecks();
		compiler.getCurrentBlock().addFirst(new IMALine(className.getClassDefinition().getInitMethod()));
		compiler.appendCurrentBlock();
	}
}