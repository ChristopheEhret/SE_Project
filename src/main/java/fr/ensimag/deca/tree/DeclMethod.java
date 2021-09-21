package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.VirtualStaticRegisterOffset;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.context.TypeTools;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.EnvironmentExp.DoubleDefException;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.pseudocode.Label;
import fr.ensimag.pseudocode.Register;

public class DeclMethod extends AbstractDeclMethod {

	private AbstractIdentifier methodName;
	private AbstractIdentifier typeReturned;
	private ListDeclParam listDeclParam;
	private AbstractMethodBody methodBody;
	
	
	public DeclMethod(AbstractIdentifier methodName, AbstractIdentifier typeReturned, ListDeclParam listDeclParam,
			AbstractMethodBody methodBody) {
		super();
		this.methodName = methodName;
		this.typeReturned = typeReturned;
		this.listDeclParam = listDeclParam;
		this.methodBody = methodBody;
	}

	/**
     * Pass 2 , rule 2.7 of [SyntaxeContextuelle] 
     */
	@Override
	public void verifyDeclMethod(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError{
			LOG.debug("verifyDeclMethod: start");
			Signature sig = listDeclParam.verifyListDeclParam(compiler);
			/*VERIFICATION DE LA REGLE  2.7 (on sait déjà que la superclass existe grâce à la passe 1) */
			ClassDefinition superClass = currentClass.getSuperClass();
			ExpDefinition envExpSuper_Name = null;
			while(envExpSuper_Name == null && superClass != compiler.getTypeDef(compiler.getOrAddSymbol("0"))) {
				envExpSuper_Name = superClass.getMembers().getOnlyCurrent(methodName.getName());
				superClass = superClass.getSuperClass();
			}
	        int index = currentClass.getNumberOfMethods();
    		Type type = typeReturned.verifyType(compiler);   
	        if( envExpSuper_Name != null ) { // Si env_exp_super(name) est défini
				if(envExpSuper_Name.isMethod()) { // Si env_exp_super(name) correspond à une MethodDefinition
	        		MethodDefinition methodDef = (MethodDefinition)envExpSuper_Name;
	        		if(!sig.isSameSignature(methodDef.getSignature())) {
	        			throw new ContextualError("The Override is impossible because signatures are not equals",this.getLocation());
	        		}	        
	        		if(!TypeTools.assign_compatible(compiler, envExpSuper_Name.getType(),type)) {
	        			throw new ContextualError("The Override is impossible because the return type is not a subtype of that of the inherited method",this.getLocation());
	        		}
	        		index = methodDef.getIndex();
	        	}
	        	else {   
	        		throw new ContextualError("The identifier " + methodName.getName().getName() + " already exists and it is not an method",this.getLocation());
	        	}
	        }
	        else {
	        	currentClass.incNumberOfMethods();
	        	index = currentClass.getNumberOfMethods();
	        }
	        MethodDefinition methodDefinition = new MethodDefinition(type, this.getLocation(),sig,index);
	        EnvironmentExp envExp = currentClass.getMembers();
	        try {
	            envExp.declare(methodName.getName(), methodDefinition);	            
	        } catch (DoubleDefException e) {
	            throw new ContextualError("Method  " + methodName.getName() + " was already defined", this.getLocation());
	        }
	        methodName.setDefinition(methodDefinition);
	        LOG.debug("verifyDeclMethod: end");
	}
	
	/**
     * Pass 3 , rule 3.11 of [SyntaxeContextuelle] 
     */
	@Override
	public void verifyMethodBody(DecacCompiler compiler,EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError{
        LOG.debug("verifyMethodBody: start");
		EnvironmentExp envExpParam = listDeclParam.verifyListParamBody(compiler);
		Type returnType = compiler.getType(typeReturned.getName());
		methodBody.verifyMethodBody(compiler, localEnv, envExpParam, currentClass, returnType);		
        LOG.debug("verifyMethodBody: end");
	}
	
	@Override
    public void decompile(IndentPrintStream s) {
		typeReturned.decompile(s);
		s.print(" ");
		methodName.decompile(s);
		s.print("(");
		listDeclParam.decompile(s);
		s.print(")");
		methodBody.decompile(s);
	}
	@Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
    	typeReturned.prettyPrint(s,prefix,false);
		methodName.prettyPrint(s,prefix,false);
    	listDeclParam.prettyPrint(s,prefix,false);
    	methodBody.prettyPrint(s,prefix,true);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
    	typeReturned.iter(f);
    	methodName.iter(f);
    	listDeclParam.iter(f);
    	methodBody.iter(f);    	
    }	
    
    @Override
    public String getMethodName() {
    	return methodName.decompile();
    }


	@Override
	public void buildMethodLabels(String className) {
		// Label methodLbl = new Label("code."+methodName.getName().getName());
		methodName.getMethodDefinition().setLabel(new Label(className + "." + getMethodName()));
		methodName.getMethodDefinition().setCodeLabel(new Label("code." + className + "."  + getMethodName()));
		methodName.getMethodDefinition().setEndLabel(new Label("fin." + className + "." + getMethodName()));
	}
	
	@Override
	public void codeGenMethodIMA(DecacCompiler compiler) {
		LOG.trace("CodeGen: Method "+getMethodName());
		compiler.addComment("- - - - - - - -");
		compiler.addComment(" New method: "+getMethodName());

	if(!typeReturned.getType().isVoid())
			compiler.setCurrentReturnLbl(methodName.getMethodDefinition().getEndLabel());
		compiler.addLabel(methodName.getMethodDefinition().getCodeLabel());
        
        int offsetParam = -3; 
        for(AbstractDeclParam aparam : listDeclParam.getList())
        	aparam.codeGenDeclIMA(compiler, new VirtualStaticRegisterOffset(compiler.getRegistersHandler(), compiler.getRegisterSet().LB, offsetParam--));
        
        methodBody.codeGenBodyIMA(compiler, typeReturned, methodName.getMethodDefinition().getLabel().toString());
	}
	
	@Override
	public void codeGenMethodGBA(DecacCompiler compiler) {
		LOG.trace("CodeGen: Method "+getMethodName());
		LOG.trace("CodeGen: Method "+getMethodName());
		compiler.addComment("- - - - - - - -");
		compiler.addComment(" New method: "+getMethodName());
		// TODOC récup le nom de la classe de la méthode
		if(!typeReturned.getType().isVoid())
			compiler.setCurrentReturnLbl(methodName.getMethodDefinition().getEndLabel());
		compiler.addLabel(methodName.getMethodDefinition().getCodeLabel());
        
        int offsetParam = -3; 
        for(AbstractDeclParam aparam : listDeclParam.getList())
        	aparam.codeGenDeclGBA(compiler, new VirtualStaticRegisterOffset(compiler.getRegistersHandler(), compiler.getRegisterSet().LB, offsetParam--));
        
        methodBody.codeGenBodyGBA(compiler, typeReturned, methodName.getMethodDefinition().getLabel().toString());
	}
	
}
