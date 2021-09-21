package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.DAddr;

public abstract class VirtualDAddr extends VirtualDVal {
    public VirtualDAddr(RegistersHandler handler) {
        super(handler);
    }

    public abstract DAddr getDAddr(DecacCompiler compiler, boolean canBeR0);
    
    @Override
    public boolean isDAddr() {
    	return true;
    }
}
