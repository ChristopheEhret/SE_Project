package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.ASMLine;
import fr.ensimag.pseudocode.AsmProgram;
import fr.ensimag.pseudocode.Line;
import fr.ensimag.pseudocode.ima.IMALine;
import fr.ensimag.pseudocode.ima.instructions.RTS;
import org.apache.commons.lang.StringEscapeUtils;


public class MethodAsm extends AbstractMethodBody {

	private StringLiteral asmString;
	
	
	public MethodAsm(StringLiteral asmString) {
		super();
		this.asmString = asmString;
	}

	@Override
	protected void verifyMethodBody(DecacCompiler compiler, EnvironmentExp localEnv,EnvironmentExp envExpParam ,ClassDefinition currentClass,Type returnType)
			throws ContextualError {
		asmString.verifyExpr(compiler, localEnv, currentClass);
	}

	@Override
	protected void codeGenBodyIMA(DecacCompiler compiler, AbstractIdentifier typeReturned, String methodName) {
		compiler.add(new ASMLine(StringEscapeUtils.unescapeJava(asmString.getValue())));
	}
	
	@Override
	protected void codeGenBodyGBA(DecacCompiler compiler, AbstractIdentifier typeReturned, String methodName) {
		compiler.add(new ASMLine(StringEscapeUtils.unescapeJava(asmString.getValue())));
	}
	
	public void decompile(IndentPrintStream s) {
		s.print("asm(");
		asmString.decompile(s);
		s.print(");");
	}
	public void prettyPrintChildren(PrintStream s, String prefix) {
		asmString.prettyPrint(s,prefix,true);
		
	}
	public void iterChildren(TreeFunction f) {
		asmString.iter(f);
	}

}
