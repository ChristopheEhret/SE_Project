package fr.ensimag.deca.codegen;

import fr.ensimag.pseudocode.Label;

public class Sprite {
	private final Label lbl;
	private final String binPath;
	private final int sizex, sizey;
	
	public Sprite(Label lbl, String binPath, int sizex, int sizey) {
		this.binPath = binPath;
		this.sizex = sizex;
		this.sizey = sizey;
		this.lbl = lbl;
	}

	public String getBinPath() {
		return binPath;
	}

	public int getSizex() {
		return sizex;
	}

	public int getSizey() {
		return sizey;
	}

	public Label getLabel() {
		return lbl;
	}

}
