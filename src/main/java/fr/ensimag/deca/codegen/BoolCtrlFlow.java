package fr.ensimag.deca.codegen;

import fr.ensimag.pseudocode.Label;

public class BoolCtrlFlow {
	private boolean branchCond;
	private Label branchLabel;
	
	public BoolCtrlFlow(boolean branchCond, Label branchLabel) {
		this.branchCond = branchCond;
		this.branchLabel = branchLabel;
	}
	
	public void not() {
		branchCond = !branchCond;
	}
	
	public Label getBranchLabel() {
		return branchLabel;
	}
	public boolean getBranchCond() {
		return branchCond;
	}
	public void setBranchLabel(Label branchLabel) {
		this.branchLabel = branchLabel ;
	}
}
