package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.codegen.VirtualRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.FieldDefinition;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.NullOperand;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.BEQ;
import fr.ensimag.pseudocode.ima.instructions.BNE;
import fr.ensimag.pseudocode.ima.instructions.CMP;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

public class Selection extends AbstractLValue {

	private AbstractExpr expr; 
	private AbstractIdentifier field;
	
	public Selection(AbstractExpr expr,AbstractIdentifier field) {
		this.expr=expr;
		this.field=field;
	}
	@Override
	public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass)
			throws ContextualError {
		Type exprType = this.expr.verifyExpr(compiler, localEnv, currentClass);		
		TypeDefinition exprTypeDef = compiler.getTypeDef(exprType.getName());
		if( exprTypeDef != null) { // Règle 3.65 + première condition de la règle 3.66
			if(!exprTypeDef.isClass()) {
				throw new ContextualError("The left expression of the selection is not of type class",expr.getLocation());
			}
		}
		else { 
			throw new ContextualError("The left expression of the selection is not defined ",expr.getLocation());
		}
		ClassDefinition classDefinition = (ClassDefinition)exprTypeDef; 
		EnvironmentExp envExp2 = classDefinition.getMembers();
		ExpDefinition expDef = envExp2.get(field.getName());
		if(expDef == null) {
			throw new ContextualError("The field is not defined in class " + exprType.getName().getName(),field.getLocation());
		}
		if(!expDef.isField()) {
			throw new ContextualError("The right identifier of the selection is not a field ",field.getLocation());
		}
	
		FieldDefinition fieldDefinition = (FieldDefinition) expDef;
		Type fieldType = field.verifyExpr(compiler, envExp2, currentClass);
	    this.setType(fieldType);
		Visibility v =field.getFieldDefinition().getVisibility();
		if(v==Visibility.PROTECTED) {
			ClassType class2 = fieldDefinition.getContainingClass().getType();
			if(!currentClass.getType().isSubClassOf(class2)) { // Deuxième condition règle 3.66
				throw new ContextualError("The field " + field.getName().getName() + " is not visible",this.getLocation());
			}
			if(!classDefinition.getType().isSubClassOf(currentClass.getType())) {
				throw new ContextualError("The type of the expression (" + expr.getType().getName().getName() + 
						") is not a subtype of the current class  " + currentClass.getType().getName().getName(),this.getLocation());

			}
			
		}
		 return fieldType;
	}

	@Override
	public void decompile(IndentPrintStream s) {
		expr.decompile(s);
		s.print(".");
		field.decompile(s);
	}

	@Override
	protected void prettyPrintChildren(PrintStream s, String prefix) {
		expr.prettyPrint(s,prefix,false);
		field.prettyPrint(s,prefix,true);
		
	}

	@Override
	protected void iterChildren(TreeFunction f) {
		expr.iter(f);
		field.iter(f);	
	}
	public AbstractExpr getExpr() {
		return expr;
	}
	public AbstractIdentifier getField() {
		return field;
	}

	@Override
    protected void codeGenInstIMA(DecacCompiler compiler) {
		this.codeGenExprIMA(compiler).free(compiler);
	}
	
	@Override
	protected VirtualDAddr codeGenAssign(DecacCompiler compiler) {
		VirtualDVal exprVal = expr.codeGenExprIMA(compiler);

		VirtualRegister exprReg = exprVal.getVRegister(compiler);

		if(!compiler.getCompilerOptions().getNoCkeck()) {
			compiler.addInstruction(new CMP(new NullOperand(), exprReg.getRegister(compiler, false)));
			compiler.addInstruction(new BEQ(compiler.getErrorManager().getErrorLabel("NullPointer")));
		}

		return new VirtualRegisterOffset(compiler.getRegistersHandler(), exprReg,  field.getFieldDefinition().getIndex());
	}
 
	@Override
	protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	VirtualDVal exprVal = expr.codeGenExprIMA(compiler);

		VirtualRegister exprReg = exprVal.getVRegister(compiler);
		if(!compiler.getCompilerOptions().getNoCkeck()) {
			compiler.addInstruction(new CMP(new NullOperand(), exprReg.getRegister(compiler, false)));
			compiler.addInstruction(new BEQ(compiler.getErrorManager().getErrorLabel("NullPointer")));
		}

		compiler.addInstruction(new LOAD(new RegisterOffset(field.getFieldDefinition().getIndex(), exprReg.getRegister(compiler, false)), exprReg.getRegister(compiler, false)));
		return exprReg;
    }
	
	@Override
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
		VirtualDVal selectVal = this.codeGenExprIMA(compiler);
		if(!(selectVal.isRegister()))
			throw new InternalError("La sélection n'est pas stockée dans un registre");
		
		VirtualRegister selectReg = selectVal.getVRegister(compiler);
		compiler.addInstruction(new CMP(new ImmediateInteger(0), selectReg.getRegister(compiler, true)));
		selectReg.free(compiler);
		
    	if(flow.getBranchCond())
    		compiler.addInstruction(new BNE(flow.getBranchLabel()));
    	else
			compiler.addInstruction(new BEQ(flow.getBranchLabel()));
			
    }
}
