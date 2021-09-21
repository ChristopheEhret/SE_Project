package fr.ensimag.deca.context;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.tree.Location;
import fr.ensimag.pseudocode.DAddr;

/**
 * Definition associated to identifier in expressions.
 *
 * @author gl27
 * @date 01/01/2021
 */
public abstract class ExpDefinition extends Definition {
    public void setOperand(VirtualDAddr operand) {
        this.operand = operand;
    }

    public VirtualDAddr getOperand(DecacCompiler compiler) {
        return operand;
    }
    private VirtualDAddr operand;

    public ExpDefinition(Type type, Location location) {
        super(type, location);
    }

}
