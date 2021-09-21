package fr.ensimag.deca.tree;

import java.io.PrintStream;

import org.apache.commons.lang.Validate;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.BoolCtrlFlow;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.TypeTools;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.ShiftType;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.WFLOAT;
import fr.ensimag.pseudocode.ima.instructions.WFLOATX;
import fr.ensimag.pseudocode.ima.instructions.WINT;

/**
 * Expression, i.e. anything that has a value.
 *
 * @author gl27
 * @date 01/01/2021
 */
public abstract class AbstractExpr extends AbstractInst {
    /**
     * @return true if the expression does not correspond to any concrete token
     * in the source code (and should be decompiled to the empty string).
     */
    boolean isImplicit() {
        return false;
    }

    /**
     * Get the type decoration associated to this expression (i.e. the type computed by contextual verification).
     */
    public Type getType() {
        return type;
    }

    protected void setType(Type type) {
        Validate.notNull(type);
        this.type = type;
    }
    private Type type;

    @Override
    protected void checkDecoration() {
        if (getType() == null) {
            throw new DecacInternalError("Expression " + decompile() + " has no Type decoration");
        }
    }

    /**
     * Verify the expression for contextual error.
     * 
     * implements non-terminals "expr" and "lvalue" 
     *    of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler  (contains the "env_types" attribute)
     * @param localEnv
     *            Environment in which the expression should be checked
     *            (corresponds to the "env_exp" attribute)
     * @param currentClass
     *            Definition of the class containing the expression
     *            (corresponds to the "class" attribute)
     *             is null in the main bloc.
     * @return the Type of the expression
     *            (corresponds to the "type" attribute)
     */
    public abstract Type verifyExpr(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError;

    /**
     * Verify the expression in right hand-side of (implicit) assignments 
     * 
     * implements non-terminal "rvalue" of [SyntaxeContextuelle] in pass 3
     *
     * @param compiler  contains the "env_types" attribute
     * @param localEnv corresponds to the "env_exp" attribute
     * @param currentClass corresponds to the "class" attribute
     * @param expectedType corresponds to the "type1" attribute            
     * @return this with an additional ConvFloat if needed...
     */
    public AbstractExpr verifyRValue(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass, 
            Type expectedType)
            throws ContextualError {
            
            Type exprType = this.verifyExpr(compiler, localEnv, currentClass);        
            if(expectedType.isFloat() && exprType.isInt()) {
                ConvFloat conv = new ConvFloat(this);
                conv.verifyExpr(compiler, localEnv, currentClass);
     //           this.setType(conv.getType());
                return conv;
            }
            
            if(!TypeTools.assign_compatible(compiler, expectedType, exprType))
                throw new ContextualError("RValue's type is not compatible with the expected type (type : " + exprType.getName() + "; expectedType :" + expectedType.getName() + ")", this.getLocation());

            return this;
        }
    
    
    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        this.verifyExpr(compiler, localEnv, currentClass);
    }

    /**
     * Verify the expression as a condition, i.e. check that the type is
     * boolean.
     *
     * @param localEnv
     *            Environment in which the condition should be checked.
     * @param currentClass
     *            Definition of the class containing the expression, or null in
     *            the main program.
     */
    public void verifyCondition(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
            
        Type exprType = this.verifyExpr(compiler, localEnv, currentClass);
        if(!(exprType.isBoolean()))
            throw new ContextualError("Condition's type must be boolean (current : " + exprType.getName() + ").", this.getLocation());
    }

    /**
     * Generate code to print the expression
     *
     * @param compiler
     */
    protected void codeGenPrintIMA(DecacCompiler compiler, boolean hex) {
        VirtualDVal virtualOperand = this.codeGenExprIMA(compiler);
        
        if(this.getType().isInt()) {
        	compiler.addInstruction(new LOAD(virtualOperand.getDVal(compiler, true), compiler.getRegisterSet().R1));
        	compiler.addInstruction(new WINT());
        } else if(this.getType().isFloat()) {
        	compiler.addInstruction(new LOAD(virtualOperand.getDVal(compiler, true), compiler.getRegisterSet().R1));
        	if(hex)
        		compiler.addInstruction(new WFLOATX());
        	else
        		compiler.addInstruction(new WFLOAT());
        } else
        	assert(false); // Programmation défensive : l'erreur doit avoir été catch dans l'étape B
        virtualOperand.free(compiler);
    }
    
    protected void codeGenPrintGBA(DecacCompiler compiler) {
    	// String prints are catched in an inherited class
    	Validate.isTrue(this.getType().isInt(), "Seuls les types int et string sont supportes sur GBA.");

    	VirtualDVal operand = this.codeGenExprGBA(compiler);
    	if(this.getType().isInt()) {
    		compiler.addInstruction(new GMOV(compiler.getRegisterSet().R1, new GOp2(operand.getDVal(compiler, true))));
        	compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("printint")));
        }
        operand.free(compiler);
    }

    @Override
    protected void codeGenInstIMA(DecacCompiler compiler) {
        VirtualDVal exprVal = this.codeGenExprIMA(compiler);
        exprVal.free(compiler);
    }
    
    @Override
    protected void codeGenInstGBA(DecacCompiler compiler) {
    	this.codeGenExprGBA(compiler);
    }
    

    @Override
    protected void decompileInst(IndentPrintStream s) {
        decompile(s);
        s.print(";");
    }

    @Override
    protected void prettyPrintType(PrintStream s, String prefix) {
        Type t = getType();
        if (t != null) {
            s.print(prefix);
            s.print("type: ");
            s.print(t);
            s.println();
        }
    }
    
    protected boolean hexMode = false;
    public void setHexMode() {
    	hexMode = true;
    }
    

    protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
        // Must be overrided
        assert(false);
    	return null;
    }
    
    protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
    	// Must be overrided
    	assert(false);
    	return null;
    }
    
    protected void codeGenBoolIMA(DecacCompiler compiler, BoolCtrlFlow flow) {
		assert(false);
	}
    
    protected void codeGenBoolGBA(DecacCompiler compiler, BoolCtrlFlow flow) {
		assert(false);
	}
    	
}
