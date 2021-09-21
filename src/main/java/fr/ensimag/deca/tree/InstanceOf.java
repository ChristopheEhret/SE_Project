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
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.NullOperand;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.BEQ;
import fr.ensimag.pseudocode.ima.instructions.BNE;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.CMP;
import fr.ensimag.pseudocode.ima.instructions.LEA;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.WSTR;

public class InstanceOf extends AbstractExpr{
	private AbstractExpr expr;
	private AbstractIdentifier ident;
	
	public InstanceOf(AbstractExpr expr,AbstractIdentifier ident) {
		super();
		this.expr=expr;
		this.ident=ident;
	}  
    @Override
    public void decompile(IndentPrintStream s) {
    	expr.decompile(s);
    	s.print(" instanceof ");
    	ident.decompile(s);    	
    }
    @Override
    protected
    void iterChildren(TreeFunction f) {
    	expr.iter(f);
    	ident.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    	expr.prettyPrint(s, prefix,false);
    	ident.prettyPrint(s,prefix,true);
    }
  
    public Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
    	Type exprType = expr.verifyExpr(compiler, localEnv, currentClass);
    	Type identType = ident.verifyType(compiler);
    	if(! (identType.isClass())){
    		throw new ContextualError("The right operand is not of type class ",ident.getLocation());
    	}
    	if(!exprType.isClassOrNull()) {
    		throw new ContextualError("The left operand is not of type class ",expr.getLocation());    		
    	}
    	Type instanceType = compiler.getType(compiler.getOrAddSymbol("boolean"));
    	this.setType(instanceType);
		return instanceType;
    } 
	public AbstractExpr getExpr() {
		return expr;
	}
	public AbstractIdentifier getIdent() {
		return ident;
	}

    @Override
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
		ClassDefinition classDef = ident.getClassDefinition();
		Label okLabel = new Label("instanceOfOk.n" + compiler.getCounterManager().addOne("instanceOfBool"));
		Label loopLabel = new Label("instanceOfLoop.n" + compiler.getCounterManager().last("instanceOfBool"));

		// Récupère le registre de l'objet à évaluer
		VirtualRegister exprReg = expr.codeGenExprIMA(compiler).getVRegister(compiler);
		// Check si l'objet est null
		compiler.addInstruction(new CMP(new NullOperand(), exprReg.getRegister(compiler, true)));
		if(flow.getBranchCond())
			compiler.addInstruction(new BEQ(okLabel));
		else
			compiler.addInstruction(new BEQ(flow.getBranchLabel()));
		
		// On charge l'adresse de la class à comparer
		compiler.addInstruction(new LEA(new RegisterOffset(classDef.getOffsetGB(), compiler.getRegisterSet().GB), compiler.getRegisterSet().R1));
		
		// Début de la boucle
		compiler.addLabel(loopLabel);

		// On récupère la superclasse de l'objet (ou la classe si on en est à la première passe)
		compiler.addInstruction(new LOAD(new RegisterOffset(0, exprReg.getRegister(compiler, true)), exprReg.getRegister(compiler, true)));
		// On vérifie si on est pas arrivé au bout de l'héritage de l'objet
		compiler.addInstruction(new CMP(new NullOperand(), exprReg.getRegister(compiler, true)));
		if(flow.getBranchCond())
			compiler.addInstruction(new BEQ(okLabel));
		else
			compiler.addInstruction(new BEQ(flow.getBranchLabel()));

		// On vérifie si la les classes correspondent
		compiler.addInstruction(new CMP(compiler.getRegisterSet().R1, exprReg.getRegister(compiler, true)));
		if(flow.getBranchCond())
			compiler.addInstruction(new BEQ(flow.getBranchLabel()));
		else
			compiler.addInstruction(new BEQ(okLabel));

		// Si aucun des deux, on reboucle
		compiler.addInstruction(new BRA(loopLabel));

		compiler.addLabel(okLabel);
		exprReg.free(compiler);
	}

	@Override
	protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
    	Label falseLbl = new Label("instanceOfFalse.n"+Integer.toString(compiler.getCounterManager().addOne("instanceOf")));
		Label endLbl = new Label("instanceOfEnd.n"+Integer.toString(compiler.getCounterManager().last("instanceOf")));
    	BoolCtrlFlow flow = new BoolCtrlFlow(false, falseLbl);
    	this.codeGenBoolIMA(compiler, flow);
		
		VirtualRegister resultReg = compiler.getRegistersHandler().getNewRegister(compiler);

    	compiler.addInstruction(new LOAD(new ImmediateInteger(1), resultReg.getRegister(compiler, false)));
		compiler.addInstruction(new BRA(endLbl));
		compiler.addLabel(falseLbl);
		compiler.addInstruction(new LOAD(new ImmediateInteger(0), resultReg.getRegister(compiler, false)));
		compiler.addLabel(endLbl);
		
    	return resultReg;
    }
}
