package fr.ensimag.deca.codegen;

import org.apache.commons.lang.Validate;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.gba.GRegisterOffset;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

public class VirtualRegisterOffset extends VirtualDAddr {
    @Override
    public boolean isRegisterOffset() {
        return true;
    }

    private VirtualRegister register;
    private int offset; 
    private boolean usable = true;
    
    public VirtualRegisterOffset(RegistersHandler handler, VirtualRegister register, int offset) {
        super(handler);

        this.register = register;
        this.offset = offset;
    }

    @Override
    public DAddr getDAddr(DecacCompiler compiler, boolean canBeR0) {
        Validate.isTrue(usable);
        if(compiler.getCompilerOptions().isForGBA())
        	return new GRegisterOffset(register.getRegister(compiler, canBeR0), offset);
        else
        	return new RegisterOffset(offset, register.getRegister(compiler, canBeR0));
    }
    
    @Override
    public DVal getDVal(DecacCompiler compiler, boolean canBeR0) {
        return this.getDAddr(compiler, canBeR0);
    }

    @Override
    public void free(DecacCompiler compiler) {
        
        this.register.free(compiler);
    }

    @Override
    public VirtualRegister getVRegister(DecacCompiler compiler) {
        // VirtualRegister vRegister = handler.getNewRegister(compiler);
        Validate.isTrue(usable);
        if(compiler.getCompilerOptions().isForGBA())
        	compiler.addInstruction(new GLDR(register.getRegister(compiler, false), this.getDAddr(compiler, false)));
        else
        	compiler.addInstruction(new LOAD(this.getDAddr(compiler, false), register.getRegister(compiler, false)));
        usable = false;
        return register;
    }

    public VirtualRegister getOffsetRegister(DecacCompiler compiler) {
        Validate.isTrue(usable);
        return register;
    }
}
