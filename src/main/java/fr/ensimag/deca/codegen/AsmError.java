package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GLabelOperand;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.ima.instructions.ERROR;
import fr.ensimag.pseudocode.ima.instructions.WNL;
import fr.ensimag.pseudocode.ima.instructions.WSTR;

public class AsmError {
	private String errorLblStr;
	private Label errorLbl;
	private String errMsg;
	
	public AsmError(String errorLbl, String errMsg) {
		this.errorLbl = new Label(errorLbl);
		this.errMsg = errMsg;
		this.errorLblStr = errorLbl;
	}
	
	public void codeGenError(DecacCompiler compiler) {
		compiler.addLabel(errorLbl);
		if(compiler.getCompilerOptions().isForGBA()) {
			Label lblErr = compiler.getDataManager().addString("Erreur : " + errMsg);
			compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GLabelOperand(lblErr)));
			compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("printstr")));
			Label endLoopInf = new Label(errorLblStr+".loopInf");
	        compiler.addLabel(endLoopInf);
	        compiler.addInstruction(new GB(endLoopInf));
		} else {
			compiler.addInstruction(new WSTR("Erreur : " + errMsg));
			compiler.addInstruction(new WNL());
			compiler.addInstruction(new ERROR());
		}
	}
	
	public Label getErrorLabel() {
		return errorLbl;
	}
}
