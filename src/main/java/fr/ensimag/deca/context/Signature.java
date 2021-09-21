package fr.ensimag.deca.context;

import java.util.ArrayList;
import java.util.List;

/**
 * Signature of a method (i.e. list of arguments)
 *
 * @author gl27
 * @date 01/01/2021
 */
public class Signature {
    List<Type> args = new ArrayList<Type>();

    public void add(Type t) {
        args.add(t);
    }
    
    public Type paramNumber(int n) {
        return args.get(n);
    }
    
    public int size() {
        return args.size();
    }
    public boolean isSameSignature(Signature s2) {
    	int size = this.size();
    	if(size != s2.size()) {
    		return false;
    	}
    	else {
    		for(int i = 0;i<size;i++) {
    			if(!this.paramNumber(i).sameType(s2.paramNumber(i))) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
}
