package fr.ensimag.deca.context;

import java.time.Clock;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.tags.SansObjetTest;
import fr.ensimag.deca.tree.AbstractIdentifier;
import fr.ensimag.deca.tree.BooleanLiteral;
import fr.ensimag.deca.tree.ConvFloat;
import fr.ensimag.deca.tree.DeclVar;
import fr.ensimag.deca.tree.FloatLiteral;
import fr.ensimag.deca.tree.Identifier;
import fr.ensimag.deca.tree.Initialization;
import fr.ensimag.deca.tree.IntLiteral;
import fr.ensimag.deca.tree.ListDeclClass;
import fr.ensimag.deca.tree.ListDeclVar;
import fr.ensimag.deca.tree.ListInst;
import fr.ensimag.deca.tree.Main;
import fr.ensimag.deca.tree.NoInitialization;
import fr.ensimag.deca.tree.Program;
import fr.ensimag.deca.tree.StringLiteral;

import static fr.ensimag.deca.context.LoggerColor.redText;
import static fr.ensimag.deca.context.LoggerColor.greenText;
import static fr.ensimag.deca.context.LoggerColor.orangeText;
import static fr.ensimag.deca.context.LoggerColor.yellowText;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestVariables {    
    private static Logger log = Logger.getLogger(TestExpression.class);

    @SansObjetTest
    @DisplayName("Variable declaration with no initialization test")
    public void declVar_no_init_test_success() {
        DecacCompiler compiler = new DecacCompiler(null, null);
        ListDeclVar vars = new ListDeclVar();
        ListDeclClass classes = new ListDeclClass();
        ListInst insts = new ListInst();

        AbstractIdentifier id = new Identifier(compiler.getOrAddSymbol("float"));
        AbstractIdentifier name = new Identifier(compiler.getOrAddSymbol("x"));
        DeclVar declVar = new DeclVar(id, name, new NoInitialization());
        vars.add(declVar);

        Main main = new Main(vars, insts);
        Program prog = new Program(classes, main);    
        assertDoesNotThrow(() -> prog.verifyProgram(compiler)
            , "Program with correct variable declaration could not verify properly.");
        
        assertAll("", 
        () -> {
            assertTrue(id.getType().isFloat(), "Type identifier is not a FloatType as it should be");
            assertTrue(id.getDefinition() == compiler.getTypeDef(compiler.getOrAddSymbol("float")), "Type identifier don't have the right TypeDefinition.");
            assertTrue(name.getDefinition().getType() == compiler.getType(compiler.getOrAddSymbol("float")), "Expression identifier don't have the right TypeDefinition.");
        },
        () -> { 
            ExpDefinition varDef = main.getLocalEnvExp().get(compiler.getOrAddSymbol("x"));
            assertNotNull(varDef, "Could not find the declared variable in the main's expression environment.");
            assertTrue(varDef instanceof VariableDefinition, "Declared variable is actually not a VariableDefinition.");
            assertTrue(varDef.getType().isFloat(), "Declared variable is not a FloatType as it should be.");
            // TODO : vérifier avec le prof que c'est bien comme ca que ca se passe -> indiqué comme ca sur les schémas mais pas dans la grammaire contextuelle
            assertTrue(varDef == name.getDefinition(), "Declared variable's definition do not match the variable identifier's definition.");
        },
        () -> { 
            assertNull(main.getLocalEnvExp().get(compiler.getOrAddSymbol("$")), "An undeclared variable was found in the main's expression environment.");

            assertEquals(name.getDefinition(), main.getLocalEnvExp().get(compiler.getOrAddSymbol("x")), 
                "Identifier and Expression do not have the same definition");
        });
    }

    @SansObjetTest
    @DisplayName("Variable declaration with initialization test")
    public void declVar_init_test_success() {
        DecacCompiler compiler = new DecacCompiler(null, null);
        ListDeclVar vars = new ListDeclVar();
        ListDeclClass classes = new ListDeclClass();
        ListInst insts = new ListInst();

        AbstractIdentifier id = new Identifier(compiler.getOrAddSymbol("float"));
        AbstractIdentifier name = new Identifier(compiler.getOrAddSymbol("x"));
        Initialization init = new Initialization(new FloatLiteral(0));
        DeclVar declVar = new DeclVar(id, name, init);
        vars.add(declVar);

        Main main = new Main(vars, insts);
        Program prog = new Program(classes, main);    
        assertDoesNotThrow(() -> prog.verifyProgram(compiler)
            , "Program with correct variable declaration could not verify properly.");
        
        assertAll("", 
        () -> {
            assertTrue(id.getType().isFloat(), "Type identifier is not a FloatType as it should be");
            assertTrue(id.getDefinition() == compiler.getTypeDef(compiler.getOrAddSymbol("float")), "Type identifier don't have the right TypeDefinition.");
            assertTrue(name.getDefinition().getType() == compiler.getType(compiler.getOrAddSymbol("float")), "Expression identifier don't have the right TypeDefinition.");
            assertTrue(init.getExpression().getType().isFloat(), "Initialization's expression is not a FloatType as it should be");
        },
        () -> { 
            ExpDefinition varDef = main.getLocalEnvExp().get(compiler.getOrAddSymbol("x"));
            assertNotNull(varDef, "Could not find the declared variable in the main's expression environment.");
            assertTrue(varDef instanceof VariableDefinition, "Declared variable is actually not a VariableDefinition.");
            assertTrue(varDef.getType().isFloat(), "Declared variable is not a FloatType as it should be.");
            // TODO : vérifier avec le prof que c'est bien comme ca que ca se passe -> indiqué comme ca sur les schémas mais pas dans la grammaire contextuelle
            assertTrue(varDef == name.getDefinition(), "Declared variable's definition do not match the variable identifier's definition.");
        },
        () -> { 
            assertNull(main.getLocalEnvExp().get(compiler.getOrAddSymbol("$")), "An undeclared variable was found in the main's expression environment.");
            assertEquals(name.getDefinition(), main.getLocalEnvExp().get(compiler.getOrAddSymbol("x")), 
            "Identifier and Expression do not have the same definition");
        });
    }
    
    @SansObjetTest
    @DisplayName("Variable declaration with initialization and conversion to float test")
    public void declVar_init_conv_test_success() {
        DecacCompiler compiler = new DecacCompiler(null, null);
        ListDeclVar vars = new ListDeclVar();
        ListDeclClass classes = new ListDeclClass();
        ListInst insts = new ListInst();

        AbstractIdentifier id = new Identifier(compiler.getOrAddSymbol("float"));
        AbstractIdentifier name = new Identifier(compiler.getOrAddSymbol("x"));
        Initialization init = new Initialization(new IntLiteral(0));
        DeclVar declVar = new DeclVar(id, name, init);
        vars.add(declVar);

        Main main = new Main(vars, insts);
        Program prog = new Program(classes, main);    
        assertDoesNotThrow(() -> prog.verifyProgram(compiler)
            , "Program with correct variable declaration could not verify properly.");
        
        assertAll("", 
        () -> {
            assertTrue(init.getExpression() instanceof ConvFloat, "The initialization's conversion from int to float isn't done.");
            assertTrue(init.getExpression().getType().isFloat(), "Initialization's expression is not a FloatType as it should be");
        });
    }
    
    @SansObjetTest
    @DisplayName("Variable declaration with invalid initialization test (boolean to float)")
    public void declVar_init_test_failure() {
        DecacCompiler compiler = new DecacCompiler(null, null);
        ListDeclVar vars = new ListDeclVar();
        ListDeclClass classes = new ListDeclClass();
        ListInst insts = new ListInst();

        AbstractIdentifier id = new Identifier(compiler.getOrAddSymbol("float"));
        AbstractIdentifier name = new Identifier(compiler.getOrAddSymbol("x"));
        Initialization init = new Initialization(new BooleanLiteral(true));
        DeclVar declVar = new DeclVar(id, name, init);
        vars.add(declVar);

        Main main = new Main(vars, insts);
        Program prog = new Program(classes, main);    
        assertThrows(ContextualError.class, () -> prog.verifyProgram(compiler)
            , "Program with incorrect variable initialization could verify properly (could initialize float variable with BooleanLiteral).");
    }

    @SansObjetTest
    @DisplayName("Variable declaration with invalid initialization test (int to float)")
    public void declVar_init_test_failure_2() {
        DecacCompiler compiler = new DecacCompiler(null, null);
        ListDeclVar vars = new ListDeclVar();
        ListDeclClass classes = new ListDeclClass();
        ListInst insts = new ListInst();

        AbstractIdentifier id = new Identifier(compiler.getOrAddSymbol("int"));
        AbstractIdentifier name = new Identifier(compiler.getOrAddSymbol("x"));
        Initialization init = new Initialization(new FloatLiteral(0));
        DeclVar declVar = new DeclVar(id, name, init);
        vars.add(declVar);

        Main main = new Main(vars, insts);
        Program prog = new Program(classes, main);    
        assertThrows(ContextualError.class, () -> prog.verifyProgram(compiler)
            , "Program with incorrect variable initialization could verify properly (could initialize string variable with IntLiteral).");
    }

    @SansObjetTest
    @DisplayName("Two variable of the same name declared")
    public void declVar_double_init_test_failure() {
        DecacCompiler compiler = new DecacCompiler(null, null);
        ListDeclVar vars = new ListDeclVar();
        ListDeclClass classes = new ListDeclClass();
        ListInst insts = new ListInst();

        AbstractIdentifier id = new Identifier(compiler.getOrAddSymbol("float"));
        AbstractIdentifier name = new Identifier(compiler.getOrAddSymbol("x"));
        Initialization init = new Initialization(new IntLiteral(0));
        AbstractIdentifier id2 = new Identifier(compiler.getOrAddSymbol("float"));
        AbstractIdentifier name2 = new Identifier(compiler.getOrAddSymbol("x"));
        Initialization init2 = new Initialization(new IntLiteral(0));
        DeclVar declVar = new DeclVar(id, name, init);
        DeclVar declVar2 = new DeclVar(id2, name2, init2);
        vars.add(declVar);
        vars.add(declVar2);

        Main main = new Main(vars, insts);
        Program prog = new Program(classes, main);    
        assertThrows(ContextualError.class, () -> prog.verifyProgram(compiler)
            , "Program with double variable initialization could verify properly.");
    }
}