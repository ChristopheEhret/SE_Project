package fr.ensimag.deca.codegen;

import java.util.HashMap;

public class CounterManager {
	private HashMap<String, Counter> counters;
	
	public CounterManager() {
		counters = new HashMap<String, Counter>();
	}
	
	public void addCounter(String id) {
		assert(!counters.containsKey(id));
		counters.put(id, new Counter());
	}
	
	public int addOne(String id) {
		assert(counters.containsKey(id));
		return counters.get(id).addOne();
	}
	
	public int last(String id) {
		assert(counters.containsKey(id));
		return counters.get(id).last();
	}
}
