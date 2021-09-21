package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.AsmProgram;
import fr.ensimag.pseudocode.gba.GCondition;
import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GCMP;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.ima.instructions.BOV;
import fr.ensimag.pseudocode.ima.instructions.TSTO;

public class StackOverflowHandler {
	private int nbVars;
	private int maxParameters;
	private int maxTemps;
	
	public StackOverflowHandler() {
		nbVars = 0;
		maxParameters = -1;
		maxTemps = 0;
	}
	
	public void setNbVars(int nv) {
		nbVars = nv;
	}
	
	public int getNbVars() {
		return nbVars;
	}
	
	public void addMethode(int params) {
		if(params>maxParameters)
			maxParameters = params;
	}
	
	public void insertCheck(DecacCompiler compiler, boolean main) {
		final AsmProgram block = compiler.getCurrentBlock();
		final int usedRegisters = compiler.getRegistersHandler().getMaxLowerReg();
		maxTemps = compiler.getRegistersHandler().getMaxPush();
		if(!compiler.getCompilerOptions().isForGBA()) {
			final int toCheck = (main || (usedRegisters<2) ? 0 : usedRegisters-1) + nbVars + maxTemps + (maxParameters!=-1 ? maxParameters+2 : 0);
			if(toCheck>0) {
				block.addFirst(new BOV(compiler.getErrorManager().getErrorLabel("StackOverflow")));
				block.addFirst(new TSTO(toCheck));
			}
		} else {
			final int toCheck = (main || (usedRegisters<2) ? 0 : (usedRegisters-1)*4) + nbVars + maxTemps*4 + (maxParameters!=-1 ? (maxParameters+2)*4 : 0);
			if(toCheck>0) {
				block.addFirst(new GB(compiler.getErrorManager().getErrorLabel("StackOverflow"), GCondition.VS));
				block.addFirst(new GCMP(compiler.getRegisterSet().SP, compiler.getRegisterSet().R0));
				block.addFirst(new GLDR(compiler.getRegisterSet().R0, new GImmediate32BitInteger(0x3000050+toCheck)));
			}
		}
	}
	
}
