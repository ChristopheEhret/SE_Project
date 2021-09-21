package fr.ensimag.deca;

import fr.ensimag.deca.codegen.StackOverflowHandler;
import fr.ensimag.deca.codegen.AsmRoutineManager;
import fr.ensimag.deca.codegen.CounterManager;
import fr.ensimag.deca.codegen.DataManager;
import fr.ensimag.deca.codegen.ErrorManager;
import fr.ensimag.deca.codegen.RegistersHandler;
import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.FloatType;
import fr.ensimag.deca.context.IntType;
import fr.ensimag.deca.context.StringType;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.context.VoidType;
import fr.ensimag.deca.syntax.DecaLexer;
import fr.ensimag.deca.syntax.DecaParser;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.deca.tree.AbstractProgram;
import fr.ensimag.deca.tree.Location;
import fr.ensimag.deca.tree.LocationException;
import fr.ensimag.pseudocode.AbstractInstruction;
import fr.ensimag.pseudocode.AbstractLine;
import fr.ensimag.pseudocode.AbstractRegisterSet;
import fr.ensimag.pseudocode.AsmProgram;
import fr.ensimag.pseudocode.ima.IMAProgram;
import fr.ensimag.pseudocode.ima.IMARegisterSet;
import fr.ensimag.pseudocode.ima.ImmediateInteger;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.gba.GBAProgram;
import fr.ensimag.pseudocode.gba.GBARegisterSet;
import fr.ensimag.pseudocode.ima.instructions.ADDSP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.log4j.Logger;

/**
 * Decac compiler instance.
 *
 * This class is to be instantiated once per source file to be compiled. It
 * contains the meta-data used for compiling (source file name, compilation
 * options) and the necessary utilities for compilation (symbol tables, abstract
 * representation of target file, ...).
 *
 * It contains several objects specialized for different tasks. Delegate methods
 * are used to simplify the code of the caller (e.g. call
 * compiler.addInstruction() instead of compiler.getProgram().addInstruction()).
 *
 * @author gl27
 * @date 01/01/2021
 */
public class DecacCompiler {
    private static final Logger LOG = Logger.getLogger(DecacCompiler.class);
    
    private HashMap<Symbol, TypeDefinition> environmentTypes = new HashMap<Symbol, TypeDefinition>();
    private SymbolTable symbols = new SymbolTable();

    private void setDefaultEnvironment() {
        // TODO : check pour la localisation des types de base
        IntType intType = new IntType(this.getOrAddSymbol("int"));
        environmentTypes.put(this.getOrAddSymbol("int"), new TypeDefinition(intType, new Location(0, 0, "default_types")));

        StringType stringType = new StringType(this.getOrAddSymbol("string"));
        environmentTypes.put(this.getOrAddSymbol("string"), new TypeDefinition(stringType, new Location(0, 0, "default_types")));
        
        BooleanType booleanType = new BooleanType(this.getOrAddSymbol("boolean"));
        environmentTypes.put(this.getOrAddSymbol("boolean"), new TypeDefinition(booleanType, new Location(0, 0, "default_types")));

        VoidType voidType = new VoidType(this.getOrAddSymbol("void"));
        environmentTypes.put(this.getOrAddSymbol("void"), new TypeDefinition(voidType, new Location(0, 0, "default_types")));

        FloatType floatType = new FloatType(this.getOrAddSymbol("float"));
        environmentTypes.put(this.getOrAddSymbol("float"), new TypeDefinition(floatType, new Location(0, 0, "default_types")));

        ClassType class0 = new ClassType(this.getOrAddSymbol("0"), new Location(0, 0, "default_class"), null);
        ClassDefinition classDef0 = new ClassDefinition(class0, new Location(0, 0, "default_class"), null);
        environmentTypes.put(this.getOrAddSymbol("0"),classDef0);   
  
    }

    public TypeDefinition getTypeDef(Symbol type){
        return environmentTypes.get(type);
    }

    public Type getType(Symbol type){
        if(!environmentTypes.containsKey(type))
            return null;

        return environmentTypes.get(type).getType();
    }
    

    public HashMap<Symbol, TypeDefinition> getEnvironmentTypes() {
		return environmentTypes;
	}

	public void addClassdefinition(Symbol typeSymbol, TypeDefinition type) {
        environmentTypes.put(typeSymbol, type);
    }

    public SymbolTable.Symbol getOrAddSymbol(String symbolName) {
        return symbols.create(symbolName);
    }


    /**
     * Portable newline character.
     */
    private static final String nl = System.getProperty("line.separator", "\n");

    public DecacCompiler(CompilerOptions compilerOptions, File source) {
        super();
        this.compilerOptions = compilerOptions;
        this.source = source;

        this.setDefaultEnvironment();
    }

    /**
     * Source file associated with this compiler instance.
     */
    public File getSource() {
        return source;
    }

    /**
     * Compilation options (e.g. when to stop compilation, number of registers
     * to use, ...).
     */
    public CompilerOptions getCompilerOptions() {
        return compilerOptions;
    }

    /**
     * @see
     * fr.ensimag.pseudocode.ima.IMAProgram#add(fr.ensimag.pseudocode.AbstractLine)
     */
    public void add(AbstractLine line) {
        currentBlock.add(line);
    }
    
    public void addEmptyLine() {
    	currentBlock.addEmptyLine();
    }

    /**
     * @see fr.ensimag.pseudocode.ima.IMAProgram#addComment(java.lang.String)
     */
    public void addComment(String comment) {
        currentBlock.addComment(comment);
    }

    /**
     * @see
     * fr.ensimag.pseudocode.ima.IMAProgram#addLabel(fr.ensimag.pseudocode.ima.Label)
     */
    public void addLabel(Label label) {
        currentBlock.addLabel(label);
    }

    /**
     * @see
     * fr.ensimag.pseudocode.ima.IMAProgram#addInstruction(fr.ensimag.pseudocode.ima.Instruction)
     */
    public void addInstruction(AbstractInstruction instruction) {
        currentBlock.addInstruction(instruction);
    }

    /**
     * @see
     * fr.ensimag.pseudocode.ima.IMAProgram#addInstruction(fr.ensimag.pseudocode.ima.Instruction,
     * java.lang.String)
     */
    public void addInstruction(AbstractInstruction instruction, String comment) {
        currentBlock.addInstruction(instruction, comment);
    }
    
    public AsmProgram getCurrentBlock() {
    	return currentBlock;
    }
    
    /**
     * @see 
     * fr.ensimag.pseudocode.ima.IMAProgram#display()
     */
    public String displayProgram() {
        return program.display();
    }
    
    private final CompilerOptions compilerOptions;
    private final File source;
    /**
     * The main program. Every instruction generated will eventually end up here.
     */
    private AsmProgram program;
 

    /**
     * Run the compiler (parse source file, generate code)
     *
     * @return true on error
     */
    public boolean compile() {
        String sourceFile = source.getAbsolutePath();
        String path = sourceFile.substring(0, sourceFile.lastIndexOf('/')+1);
        LOG.trace("PATH: " + path);
        String destFile;
        if(compilerOptions.isForGBA()) {
        	destFile = path + sourceFile.substring(sourceFile.lastIndexOf('/')+1, sourceFile.lastIndexOf('.')) + ".s";
        	program = new GBAProgram();
        } else {
        	destFile = path + sourceFile.substring(sourceFile.lastIndexOf('/')+1, sourceFile.lastIndexOf('.')) + ".ass";
        	program = new IMAProgram();
        }
        currentBlock = program;
        
        PrintStream err = System.err;
        PrintStream out = System.out;
        LOG.debug("Compiling file " + sourceFile + " to assembly file " + destFile);
        try {
            return doCompile(sourceFile, destFile, out, err);
        } catch (LocationException e) {
            e.display(err);
            return true;
        } catch (DecacFatalError e) {
            err.println(e.getMessage());
            return true;
        } catch (StackOverflowError e) {
            LOG.debug("stack overflow", e);
            err.println("Stack overflow while compiling file " + sourceFile + ".");
            return true;
        } catch (Exception e) {
            LOG.fatal("Exception raised while compiling file " + sourceFile
                    + ":", e);
            err.println("Internal compiler error while compiling file " + sourceFile + ", sorry.");
            return true;
        } catch (AssertionError e) {
            LOG.fatal("Assertion failed while compiling file " + sourceFile
                    + ":", e);
            err.println("Internal compiler error while compiling file " + sourceFile + ", sorry.");
            return true;
        }
    }

    /**
     * Internal function that does the job of compiling (i.e. calling lexer,
     * verification and code generation).
     *
     * @param sourceName name of the source (deca) file
     * @param destName name of the destination (assembly) file
     * @param out stream to use for standard output (output of decac -p)
     * @param err stream to use to display compilation errors
     *
     * @return true on error
     */
    private boolean doCompile(String sourceName, String destName,
            PrintStream out, PrintStream err)
            throws DecacFatalError, LocationException {
        AbstractProgram prog = doLexingAndParsing(sourceName, err);

        if (prog == null) {
            LOG.info("Parsing failed");
            return true;
        }
        if(compilerOptions.getpOption()) {
        	prog.decompile(out);
        	return false;
        }
        assert(prog.checkAllLocations());


        prog.verifyProgram(this);
        assert(prog.checkAllDecorations());
        if(this.compilerOptions.getVerif())
        	return false;

        addComment("start main program");
        initCodeGen();
        if(compilerOptions.isForGBA())
        	prog.codeGenProgramGBA(this);
        else
        	prog.codeGenProgramIMA(this);
        addComment("end main program");
        LOG.debug("Generated assembly code:" + nl + program.display());
        LOG.info("Output file assembly file is: " + destName);

        FileOutputStream fstream = null;
        try {
            fstream = new FileOutputStream(destName);
        } catch (FileNotFoundException e) {
            throw new DecacFatalError("Failed to open output file: " + e.getLocalizedMessage());
        }

        LOG.info("Writing assembler file ...");

        program.display(new PrintStream(fstream));
        LOG.info("Compilation of " + sourceName + " successful.");
        return false;
    }

    /**
     * Build and call the lexer and parser to build the primitive abstract
     * syntax tree.
     *
     * @param sourceName Name of the file to parse
     * @param err Stream to send error messages to
     * @return the abstract syntax tree
     * @throws DecacFatalError When an error prevented opening the source file
     * @throws DecacInternalError When an inconsistency was detected in the
     * compiler.
     * @throws LocationException When a compilation error (incorrect program)
     * occurs.
     */
    protected AbstractProgram doLexingAndParsing(String sourceName, PrintStream err)
            throws DecacFatalError, DecacInternalError {
        DecaLexer lex;
        try {
            lex = new DecaLexer(CharStreams.fromFileName(sourceName));
        } catch (IOException ex) {
            throw new DecacFatalError("Failed to open input file: " + ex.getLocalizedMessage());
        }
        lex.setDecacCompiler(this);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        DecaParser parser = new DecaParser(tokens);
        parser.setDecacCompiler(this);
        return parser.parseProgramAndManageErrors(err);
    }
    
    /**
     * The current block (method, init, main)
     */
    private AsmProgram currentBlock;
    private boolean main;
    
    public void switchNewBlock(boolean main) {
    	this.main = main;
    	if(compilerOptions.isForGBA())
    		currentBlock = new GBAProgram();
    	else
    		currentBlock = new IMAProgram();
        sovh = new StackOverflowHandler();
        rh.fullReset();
    }

    public void addBlockChecks() {
        if(!main && !compilerOptions.isForGBA())
            this.getCurrentBlock().addFirst(new ADDSP(new ImmediateInteger(sovh.getNbVars())));
        if(!compilerOptions.getNoCkeck())
            sovh.insertCheck(this, main); // ajoute TSTO / BOV au début du block
    }
    
    public void appendCurrentBlock() {
    	program.append(currentBlock);
    	currentBlock = program;
        currentReturnLbl = null;
        
        // this.addBlockChecks();
    }
    
    
    private CounterManager counters;
    private StackOverflowHandler sovh;
    private RegistersHandler rh;
    private ErrorManager errors;
    private AsmRoutineManager routines;
    private DataManager data;
    private Label currentReturnLbl = null;
    private AbstractRegisterSet registers;
    
    private void initCodeGen() {
    	counters = new CounterManager();
    	counters.addCounter("if");
    	counters.addCounter("while");
    	counters.addCounter("and");
    	counters.addCounter("or");
    	counters.addCounter("ldBool");
    	counters.addCounter("instanceOf");
    	counters.addCounter("instanceOfBool");
    	counters.addCounter("NoReturn");
    	sovh = new StackOverflowHandler();
    	routines = new AsmRoutineManager();
    	data = new DataManager();
        sovh = new StackOverflowHandler();
        rh = new RegistersHandler(2, compilerOptions.getNbRegs() - 1);
    	
    	/* Errors */
    	if(!compilerOptions.getNoCkeck()) {
	    	errors = new ErrorManager();
            errors.addError("StackOverflow", "La pile est pleine.");
            errors.addError("HeapOverflow", "Le tas est plein");
	    	errors.addError("FloatOverflow", "Une operation a depasse la capacite maximale des flottants.");
	    	errors.addError("DivideByZero", "Division par 0.");
	    	errors.addError("IOError", "L'entree n'est pas au format attendu.");
	    	errors.addError("NullPointer", "Dereferencement d'un objet null.");
	    	errors.addError("CastFail", "Cast non valide effectue.");
    	}
    	
    	if(compilerOptions.isForGBA()) {
    		registers = new GBARegisterSet();
    		routines.addRoutine("printint", new Label("printInt"));
    		routines.addRoutine("printstr", new Label("printStr"));
    		routines.addRoutine("newline", new Label("newline"));
    		routines.addRoutine("new", new Label("new"));
    		routines.addRoutine("getkey", new Label("getKey"));
    		routines.addRoutine("setcursor", new Label("setCursor"));
    		routines.addRoutine("clrlcdfull", new Label("clrlcdfull"));
    	} else
    		registers = new IMARegisterSet();
    }
    
    public CounterManager getCounterManager() {
    	return this.counters;
    }
    
    public StackOverflowHandler getSOVH() {
    	return this.sovh;
    }

    public RegistersHandler getRegistersHandler() {
        return rh;
    }

    public ErrorManager getErrorManager() {
    	return this.errors;
    }
    
    public AsmRoutineManager getRoutineManager() {
    	return this.routines;
    }

	public Label getCurrentReturnLbl() {
		return currentReturnLbl;
	}
	
	public void setCurrentReturnLbl(Label currentReturnLbl) {
		this.currentReturnLbl = currentReturnLbl;
	}

	public DataManager getDataManager() {
		return data;
	}

	public AbstractRegisterSet getRegisterSet() {
		return registers;
	}

}
