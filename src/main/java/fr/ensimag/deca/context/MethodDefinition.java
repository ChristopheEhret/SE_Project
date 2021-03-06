package fr.ensimag.deca.context;

import fr.ensimag.deca.tree.Location;
import fr.ensimag.pseudocode.Label;

import org.apache.commons.lang.Validate;

/**
 * Definition of a method
 *
 * @author gl27
 * @date 01/01/2021
 */
public class MethodDefinition extends ExpDefinition {

    @Override
    public boolean isMethod() {
        return true;
    }

    public Label getLabel() {
        Validate.isTrue(label != null,
                "setLabel() should have been called before");
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    protected Label codeLabel;
    public void setCodeLabel(Label label) {
        codeLabel = label;
    }
    public Label getCodeLabel() {
        return codeLabel;
    }

    protected Label endLabel;
    public void setEndLabel(Label label) {
        endLabel = label;
    }
    public Label getEndLabel() {
        return endLabel;
    }

    public int getIndex() {
        return index;
    }

    private int index;

    @Override
    public MethodDefinition asMethodDefinition(String errorMessage, Location l)
            throws ContextualError {
        return this;
    }

    private final Signature signature;
    private Label label;
    
    /**
     * 
     * @param type Return type of the method
     * @param location Location of the declaration of the method
     * @param signature List of arguments of the method
     * @param index Index of the method in the class. Starts from 0.
     */
    public MethodDefinition(Type type, Location location, Signature signature, int index) {
        super(type, location);
        this.signature = signature;
        this.index = index;
    }

    public Signature getSignature() {
        return signature;
    }

    @Override
    public String getNature() {
        return "method";
    }

    @Override
    public boolean isExpression() {
        return false;
    }

}
