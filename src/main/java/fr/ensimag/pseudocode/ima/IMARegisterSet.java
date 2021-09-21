package fr.ensimag.pseudocode.ima;

import fr.ensimag.pseudocode.AbstractRegisterSet;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.Register;

/**
 * Register operand (including special registers like SP).
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class IMARegisterSet extends AbstractRegisterSet {
    
    protected GPRegister[] initRegisters() {
        GPRegister [] res = new GPRegister[16];
        for (int i = 0; i <= 15; i++) {
            res[i] = new GPRegister("R" + i, i);
        }
        return res;
    }
    
    protected Register initGB() {
    	return new Register("GB");
    }
    
    protected Register initLB() {
    	return new Register("LB");
    }
    
    protected Register initSP() {
    	return new Register("SP");
    }
    
    protected Register initLR() {
    	return null;
    }
    
    protected Register initPC() {
    	return null;
    }
    
}
