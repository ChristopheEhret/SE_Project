package fr.ensimag.pseudocode;

public abstract class AbstractRegisterSet {
	
	/**
     * Global Base register
     */
    public final Register GB = initGB();
    /**
     * Local Base register
     */
    public final Register LB = initLB();
    /**
     * Stack Pointer
     */
    public final Register SP = initSP();
    /**
     * Link register
     */
    public final Register LR = initLR();
    /**
     * Program Counter
     */
    public final Register PC = initPC();
    
    /**
     * General Purpose Registers. Array is private because Java arrays cannot be
     * made immutable, use getR(i) to access it.
     */
    private final GPRegister[] R = initRegisters();
    
    /**
     * Convenience shortcut for R[0]
     */
    public final GPRegister R0 = R[0];
    /**
     * Convenience shortcut for R[1]
     */
    public final GPRegister R1 = R[1];
    
    /**
     * General Purpose Registers
     */
    public GPRegister getR(int i) {
        return R[i];
    }
    
    protected abstract GPRegister[] initRegisters();
    
    protected abstract Register initGB();
    
    protected abstract Register initLB();
    
    protected abstract Register initSP();
    
    protected abstract Register initLR();
    
    protected abstract Register initPC();
    
}
