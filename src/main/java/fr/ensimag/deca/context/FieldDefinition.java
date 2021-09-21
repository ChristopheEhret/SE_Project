package fr.ensimag.deca.context;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualDAddr;
import fr.ensimag.deca.codegen.VirtualRegister;
import fr.ensimag.deca.codegen.VirtualRegisterOffset;
import fr.ensimag.deca.tree.Location;
import fr.ensimag.deca.tree.Visibility;
import fr.ensimag.pseudocode.GPRegister;
import fr.ensimag.pseudocode.gba.GRegisterOffset;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.LOAD;

/**
 * Definition of a field (data member of a class).
 *
 * @author gl27
 * @date 01/01/2021
 */
public class FieldDefinition extends ExpDefinition {
    public int getIndex() {
        return index;
    }

    private int index;
    
    @Override
    public boolean isField() {
        return true;
    }

    private final Visibility visibility;
    private final ClassDefinition containingClass;
    
    public FieldDefinition(Type type, Location location, Visibility visibility,
            ClassDefinition memberOf, int index) {
        super(type, location);
        this.visibility = visibility;
        this.containingClass = memberOf;
        this.index = index;
    }
    
    @Override
    public FieldDefinition asFieldDefinition(String errorMessage, Location l)
            throws ContextualError {
        return this;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public ClassDefinition getContainingClass() {
        return containingClass;
    }

    @Override
    public String getNature() {
        return "field";
    }

    @Override
    public boolean isExpression() {
        return true;
    }

    @Override
    public void setOperand(VirtualDAddr operand) {
        throw new InternalError("Set operand should not be called on a FieldDefinition");
    }

    @Override
    public VirtualDAddr getOperand(DecacCompiler compiler) {
        VirtualRegister register = compiler.getRegistersHandler().getNewRegister(compiler);
        VirtualRegisterOffset offset = new VirtualRegisterOffset(compiler.getRegistersHandler(), register, this.getIndex());

        // Charge this dans le registre à offset (dans VirtualRegisterOffset)
        if(compiler.getCompilerOptions().isForGBA()) 
            compiler.addInstruction(new GLDR(register.getRegister(compiler, false), new GRegisterOffset(compiler.getRegisterSet().LB, -2)));
        else
            compiler.addInstruction(new LOAD(new RegisterOffset(-2, compiler.getRegisterSet().LB), register.getRegister(compiler, false)));

        return offset;
    }
}
