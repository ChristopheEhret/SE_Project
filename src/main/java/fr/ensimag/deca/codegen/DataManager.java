package fr.ensimag.deca.codegen;

import java.util.HashMap;

import org.apache.commons.lang.Validate;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.instructions.GBYTE;
import fr.ensimag.pseudocode.gba.instructions.GINCBIN;
import fr.ensimag.pseudocode.gba.instructions.GWORD;

public class DataManager {
	private HashMap<String, Label> strings;
	private HashMap<Integer, Label> integers;
	private HashMap<Integer, Sprite> sprites;
	private int nbS, nbI, nbB;
	
	public DataManager() {
		strings = new HashMap<String, Label>();
		integers = new HashMap<Integer, Label>();
		sprites = new HashMap<Integer, Sprite>();
		nbS = 0;
		nbI = 0;
		nbB = 0;
	}
	
	public Label addString(String id) {
		if(strings.containsKey(id))
			return strings.get(id);
		final Label lbl = new Label("data.string"+(++nbS));
		strings.put(id, lbl);
		return lbl;
	}
	
	public Label addInteger(int id) {
		if(integers.containsKey(id))
			return integers.get(id);
		final Label lbl = new Label("data.numeral"+(++nbI));
		integers.put(id, lbl);
		return lbl;
	}
	
	public int addSprite(String binPath, int sizex, int sizey) {
		final Label lbl = new Label("data.bin"+(++nbB));
		final Sprite spr = new Sprite(lbl, binPath, sizex, sizey);
		sprites.put(nbB, spr);
		return nbB;
	}
	
	public Sprite getSprite(int id) {
		Validate.isTrue(sprites.containsKey(id));
		return sprites.get(id);
	}
	
	public void insertData(DecacCompiler compiler) {
		compiler.addEmptyLine();
		compiler.addComment("*************** DATA **************");
		compiler.addComment("Strings");
		for(HashMap.Entry<String, Label> e : strings.entrySet()) {
			compiler.addLabel(e.getValue());
			compiler.addInstruction(GBYTE.createString(e.getKey()));
		}
		
		compiler.addEmptyLine();
		compiler.addComment("Numerals");
		for(HashMap.Entry<Integer, Label> e : integers.entrySet()) {
			compiler.addLabel(e.getValue());
			compiler.addInstruction(new GWORD(Integer.toString(e.getKey())));
		}
		
		compiler.addEmptyLine();
		compiler.addComment("Bins");
		for(HashMap.Entry<Integer, Sprite> e : sprites.entrySet()) {
			compiler.addLabel(e.getValue().getLabel());
			compiler.addInstruction(new GBYTE(Integer.toString(e.getValue().getSizex())));
			compiler.addInstruction(new GBYTE(Integer.toString(e.getValue().getSizey())));
			compiler.addInstruction(new GINCBIN(e.getValue().getBinPath()));
		}
		
		strings = new HashMap<String, Label>();
		integers = new HashMap<Integer, Label>();
		sprites = new HashMap<Integer, Sprite>();
	}
	
}
