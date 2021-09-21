package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.AbstractRegisterSet;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Register;

/**
 * Register operand (including special registers like SP).
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class GBARegisterSet extends AbstractRegisterSet {
    
    protected GPRegister[] initRegisters() {
        GPRegister [] res = new GPRegister[11];
        for (int i = 0; i <= 10; i++) {
            res[i] = new GPRegister("R" + i, i);
        }
        return res;
    }
    
    protected Register initGB() {
    	return new Register("R11");
    }
    
    protected Register initLB() {
    	return new Register("R12");
    }
    
    protected Register initSP() {
    	return new Register("R13");
    }
    
    protected Register initLR() {
    	return new Register("R14");
    }
    
    protected Register initPC() {
    	return new Register("R15");
    }
    
}
