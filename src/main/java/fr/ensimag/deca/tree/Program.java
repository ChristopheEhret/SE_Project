package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.GRegisterOffset;
import fr.ensimag.pseudocode.gba.instructions.GALIGN;
import fr.ensimag.pseudocode.gba.instructions.GARM;
import fr.ensimag.pseudocode.gba.instructions.GBL;
import fr.ensimag.pseudocode.gba.instructions.GGLOBAL;
import fr.ensimag.pseudocode.gba.instructions.GINCLUDE;
import fr.ensimag.pseudocode.gba.instructions.GLDR;
import fr.ensimag.pseudocode.gba.instructions.GMOV;
import fr.ensimag.pseudocode.gba.instructions.GSTR;
import fr.ensimag.pseudocode.gba.instructions.GTEXT;

import java.io.PrintStream;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

/**
 * Deca complete program (class definition plus main block)
 *
 * @author gl27
 * @date 01/01/2021
 */
public class Program extends AbstractProgram {
    private static final Logger LOG = Logger.getLogger(Program.class);
    
    public Program(ListDeclClass classes, AbstractMain main) {
        Validate.notNull(classes);
        Validate.notNull(main);
        this.classes = classes;
        this.main = main;
    }
    public ListDeclClass getClasses() {
        return classes;
    }
    public AbstractMain getMain() {
        return main;
    }
    private ListDeclClass classes;
    private AbstractMain main;

    @Override
    public void verifyProgram(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify program: start");
        Identifier obj = new Identifier(compiler.getOrAddSymbol("Object"));
        obj.setLocation(new Location(0,0,"default_object"));
        Identifier superObj = new Identifier(compiler.getOrAddSymbol("0"));
        superObj.setLocation(new Location(0,0,"default_object"));
        ListDeclField objField = new ListDeclField();
        ListDeclMethod objListMethod = new ListDeclMethod();
        objListMethod.setLocation(new Location(0,0,"default_ListMethod"));
        ListDeclParam eqParams = new ListDeclParam();
        eqParams.setLocation(new Location(0,0,"default_params"));
        Identifier o = new Identifier(compiler.getOrAddSymbol("o"));
        o.setLocation(new Location(0,0,"default_param_ident"));
        DeclParam param = new DeclParam(obj,o);
        param.setLocation(new Location(0,0,"default_param"));
        eqParams.add(param);
        
        ListDeclVar varLocales = new ListDeclVar();
        ListInst eqInsts = new ListInst();
        eqInsts.setLocation(new Location(0,0,"default_ListInst"));
        This thiss = new This(false);
        thiss.setLocation(new Location(0,0,"default_this"));
        Equals isEqual = new Equals(thiss,o);
        isEqual.setLocation(new Location(0,0,"default_equals"));
        Return returnInst = new Return(isEqual);
        returnInst.setLocation(new Location(0,0,"default_returnInst"));
        eqInsts.add(returnInst);
        MethodBody eqBody = new MethodBody(varLocales,eqInsts);
        eqBody.setLocation(new Location(0,0,"default_MethodBody"));
        Identifier equalsIdent = new Identifier(compiler.getOrAddSymbol("equals"));
        equalsIdent.setLocation(new Location(0,0,"default_equalsIdent"));
        Identifier booleanIdent = new Identifier(compiler.getOrAddSymbol("boolean"));
        booleanIdent.setLocation(new Location(0,0,"default_booleanIdent"));
        DeclMethod equalsMethod = new DeclMethod(equalsIdent,booleanIdent,eqParams,eqBody);
        equalsMethod.setLocation(new Location(0,0,"default_method"));
        objListMethod.add(equalsMethod);
        DeclClass obje = new DeclClass(obj,superObj,objField,objListMethod);     
        obje.setLocation(new Location(0,0,"default_class"));
        classes.getModifiableList().add(0,obje);
        //  Passes 1 & 2
        classes.verifyListClass(compiler);
        classes.verifyListClassMembers(compiler);
        // Passe 3 :
        classes.verifyListClassBody(compiler); 
        main.verifyMain(compiler);

        LOG.debug("verify program: end");
    }

    @Override
    public void codeGenProgramIMA(DecacCompiler compiler) {
    	LOG.trace("CodeGen: Program IMA");
    	
    	// PASS 1
    	int nextOffset = 1;
        for(AbstractDeclClass cl : classes.getList()) {
            nextOffset = cl.buildMethodTableIMA(compiler, nextOffset);
        }

    	// PASS 2
    	/* MAIN */
        main.codeGenMainIMA(compiler, nextOffset);
        
        /* CLASSES */
        for(AbstractDeclClass cl : classes.getList()) {
        	cl.codeGenInitIMA(compiler); // init method
        	cl.codeGenClassIMA(compiler); // explicit methods
        }
    }
    
    @Override
    public void codeGenProgramGBA(DecacCompiler compiler) {
    	LOG.trace("CodeGen: Program GBA");
        
    	compiler.addInstruction(new GARM());
    	compiler.addInstruction(new GALIGN("4"));
    	compiler.addInstruction(new GTEXT());
    	compiler.addInstruction(new GGLOBAL("main"));
    	compiler.addLabel(new Label("main"));
    	/* Setting up the screen */
    	compiler.addInstruction(new GLDR(compiler.getRegisterSet().R0, new GImmediate32BitInteger(0x4000000)));
    	compiler.addInstruction(new GLDR(compiler.getRegisterSet().R1, new GImmediate32BitInteger(0x403)));
    	compiler.addInstruction(new GSTR(compiler.getRegisterSet().R1, new GRegisterOffset(compiler.getRegisterSet().R0, 0)));
    	compiler.addInstruction(new GBL(compiler.getRoutineManager().getRoutine("clrlcdfull")));
    	
        compiler.addInstruction(new GMOV(compiler.getRegisterSet().GB, compiler.getRegisterSet().SP));

        int nextOffset = 1;
        for(AbstractDeclClass cl : classes.getList()) {
            nextOffset = cl.buildMethodTableGBA(compiler, nextOffset);
        }

        main.codeGenMainGBA(compiler, 1);

        for(AbstractDeclClass cl : classes.getList()) {
        	cl.codeGenInitGBA(compiler); // init method
        	cl.codeGenClassGBA(compiler); // explicit methods
        }
        
        compiler.addInstruction(new GINCLUDE("\"stdio.asm\""));
        compiler.addInstruction(new GINCLUDE("\"new.asm\""));
        
        compiler.getDataManager().insertData(compiler);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        getClasses().decompile(s);
        getMain().decompile(s);
    }
    
    @Override
    protected void iterChildren(TreeFunction f) {
        classes.iter(f);
        main.iter(f);
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        classes.prettyPrint(s, prefix, false);
        main.prettyPrint(s, prefix, true);
    }
}
