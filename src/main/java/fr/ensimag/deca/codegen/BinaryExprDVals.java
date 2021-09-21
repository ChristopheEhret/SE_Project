package fr.ensimag.deca.codegen;

public class BinaryExprDVals {
    private VirtualDVal rOperand;
    private VirtualRegister lOperand;

    public BinaryExprDVals(VirtualDVal rOperand, VirtualRegister lOperand) {
        this.rOperand = rOperand;
        this.lOperand = lOperand;    
    }

    public VirtualDVal getROperand() {
        return rOperand;
    }

    public VirtualRegister getLOperand() {
        return lOperand;
    }
}
