package fr.ensimag.pseudocode.ima;

/**
 *
 * @author Ensimag
 * @date 01/01/2021
 */
public abstract class UnaryInstructionImmInt extends UnaryInstruction {

    protected UnaryInstructionImmInt(ImmediateInteger operand) {
        super(operand);
    }

    protected UnaryInstructionImmInt(int i) {
        super(new ImmediateInteger(i));
    }

}
