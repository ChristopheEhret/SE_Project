package fr.ensimag.deca.context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.SymbolTable.Symbol;

/**
 * Dictionary associating identifier's ExpDefinition to their names.
 * 
 * This is actually a linked list of dictionaries: each EnvironmentExp has a
 * pointer to a parentEnvironment, corresponding to superblock (eg superclass).
 * 
 * The dictionary at the head of this list thus corresponds to the "current" 
 * block (eg class).
 * 
 * Searching a definition (through method get) is done in the "current" 
 * dictionary and in the parentEnvironment if it fails. 
 * 
 * Insertion (through method declare) is always done in the "current" dictionary.
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class EnvironmentExp {
    // A FAIRE : implémenter la structure de donnée représentant un
    // environnement (association nom -> définition, avec possibilité
    // d'empilement).

    EnvironmentExp parentEnvironment;
    public EnvironmentExp(EnvironmentExp parentEnvironment) {
        this.parentEnvironment = parentEnvironment;
    }

    public static class DoubleDefException extends Exception {
        private static final long serialVersionUID = -2733379901827316441L;
    }

    HashMap<Symbol, ExpDefinition> envMap = new HashMap<Symbol, ExpDefinition>();

    /**
     * Return the definition of the symbol in the environment, or null if the
     * symbol is undefined.
     */
    public ExpDefinition get(Symbol key) {
        // if(!envMap.containsKey(key))
        //     /* TODO : trouver une meilleure exception pour soulever l'erreur (ContextError?) */
        //     throw new DecacInternalError("ERREUR TEMPORAIRE : " + key + " n'est pas une expression valide!!");

        ExpDefinition expDef = envMap.get(key);
    	if(parentEnvironment == null) {  // Classe Object
    		return expDef;
    	}
    	else if (expDef != null) {
    		return expDef;  		
    	}
    	else {
        	return parentEnvironment.get(key);    		
    	}
    }

    /**
     * Add the definition def associated to the symbol name in the environment.
     * 
     * Adding a symbol which is already defined in the environment,
     * - throws DoubleDefException if the symbol is in the "current" dictionary 
     * - or, hides the previous declaration otherwise.
     * 
     * @param name
     *            Name of the symbol to define
     * @param def
     *            Definition of the symbol
     * @throws DoubleDefException
     *             if the symbol is already defined at the "current" dictionary
     *
     */
    public void declare(Symbol name, ExpDefinition def) throws DoubleDefException {
        if(envMap.containsKey(name))
            throw new DoubleDefException();
            
        envMap.put(name, def);
    }

    public void getAllMethods(MethodDefinition[] methods) {
        if(parentEnvironment != null) 
            parentEnvironment.getAllMethods(methods);

        for(Map.Entry<Symbol, ExpDefinition> def : envMap.entrySet()) {
            if(def.getValue().isMethod()) {
                MethodDefinition mDef = (MethodDefinition) def.getValue();
                methods[mDef.getIndex() - 1] = mDef;
            }
        }
    }
    
	public void setEnvMap(HashMap<Symbol, ExpDefinition> envMap) {
		this.envMap = envMap;
	}

	public HashMap<Symbol, ExpDefinition> getEnvMap() {
		return envMap;
	}
    public ExpDefinition getOnlyCurrent(Symbol key) {
        return envMap.get(key);

    }
}
