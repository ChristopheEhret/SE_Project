package fr.ensimag.pseudocode.gba;

import fr.ensimag.pseudocode.DAddr;
import fr.ensimag.pseudocode.Register;

/**
 * Operand representing a register indirection with offset, e.g. [R3, #42].
 *
 * @author gl27
 * @date 01/01/2021
 */
public class GRegisterOffset extends DAddr {
    public int getOffset() {
        return offset;
    }
    public Register getRegister() {
        return register;
    }
    private final int offset;
    private final Register register;
    public GRegisterOffset(Register register, int offset) {
        super();
        this.offset = offset;
        this.register = register;
    }
    @Override
    public String toString() {
        return "[" + register + ", #" + offset*4 + "]";
    }
}
