package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.DVal;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.instructions.GPOP;
import fr.ensimag.pseudocode.ima.instructions.POP;

public class VirtualRegister extends VirtualDVal {
    protected GPRegister register;
    protected int stackPosition = -1;

    @Override
    public boolean isRegister() {
        return true;
    }
    
    public VirtualRegister(RegistersHandler handler, GPRegister register) {
        super(handler);
        this.register = register;
    }

    public DVal getDVal(DecacCompiler compiler, boolean canBeR0) {
        return this.getRegister(compiler, canBeR0);
    }

    public VirtualRegister getVRegister(DecacCompiler compiler) {
        return this;
    }

    public GPRegister getRegister(DecacCompiler compiler, boolean canBeR0) {
        if(register != null) {
            return register;
        } else if (canBeR0) {
            this.setPop(compiler.getRegisterSet().R0);
            handler.pop(compiler, this);
        } else {
            if(!handler.isThereFreeRegisters())
                throw new InternalError("Impossible de récupérer un registre car il n'y a aucun registre de dispo");

            this.setPop(handler.getFreeRegister(compiler));
            handler.pop(compiler, this);
        }

        if(compiler.getCompilerOptions().isForGBA())
        	compiler.addInstruction(new GPOP(register));
        else
        	compiler.addInstruction(new POP(register));
        return register;
    }

    /*
        Fontion qui essaie de récupérer le registre du VirtualRegister.
        Si il n'y a plus de registre disponible, le swapRegister va être placé en r0 et le VirtualRegister va être placé sur le swapRegister.
        Attention, comme le swapRegister va être placé sur r0, et ne va donc plus être suivi par le RegisterHandler.
    */
    public GPRegister getRegisterSwapIfNecessary(DecacCompiler compiler, VirtualRegister swapRegister) {
        if(register != null) {
            return register;
        } else if (handler.isThereFreeRegisters()) {
            this.setPop(handler.getFreeRegister(compiler));
            handler.pop(compiler, this);
            // TODOC a enlever pour le déploiment
            throw new InternalError("On ne devrait pas avoir a swap un registre si tous les registres du handler ne sont pas pleins. Check dans quel cas ca arrive.");
        }

        handler.popInRegister(compiler, this, swapRegister);
        return register;
    }

    public boolean isInRegister() {
        return (register != null);
    }

    public void setPush(int stackPosition) {
        if(register == null)
            throw new InternalError("Un VirtualRegister a été push alors qu'il n'est pas associé avec un registre réel.");

        register = null;
        this.stackPosition = stackPosition;
    }

    public void setPop(GPRegister register) {
        if(stackPosition == -1)
            throw new InternalError("Un VirtualRegister a été pop alors qu'il n'est pas positionné dans la pile.");
        if(stackPosition != handler.getStackLoad())
            throw new InternalError("Un VirtualRegister a été pop alors qu'il n'est pas en haut de la pile (currentStackLoad : " + handler.getStackLoad() + ";VRegister StackPosition : " + stackPosition + ").");
            
        this.register = register;
        this.stackPosition = -1;
    }

    public void changeRegister(GPRegister register) {
        this.register = register;
    }

    @Override
    public void free(DecacCompiler compiler) { // ENDPOINT
        this.handler.freeRegister(compiler, this);
        this.register = null;
        this.stackPosition = -1;
    }
}
