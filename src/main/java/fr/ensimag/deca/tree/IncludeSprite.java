package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDVal;
import fr.ensimag.deca.codegen.VirtualIntImmediate;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.ima.IMAInternalError;

/**
 * @author gl27
 * @date 01/01/2021
 */
public class IncludeSprite extends AbstractExpr {
	private StringLiteral path;
	private IntLiteral sizex;
	private IntLiteral sizey;
	
    /**
     * @param arguments arguments passed to the includeSprite(...) statement.
     */
    public IncludeSprite(StringLiteral path, IntLiteral sizex, IntLiteral sizey) {
        this.path = path;
        this.sizex = sizex;
        this.sizey = sizey;
    }

	@Override
	public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass)
			throws ContextualError {
		this.setType(sizex.getType());
		return sizex.getType();
	}

	@Override
	public void decompile(IndentPrintStream s) {
		s.print("includeSprite(");
		path.decompile();
		s.print(", ");
		sizex.decompile();
		s.print(", ");
		sizey.decompile();
		s.print(")");
	}

	@Override
	protected void prettyPrintChildren(PrintStream s, String prefix) {
		path.prettyPrint(s, prefix, false);
		sizex.prettyPrint(s, prefix, false);
		sizey.prettyPrint(s, prefix, true);
	}

	@Override
	protected void iterChildren(TreeFunction f) {
		path.iter(f);
		sizex.iter(f);
		sizey.iter(f);
	}
	
	@Override
	protected VirtualDVal codeGenExprIMA(DecacCompiler compiler) {
		throw new IMAInternalError("IncludeSprite n'est pas supporté pour IMA.");
	}
	
	
	@Override
	protected VirtualDVal codeGenExprGBA(DecacCompiler compiler) {
		final int id = compiler.getDataManager().addSprite(path.getValue(), sizex.getValue(), sizey.getValue());
		return new VirtualIntImmediate(compiler.getRegistersHandler(), id);
	}
	
}
