package fr.ensimag.deca.context;

import fr.ensimag.deca.DecacCompiler;

public class TypeTools {
    public static boolean assign_compatible(DecacCompiler compiler,Type t1, Type t2) {
        if(t1 == null || t2 == null) {
        	return false;
        }
    	if(t1.getName().getName()==t2.getName().getName()) {
    		return true;   		
    	}

        if(t1 instanceof FloatType && t2 instanceof IntType)
            return true;
        
        if(t1.isClass() && t2.isClass()) {
        	ClassType class1 = (ClassType) t1;
        	ClassType class2 = (ClassType) t2;
        	if(class2.isSubClassOf(class1)) {
        		return true;
        	}
        }
        return false;
    }

    public static boolean cast_compatible(DecacCompiler compiler, Type t1, Type t2) {
        if(t1 instanceof VoidType)
            return false;

        return assign_compatible(compiler, t1, t2) || assign_compatible(compiler, t2, t1);
    }
}
