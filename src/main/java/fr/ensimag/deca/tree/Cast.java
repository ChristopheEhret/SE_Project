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
import fr.ensimag.deca.context.TypeTools;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.ima.NullOperand;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.BEQ;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.CMP;
import fr.ensimag.pseudocode.ima.instructions.FLOAT;
import fr.ensimag.pseudocode.ima.instructions.INT;
import fr.ensimag.pseudocode.ima.instructions.LEA;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

public class Cast extends AbstractExpr{
	private AbstractIdentifier type_identificateur;
	private AbstractExpr expr;
	
	public Cast(AbstractIdentifier type_identificateur, AbstractExpr expr) {
		super();
		this.type_identificateur=type_identificateur;
		this.expr=expr;
	}
	@Override
    public void decompile(IndentPrintStream s) {
		s.print("(");
		type_identificateur.decompile(s);
		s.print(")(");
		expr.decompile(s);
		s.print(")");
	}
    @Override
    protected
    void iterChildren(TreeFunction f) {
    	type_identificateur.iter(f);
    	expr.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    	type_identificateur.prettyPrint(s, prefix,false);
    	expr.prettyPrint(s, prefix,true);
    }
  
    public Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
    	Type identType = type_identificateur.verifyType(compiler);
    	Type exprType = expr.verifyExpr(compiler, localEnv, currentClass);
    	if(identType.isVoid()) {
    		throw new ContextualError("Cannot cast a void type",this.getLocation());
    	}
    	if(!(TypeTools.assign_compatible(compiler, identType, exprType) || TypeTools.assign_compatible(compiler, exprType, identType))) {
    		throw new ContextualError("The types are incompatible for a cast ",this.getLocation());
    	}
    	this.setType(identType);
    	return identType;
    } 	
	public AbstractExpr getTypeIdentificateur() {
		return type_identificateur;
	}
	public AbstractExpr getExpr() {
		return expr;
	}	

	@Override
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
	}

    protected VirtualDVal codeGenExprIMAObjectCast(DecacCompiler compiler) {
		ClassDefinition classDef = type_identificateur.getClassDefinition();
		Label castOkLabel = new Label("instanceOfOk.n" + compiler.getCounterManager().addOne("instanceOfBool"));
		Label castFailLabel = new Label("instanceOfFail.n" + compiler.getCounterManager().last("instanceOfBool"));
		Label loopLabel = new Label("instanceOfLoop.n" + compiler.getCounterManager().last("instanceOfBool"));

		// Récupère le registre de l'objet à évaluer
		VirtualRegister exprReg = expr.codeGenExprIMA(compiler).getVRegister(compiler);
		// Check si l'objet est null
		compiler.addInstruction(new CMP(new NullOperand(), exprReg.getRegister(compiler, true)));
		compiler.addInstruction(new BEQ(castOkLabel));
		
		// On charge l'adresse de la class à comparer
		compiler.addInstruction(new LEA(new RegisterOffset(classDef.getOffsetGB(), compiler.getRegisterSet().GB), compiler.getRegisterSet().R1));
		compiler.addInstruction(new LOAD(new RegisterOffset(0, exprReg.getRegister(compiler, false)), compiler.getRegisterSet().R0));
		
		// Début de la boucle
		compiler.addLabel(loopLabel);

		// On vérifie si on est pas arrivé au bout de l'héritage de l'objet
		compiler.addInstruction(new CMP(new NullOperand(), compiler.getRegisterSet().R0));
		compiler.addInstruction(new BEQ(castFailLabel));
		
		// On vérifie si la les classes correspondent
		compiler.addInstruction(new CMP(compiler.getRegisterSet().R1, compiler.getRegisterSet().R0));
		compiler.addInstruction(new BEQ(castOkLabel));
		
		// On récupère la superclasse de l'objet (ou la classe si on en est à la première passe)
		compiler.addInstruction(new LOAD(new RegisterOffset(0, compiler.getRegisterSet().R0), compiler.getRegisterSet().R0));
		// Si aucun des deux, on reboucle
		compiler.addInstruction(new BRA(loopLabel));

		compiler.addLabel(castFailLabel);
		compiler.addInstruction(new BRA(compiler.getErrorManager().getErrorLabel("CastFail")));
		
		compiler.addLabel(castOkLabel);

		return exprReg;
	}

	@Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
		if(type_identificateur.getType().isInt() && expr.getType().isInt())
			return expr.codeGenExprIMA(compiler);
		if(type_identificateur.getType().isFloat() && expr.getType().isFloat())
			return expr.codeGenExprIMA(compiler);
		if(type_identificateur.getType().isBoolean() && expr.getType().isBoolean())
			return expr.codeGenExprIMA(compiler);

		if(type_identificateur.getType().isInt() && expr.getType().isFloat()) {
			VirtualRegister exprReg = expr.codeGenExprIMA(compiler).getVRegister(compiler);
			compiler.addInstruction(new INT(exprReg.getDVal(compiler, false), exprReg.getRegister(compiler, false)));
			return exprReg;
		}

		if(type_identificateur.getType().isFloat() && expr.getType().isInt()) {
			VirtualRegister exprReg = expr.codeGenExprIMA(compiler).getVRegister(compiler);
			compiler.addInstruction(new FLOAT(exprReg.getDVal(compiler, false), exprReg.getRegister(compiler, false)));
			return exprReg;
		}

		return codeGenExprIMAObjectCast(compiler);
	}
}
