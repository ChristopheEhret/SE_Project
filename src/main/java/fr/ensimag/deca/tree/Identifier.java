package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.codegen.VirtualRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.FieldDefinition;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.VariableDefinition;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GCMP;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.instructions.BEQ;
import fr.ensimag.pseudocode.ima.instructions.BNE;
import fr.ensimag.pseudocode.ima.instructions.BRA;
import fr.ensimag.pseudocode.ima.instructions.CMP;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * Deca Identifier
 *
 * @author gl27
 * @date 01/01/2021
 */
public class Identifier extends AbstractIdentifier {
   
    @Override
    protected void checkDecoration() {
        if (getDefinition() == null) {
            throw new DecacInternalError("Identifier " + this.getName() + " has no attached Definition");
        }
    }

    @Override
    public Definition getDefinition() {
        return definition;
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * ClassDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a class definition.
     */
    @Override
    public ClassDefinition getClassDefinition() {
        try {
            return (ClassDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a class identifier, you can't call getClassDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * MethodDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a method definition.
     */
    @Override
    public MethodDefinition getMethodDefinition() {
        try {
            return (MethodDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a method identifier, you can't call getMethodDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * FieldDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a field definition.
     */
    @Override
    public FieldDefinition getFieldDefinition() {
        try {
            return (FieldDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a field identifier, you can't call getFieldDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * VariableDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a field definition.
     */
    @Override
    public VariableDefinition getVariableDefinition() {
        try {
            return (VariableDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a variable identifier, you can't call getVariableDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a ExpDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *             if the definition is not a field definition.
     */
    @Override
    public ExpDefinition getExpDefinition() {
        try {
            return (ExpDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a Exp identifier, you can't call getExpDefinition on it");
        }
    }

    @Override
    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    @Override
    public Symbol getName() {
        return name;
    }

    private Symbol name;

    public Identifier(Symbol name) {
        Validate.notNull(name);
        this.name = name;
    }

    /*
    * Implements rule (0.1) 
    */
    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        LOG.debug("Verify identifier as Expr");
        
        // Symbol s = compiler.getOrAddSymbol(this.getName().getName());
        ExpDefinition def = localEnv.get(this.getName());
        if(def == null) {
            throw new ContextualError("Expression identifier " + this.getName() + " is not declared in this scop ", this.getLocation());
        }
      

        this.setDefinition(def);
        this.setType(def.getType());

        // TODO : Je pense que c'est faux ? -> d'après doc, il faudrait renvoyer directement la definition
        return definition.getType();
    }

    /**
     * Implements rule (0.2)
     * @param compiler contains "env_types" attribute
     */
    @Override
    public Type verifyType(DecacCompiler compiler) throws ContextualError {
        LOG.debug("Verify identifier as Type");

        // Symbol s = compiler.getOrAddSymbol(this.getName().getName());
        TypeDefinition def = compiler.getTypeDef(this.getName());
        if(def == null) {
            throw new ContextualError("Type identifier " + this.getName() + " is not declared. ", this.getLocation());
        }

        this.setDefinition(def);
        this.setType(def.getType());

        return definition.getType();
    }
    
    
    private Definition definition;


    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(name.toString());
    }

    @Override
    String prettyPrintNode() {
        return "Identifier (" + getName() + ")";
    }

    @Override
    protected void prettyPrintType(PrintStream s, String prefix) {
        Definition d = getDefinition();
        if (d != null) {
            s.print(prefix);
            s.print("definition: ");
            s.print(d);
            s.println();
        }
    }
    
    @Override
    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
        return getExpDefinition().getOperand(compiler);
    }
    
    @Override
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
        VirtualDAddr lOperand = getExpDefinition().getOperand(compiler);
        
        VirtualRegister boolReg = compiler.getRegistersHandler().getNewRegister(compiler);
        compiler.addInstruction(new LOAD(lOperand.getDAddr(compiler, true), boolReg.getRegister(compiler, true)));
    	compiler.addInstruction(new CMP(new ImmediateInteger(0), boolReg.getRegister(compiler, true)));
        boolReg.free(compiler);
        lOperand.free(compiler);
    	
    	if(flow.getBranchCond())
    		compiler.addInstruction(new BNE(flow.getBranchLabel()));
    	else
            compiler.addInstruction(new BEQ(flow.getBranchLabel()));
            
    }
    
    @Override
    protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
        VirtualDAddr lOperand = getExpDefinition().getOperand(compiler);
        
        VirtualRegister boolReg = compiler.getRegistersHandler().getNewRegister(compiler);
        compiler.addInstruction(new GLDR(boolReg.getRegister(compiler, true), lOperand.getDAddr(compiler, true)));
    	compiler.addInstruction(new GCMP(boolReg.getRegister(compiler, true), GOp2.convertOp2(0)));
        boolReg.free(compiler);
        lOperand.free(compiler);
    	
    	if(flow.getBranchCond())
    		compiler.addInstruction(new GB(flow.getBranchLabel(), GCondition.NE));
    	else
            compiler.addInstruction(new GB(flow.getBranchLabel(), GCondition.EQ));
            
    }
    
    @Override
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
    	VirtualRegister vval = compiler.getRegistersHandler().getNewRegister(compiler);
		compiler.addInstruction(new GLDR(vval.getRegister(compiler, false), getExpDefinition().getOperand(compiler).getDAddr(compiler, false)));
		return vval;
    }

	@Override
	protected VirtualDAddr codeGenAssign(DecacCompiler compiler) {
		return this.getExpDefinition().getOperand(compiler);
	}

}
