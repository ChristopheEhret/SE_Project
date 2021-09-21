package fr.ensimag.deca.codegen;

public class Counter {
	private int nb = 0;
	
	public int addOne() {
		return ++nb;
	}
	
	public int last() {
		return nb;
	}
}
