package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualStaticRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.Register;
import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.instructions.GADD;
import fr.ensimag.pseudocode.gba.instructions.GB;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.gba.instructions.GLTORG;
import fr.ensimag.pseudocode.gba.instructions.GSUB;
import fr.ensimag.pseudocode.ima.RegisterOffset;
import fr.ensimag.pseudocode.ima.instructions.ADDSP;
import fr.ensimag.pseudocode.ima.instructions.HALT;

import java.io.PrintStream;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

/**
 * @author gl27
 * @date 01/01/2021
 */
public class Main extends AbstractMain {
    private static final Logger LOG = Logger.getLogger(Main.class);
    
    private ListDeclVar declVariables;
    private ListInst insts;
    private EnvironmentExp localEnvExp;

    public Main(ListDeclVar declVariables,
            ListInst insts) {
        Validate.notNull(declVariables);
        Validate.notNull(insts);
        this.declVariables = declVariables;
        this.insts = insts;

        this.localEnvExp = new EnvironmentExp(null);
    }

    public EnvironmentExp getLocalEnvExp() {
        return this.localEnvExp;
    }

    @Override
    protected void verifyMain(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify Main: start");
        
        // Vous avez le droit de changer le profil fourni pour ces méthodes
        // (mais ce n'est à priori pas nécessaire).
        ClassDefinition currentClass = (ClassDefinition) compiler.getTypeDef(compiler.getOrAddSymbol("0"));

        declVariables.verifyListDeclVariable(compiler, this.getLocalEnvExp(), currentClass);
        insts.verifyListInst(compiler, localEnvExp, currentClass, compiler.getType(compiler.getOrAddSymbol("void")));
        LOG.debug("verify Main: end");
    }

    @Override
    protected void codeGenMainIMA(DecacCompiler compiler, int offsetGB) {
    	LOG.trace("CodeGen: Main");
    	compiler.addComment("Main program");
        compiler.switchNewBlock(true);
        compiler.addInstruction(new ADDSP(declVariables.size()+offsetGB-1));
        compiler.getSOVH().setNbVars(declVariables.size()+offsetGB-1);
       
        int offsetVar = offsetGB;
        for(AbstractDeclVar avar : declVariables.getList()) {
            compiler.getRegistersHandler().resetRegisters();
            avar.codeGenDeclIMA(compiler, new VirtualStaticRegisterOffset(compiler.getRegistersHandler(), compiler.getRegisterSet().GB, offsetVar++));
        }
        
        compiler.addComment("Beginning of main instructions:");
        insts.codeGenListInstIMA(compiler);
        compiler.addInstruction(new HALT());
        
        if(!compiler.getCompilerOptions().getNoCkeck())
        	compiler.getErrorManager().codeGenError(compiler);

        compiler.addBlockChecks();
        compiler.appendCurrentBlock();
    }
    
    @Override
    protected void codeGenMainGBA(DecacCompiler compiler, int offsetGB) {
    	LOG.trace("CodeGen: Main");
    	compiler.addComment("Main program");
        compiler.switchNewBlock(true);
        
        final int skipSP = declVariables.size()*4+offsetGB-1;
        final GOp2 op2 = GOp2.convertOp2(skipSP);
        if(op2==null) {
        	compiler.addInstruction(new GLDR(compiler.getRegisterSet().R0, new GImmediate32BitInteger(skipSP)));
        	compiler.addInstruction(new GSUB(compiler.getRegisterSet().SP, compiler.getRegisterSet().SP, new GOp2(compiler.getRegisterSet().R0)));
        } else
        	compiler.addInstruction(new GSUB(compiler.getRegisterSet().SP, compiler.getRegisterSet().SP, op2));
        compiler.getSOVH().setNbVars(skipSP);
       
        int offsetVar = offsetGB;
        for(AbstractDeclVar avar : declVariables.getList()) {
            compiler.getRegistersHandler().resetRegisters();
            avar.codeGenDeclGBA(compiler, new VirtualStaticRegisterOffset(compiler.getRegistersHandler(), compiler.getRegisterSet().GB, -offsetVar++));
        }
        
        compiler.addComment("Beginning of main instructions:");
        
        insts.codeGenListInstGBA(compiler);
        Label endLoopInf = new Label("endLoopInf");
        compiler.addLabel(endLoopInf);
        compiler.addInstruction(new GB(endLoopInf));
        
        if(!compiler.getCompilerOptions().getNoCkeck())
        	compiler.getErrorManager().codeGenError(compiler);

        compiler.appendCurrentBlock();
        compiler.addInstruction(new GLTORG());
    }
    
    @Override
    public void decompile(IndentPrintStream s) {
        s.println("{");
        s.indent();
        declVariables.decompile(s);
        insts.decompile(s);
        s.unindent();
        s.println("}");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        declVariables.iter(f);
        insts.iter(f);
    }
 
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        declVariables.prettyPrint(s, prefix, false);
        insts.prettyPrint(s, prefix, true);
    }
}
