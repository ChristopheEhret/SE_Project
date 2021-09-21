package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.ima.ImmediateFloat;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

public class VirtualFloatImmediate extends VirtualDVal {
    @Override
    public boolean isImmediate() {
        return true;
    }

    float value;
    public VirtualFloatImmediate(RegistersHandler handler, float value) {
        super(handler);

        this.value = value;
    }

    public DVal getDVal(DecacCompiler compiler, boolean canBeR0) {
    	assert(!compiler.getCompilerOptions().isForGBA()); // not implemented
        return new ImmediateFloat(value);
    }

    public VirtualRegister getVRegister(DecacCompiler compiler) {
    	assert(!compiler.getCompilerOptions().isForGBA()); // not implemented
        VirtualRegister vReg = handler.getNewRegister(compiler);
        if(!vReg.isInRegister())
            throw new InternalError("Loaded a new VirtualRegister that is not tied to an actual register");

        compiler.addInstruction(new LOAD(new ImmediateFloat(value), vReg.getRegister(compiler, false)));

        return vReg;
    }
}
