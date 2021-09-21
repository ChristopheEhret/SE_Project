package fr.ensimag.deca.codegen;

import java.util.HashMap;

import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.Label;

public class AsmRoutineManager {
	private HashMap<String, Label> routines;
	
	public AsmRoutineManager() {
		routines = new HashMap<String, Label>();
	}
	
	public void addRoutine(String id, Label lbl) {
		Validate.isTrue(!routines.containsKey(id));
		routines.put(id, lbl);
	}
	
	public Label getRoutine(String id) {
		Validate.isTrue(routines.containsKey(id));
		return routines.get(id);
	}
	
}
