package fr.ensimag.deca.codegen;

import java.util.ArrayList;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GPOP;
import fr.ensimag.pseudocode.gba.instructions.GPUSH;
import fr.ensimag.pseudocode.gba.instructions.GSUB;
import fr.ensimag.pseudocode.ima.instructions.LOAD;
import fr.ensimag.pseudocode.ima.instructions.POP;
import fr.ensimag.pseudocode.ima.instructions.PUSH;
import fr.ensimag.pseudocode.ima.instructions.SUBSP;

public class RegistersHandler {
    private final int higherReg;
    private final int lowerReg;
    
    private int currentLowerReg = 0;
    private int maxLowerReg = 0;
    private int maxPush = 0;
    
    private int pushedInStack = 0;
    private ArrayList<VirtualRegister> stackedRegisters = new ArrayList<VirtualRegister>();
    private VirtualRegister[] actualRegisters;
    // TODOC surement inutile
    private boolean[] registersUsage;

    public RegistersHandler(int lowerReg, int higherReg) {
        this.lowerReg = lowerReg;
        this.higherReg = higherReg;
        currentLowerReg = this.lowerReg;
        maxLowerReg = currentLowerReg - 1;
        actualRegisters = new VirtualRegister[higherReg+1];
        registersUsage = new boolean[higherReg+1];
        for(int i = 0; i <= higherReg; i++) registersUsage[i] = false;

    }

    public boolean isThereFreeRegisters() {
        return currentLowerReg <= higherReg;
    }

    public GPRegister getFreeRegister(DecacCompiler compiler) {
        if(currentLowerReg <= higherReg) {
            int regNumber = currentLowerReg++; 
            registersUsage[regNumber] = true;

            if(maxLowerReg < regNumber)
                maxLowerReg = regNumber;

            return compiler.getRegisterSet().getR(regNumber);
        } else {
            VirtualRegister pushedRegister = actualRegisters[higherReg];
            actualRegisters[higherReg] = null;
            if(!pushedRegister.isInRegister())
                throw new InternalError("Un VirtualRegister est dans la liste des vrais registres sans être réellement associé à un registre");

            GPRegister retRegister = pushedRegister.getRegister(compiler, false);
                
            stackedRegisters.add(pushedRegister);
            pushedRegister.setPush(stackedRegisters.size());

            if(stackedRegisters.size() + pushedInStack > maxPush)
                maxPush = stackedRegisters.size() + pushedInStack;

            if(compiler.getCompilerOptions().isForGBA())
            	compiler.addInstruction(new GPUSH(retRegister));
            else
            	compiler.addInstruction(new PUSH(retRegister));
            return retRegister;
        }
    }

    /*
        Fonction qui place outVRegister dans r0 et qui place inVRegister dans le registre de outVRegister
    */
    public void popInRegister(DecacCompiler compiler, VirtualRegister inVRegister, VirtualRegister outVRegister) {
        if(!outVRegister.isInRegister())
            throw new InternalError("On ne peut pas discard un VirtualRegister qui n'est pas dans un registre");

        GPRegister register = outVRegister.getRegister(compiler, false);
        outVRegister.changeRegister(compiler.getRegisterSet().R0);
        
        if(compiler.getCompilerOptions().isForGBA())
        	compiler.addInstruction(new GMOV(compiler.getRegisterSet().R0, register));
        else
        	compiler.addInstruction(new LOAD(register, compiler.getRegisterSet().R0));
        
        inVRegister.changeRegister(register);
        this.pop(compiler, inVRegister);

        if(compiler.getCompilerOptions().isForGBA())
        	compiler.addInstruction(new GPOP(register));
        else
        	compiler.addInstruction(new POP(register));
    }

    public VirtualRegister getNewRegister(DecacCompiler compiler) {  // ENDPOINT
        GPRegister reg = this.getFreeRegister(compiler);
        VirtualRegister newReg = new VirtualRegister(this, reg);

        actualRegisters[reg.getNumber()] = newReg;

        return newReg;
    }

    public void pop(DecacCompiler compiler, VirtualRegister register) {
        if(register != stackedRegisters.get(stackedRegisters.size() - 1))
            throw new InternalError("Tried to pop a register that was not on top of the stack");
        if(!register.isInRegister())
            throw new InternalError("Le VirtualRegister a appelé pop sans avoir set son registre");

        stackedRegisters.remove(register);

        int registerNumber = register.getRegister(compiler, false).getNumber();        
        if(registerNumber != 0 && registerNumber != 1)
            actualRegisters[registerNumber] = register;
    }

    public void freeRegister(DecacCompiler compiler, VirtualRegister register) {
        if(!register.isInRegister()) {
            if(register != stackedRegisters.get(stackedRegisters.size() - 1))
                throw new InternalError("Tried to free a VirtualRegister that is not in an actual register nor at the top of the pile");
            
            stackedRegisters.remove(register);
            if(compiler.getCompilerOptions().isForGBA())
            	compiler.addInstruction(new GSUB(compiler.getRegisterSet().SP, compiler.getRegisterSet().SP, GOp2.convertOp2(1)));
            else
            	compiler.addInstruction(new SUBSP(1));
            return;
        }

        if(register.getRegister(compiler, false) == compiler.getRegisterSet().R0 || register.getRegister(compiler, false) == compiler.getRegisterSet().R1)
            return;

        if(register != actualRegisters[currentLowerReg - 1])
            throw new InternalError("Un Virtual Register a été libéré sans que ce soit le dernier a avoir été utilisé.");

        actualRegisters[currentLowerReg - 1] = null;
        currentLowerReg--;

        // TODOC : peut être utile mais pas sûr du tout
        // if(stackedRegisters.size() > 0) {

        // }
    }

    public int getStackLoad() {
        return stackedRegisters.size();
    }

    public int getMaxPush() {
        return maxPush;
    }

    public int getMaxLowerReg() {
        return maxLowerReg;
    }

    public void pushInStack(int amountPushed) {
        pushedInStack += amountPushed;
        if(pushedInStack + stackedRegisters.size() > maxPush)
            maxPush = pushedInStack + stackedRegisters.size();
    }

    public void popFromStack(int amountPoped) {
        pushedInStack -= amountPoped;
    }
    public void resetRegisters() {
        if(stackedRegisters.size() != 0)
            throw new InternalError("Tried to reset registers while some were still on stack");

        for(int i = 0; i < actualRegisters.length; i++)
            actualRegisters[i] = null;

        currentLowerReg = lowerReg;
    }

    public void fullReset() {
        this.resetRegisters();
        for(int i = 0; i <= higherReg; i++) registersUsage[i] = false;
        maxPush = 0;
        maxLowerReg = currentLowerReg - 1;
    }
}