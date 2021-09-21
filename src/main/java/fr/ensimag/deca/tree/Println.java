package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.ima.instructions.WNL;

/**
 * @author gl27
 * @date 01/01/2021
 */
public class Println extends AbstractPrint {

    /**
     * @param arguments arguments passed to the print(...) statement.
     * @param printHex if true, then float should be displayed as hexadecimal (printlnx)
     */
    public Println(boolean printHex, ListExpr arguments) {
        super(printHex, arguments);
    }

    @Override
    protected void codeGenInstIMA(DecacCompiler compiler) {
        super.codeGenInstIMA(compiler);
        compiler.addInstruction(new WNL());
    }
    
    @Override
    protected void codeGenInstGBA(DecacCompiler compiler) {
        super.codeGenInstGBA(compiler);
        compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("newline")));
    }

    @Override
    String getSuffix() {
        return "ln";
    }
}
