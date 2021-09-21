package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

public class VirtualIntImmediate extends VirtualDVal {
    @Override
    public boolean isImmediate() {
        return true;
    }

    int value;
    public VirtualIntImmediate(RegistersHandler handler, int value) {
        super(handler);

        this.value = value;
    }

    public DVal getDVal(DecacCompiler compiler, boolean canBeR0) {
    	if(compiler.getCompilerOptions().isForGBA()) {
    		GOp2 imm = GOp2.convertOp2(value);
    		assert(imm!=null);
    		return imm;
    	} else {
    		return new ImmediateInteger(value);
    	}
    }

    public VirtualRegister getVRegister(DecacCompiler compiler) {
        VirtualRegister vReg = handler.getNewRegister(compiler);
        if(!vReg.isInRegister())
            throw new InternalError("Loaded a new VirtualRegister that is not tied to an actual register");

        if(compiler.getCompilerOptions().isForGBA())
        	compiler.addInstruction(new GMOV(vReg.getRegister(compiler, false), GOp2.convertOp2(value)));
        else
        	compiler.addInstruction(new LOAD(new ImmediateInteger(value), vReg.getRegister(compiler, false)));

        return vReg;
    }
}
