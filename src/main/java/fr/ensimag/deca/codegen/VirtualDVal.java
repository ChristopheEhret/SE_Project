package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.DVal;

public abstract class VirtualDVal {
    RegistersHandler handler;

    public VirtualDVal(RegistersHandler handler) {
        this.handler = handler;
    }

    abstract public DVal getDVal(DecacCompiler compiler, boolean canBeR0); // ENDPOINT

    abstract public VirtualRegister getVRegister(DecacCompiler compiler); // ENDPOINT

    public void free(DecacCompiler compiler) {} // ENDPOINT
    
    public boolean isDAddr() {
    	return false;
    }

    public boolean isImmediate() {
        return false;
    }

    public boolean isRegister() {
        return false;
    }

    public boolean isRegisterOffset() {
        return false;
    }
}
