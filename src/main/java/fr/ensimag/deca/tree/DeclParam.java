package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.ParamDefinition;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.VoidType;


public class DeclParam extends AbstractDeclParam {
	
	private AbstractIdentifier paramType;
	private AbstractIdentifier paramName;	
	
	
	public DeclParam(AbstractIdentifier paramType, AbstractIdentifier paramName) {
		super();
		this.paramType = paramType;
		this.paramName = paramName;
	}
	/**
     * Pass 2 , rule 2.9 of [SyntaxeContextuelle] 
     */
	@Override
	protected Type verifyDeclParam(DecacCompiler compiler) 
			throws ContextualError {
		Type type = paramType.verifyType(compiler);
		 if(type instanceof VoidType) {
	            throw new ContextualError("Cannot declare a parameter of type void", this.getLocation());
		 }
		return type;
	}
	/**
     * Pass 3 , rule 3.13 of [SyntaxeContextuelle] 
     */
	@Override
	protected  ParamDefinition verifyParamBody(DecacCompiler compiler) 
            throws ContextualError{
		Type type = this.verifyDeclParam(compiler);
		ParamDefinition paramDefinition = new ParamDefinition(type,this.getLocation());
		paramName.setDefinition(paramDefinition);
		return paramDefinition;
	}
	
	@Override
	public void codeGenDeclIMA(DecacCompiler compiler, VirtualDAddr operand) {
		paramName.getExpDefinition().setOperand(operand);
	}
	
	@Override
	public void codeGenDeclGBA(DecacCompiler compiler, VirtualDAddr operand) {
		paramName.getExpDefinition().setOperand(operand);
	}
	
	public void decompile(IndentPrintStream s) {
		paramType.decompile(s);
		s.print(" ");
		paramName.decompile(s);
   	}
	@Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    	paramType.prettyPrint(s,prefix,false);
    	paramName.prettyPrint(s,prefix,true);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    	paramType.iter(f);
    	paramName.iter(f);
    }
    @Override
	public AbstractIdentifier getParamName() {
		return paramName;
	}	

}
