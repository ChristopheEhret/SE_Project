package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.FieldDefinition;
import fr.ensimag.deca.context.VoidType;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.EnvironmentExp.DoubleDefException;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.GRegisterOffset;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GSTR;
import fr.ensimag.pseudocode.ima.ImmediateFloat;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.NullOperand;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.STORE;

public class DeclField extends AbstractDeclField {
	private AbstractIdentifier fieldName;
	private AbstractIdentifier fieldType;
	private AbstractInitialization init;
	private Visibility visibility;
	
	public DeclField(AbstractIdentifier fieldName, AbstractIdentifier fieldType, AbstractInitialization init,
			Visibility visibility) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.init = init;
		this.visibility = visibility;
	}

	@Override
	protected void verifyDeclField(DecacCompiler compiler,EnvironmentExp localEnv,ClassDefinition currentClass)
			throws ContextualError {
        	Type typeField = compiler.getType(fieldType.getName());     
			if(typeField == null) {
            	throw new ContextualError("The type " + fieldType.getName().getName() + " doesn't exist",fieldType.getLocation());
    		}
			fieldType.setType(typeField);
			fieldType.setDefinition(compiler.getTypeDef(fieldType.getName()));	        
	        ClassDefinition superClass = currentClass.getSuperClass();
	        // Verification de la règle 2.5 (on sait déjà que la superclass existe grâce à la passe 1)
	        ExpDefinition envExpSuper_Name = superClass.getMembers().getOnlyCurrent(fieldName.getName());
	        int index = currentClass.getNumberOfFields();
	        if( envExpSuper_Name != null ) {
	        	if(!envExpSuper_Name.isField()) { 
	        		throw new ContextualError("The identifier " + fieldName.getName().getName() + " already exists and it is not an attribute",this.getLocation());
	        	}	        
	        }
	        currentClass.incNumberOfFields();
	       	index = currentClass.getNumberOfFields();
			if(typeField instanceof VoidType)
	            throw new ContextualError("Cannot declare a champ of type void", this.getLocation());			
	        FieldDefinition fieldDefinition = new FieldDefinition(typeField, this.getLocation(),visibility,currentClass,index);
	       /*Les quatres lignes suivantes permettent juste de vérifier que l'on initialise pas un champ
	        * à lui même ( du style int a = a )*/
	        AbstractExpr expression = init.getExpression();
	        if(expression != null) {
		        expression.verifyExpr(compiler, localEnv, currentClass);	        	
	        }
	        try {
	            localEnv.declare(fieldName.getName(), fieldDefinition);
	            // localEnv.declare(varName.getName(), variableDefinition);
	        } catch (DoubleDefException e) {
	            throw new ContextualError("Field  " + fieldName.getName() + " was already defined", this.getLocation());
	        }

	        fieldName.setDefinition(fieldDefinition);
	        
	}
	@Override
	public void verifyFieldBody(DecacCompiler compiler,ClassDefinition currentClass) throws ContextualError {		
		init.verifyInitialization(compiler,compiler.getType(fieldType.getName()),currentClass.getMembers(), currentClass);
	}

	@Override
    public void decompile(IndentPrintStream s) {
		if(visibility == Visibility.PROTECTED) {
			s.print(visibility.toString().toLowerCase() + " ");			
		}
		fieldType.decompile(s);
		s.print(" ");
		fieldName.decompile(s);
		init.decompile(s);
		s.print(";");
	}
	@Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    	fieldType.prettyPrint(s,prefix,false);
		fieldName.prettyPrint(s,prefix,false);
    	init.prettyPrint(s,prefix,true);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    	fieldType.iter(f);
    	fieldName.iter(f);
    	init.iter(f);
	}	
	
	@Override
	public void codeGenFieldPreDecl(DecacCompiler compiler, GPRegister objectAddrReg, GPRegister initReg) {
		if(fieldName.getFieldDefinition().getType().isFloat())
			compiler.addInstruction(new LOAD(new ImmediateFloat(0), initReg));
		else if(fieldName.getFieldDefinition().getType().isClass())
			compiler.addInstruction(new LOAD(new NullOperand(), initReg));
		else
			compiler.addInstruction(new LOAD(new ImmediateInteger(0), initReg));

		compiler.addInstruction(new STORE(initReg, new RegisterOffset(fieldName.getFieldDefinition().getIndex(), objectAddrReg)));
	}

	@Override
	public void codeGenFieldDecl(DecacCompiler compiler, boolean fullDecl) {
		if(init instanceof NoInitialization) {
			if(!fullDecl)
				return;

			// TODOC : A faire avec l'initialisation des objets
			if(fieldName.getFieldDefinition().getType().isFloat())
				compiler.addInstruction(new LOAD(new ImmediateFloat(0), compiler.getRegisterSet().R0));
			else if(fieldName.getFieldDefinition().getType().isClass())
				compiler.addInstruction(new LOAD(new NullOperand(), compiler.getRegisterSet().R0));	
			else
				compiler.addInstruction(new LOAD(new ImmediateInteger(0), compiler.getRegisterSet().R0));

		} else {
			if(fieldName.getFieldDefinition().getType().isBoolean()) {
				Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
				Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
				
				BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);
				init.codeGenBoolIMA(compiler, flow);
				compiler.addInstruction(new LOAD(new ImmediateInteger(1), compiler.getRegisterSet().R0));
				compiler.addInstruction(new BRA(endLbl));
				compiler.addLabel(falseLbl);
				compiler.addInstruction(new LOAD(new ImmediateInteger(0), compiler.getRegisterSet().R0));
				compiler.addLabel(endLbl);
				// compiler.addInstruction(new LOAD(GPRegister.getR(lowerReg), GPRegister.R0));
			} else {
				// TODOC techniquement optimisable
				VirtualDVal initVal = init.codeGenExprIMA(compiler);
				compiler.addInstruction(new LOAD(initVal.getDVal(compiler, true), compiler.getRegisterSet().R0));
				initVal.free(compiler);
			}
		}

		compiler.addInstruction(new LOAD(new RegisterOffset(-2, compiler.getRegisterSet().LB), compiler.getRegisterSet().getR(1)));
		
		final int index = fieldName.getFieldDefinition().getIndex();
		compiler.addInstruction(new STORE(compiler.getRegisterSet().R0, new RegisterOffset(index, compiler.getRegisterSet().getR(1))));
	}

	@Override
	public void codeGenFieldPreDeclGBA(DecacCompiler compiler, GPRegister objectAddrReg, GPRegister initReg) {
		compiler.addInstruction(new GMOV(initReg, GOp2.convertOp2(0)));
		compiler.addInstruction(new GSTR(initReg, new GRegisterOffset(objectAddrReg, fieldName.getFieldDefinition().getIndex())));
	}

	@Override
	public void codeGenFieldDeclGBA(DecacCompiler compiler, boolean fullDecl) {
		if(init instanceof NoInitialization) {
			if(!fullDecl)
				return;
				compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, GOp2.convertOp2(0)));
		
		} else {
			if(fieldName.getFieldDefinition().getType().isBoolean()) {
				Label falseLbl = new Label("ldFalse.n"+Integer.toString(compiler.getCounterManager().addOne("ldBool")));
				Label endLbl = new Label("ldEnd.n"+Integer.toString(compiler.getCounterManager().last("ldBool")));
				
				BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);
				init.codeGenBoolIMA(compiler, flow);
				compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, GOp2.convertOp2(1)));
				compiler.addInstruction(new GB(endLbl));
				compiler.addLabel(falseLbl);
				compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, GOp2.convertOp2(1)));
				compiler.addLabel(endLbl);
			} else {
				// TODOC techniquement optimisable
				VirtualDVal initVal = init.codeGenExprIMA(compiler);
				VirtualRegister initReg = initVal.getVRegister(compiler);
				
				compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, initReg.getRegister(compiler, true)));
				initReg.free(compiler);
			}
		}

		compiler.addInstruction(new GLDR(compiler.getRegisterSet().getR(1), new GRegisterOffset(compiler.getRegisterSet().LB, -2)));
		
		final int index = fieldName.getFieldDefinition().getIndex();
		compiler.addInstruction(new GSTR(compiler.getRegisterSet().R0, new GRegisterOffset(compiler.getRegisterSet().R1, index)));
	}
}