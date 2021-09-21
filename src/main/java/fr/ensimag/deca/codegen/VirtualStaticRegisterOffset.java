package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GRegisterOffset;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

public class VirtualStaticRegisterOffset extends VirtualDAddr{
    private final Register register;
    private final int offset;
    
    public VirtualStaticRegisterOffset(RegistersHandler handler, Register register, int offset) {
        super(handler);
        
        this.register = register;
        this.offset = offset;
    }

    @Override
    public DAddr getDAddr(DecacCompiler compiler, boolean canBeR0) {
    	if(compiler.getCompilerOptions().isForGBA())
    		return new GRegisterOffset(register, offset);
    	else
    		return new RegisterOffset(offset, register);
    }

    @Override
    public DVal getDVal(DecacCompiler compiler, boolean canBeR0) {
        return this.getDAddr(compiler, canBeR0);
    }

    @Override
    public VirtualRegister getVRegister(DecacCompiler compiler) {
        VirtualRegister vRegister = handler.getNewRegister(compiler);
        
        if(compiler.getCompilerOptions().isForGBA())
        	compiler.addInstruction(new GLDR(vRegister.getRegister(compiler, false), this.getDAddr(compiler, true)));
        else
        	compiler.addInstruction(new LOAD(this.getDAddr(compiler, true), vRegister.getRegister(compiler, false)));
        return vRegister;
    }
}
