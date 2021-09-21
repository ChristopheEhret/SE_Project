package fr.ensimag.deca.context;

import java.time.Clock;

import javax.sound.midi.MidiChannel;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.mockito.Mockito;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.tags.HelloWorldTest;
import fr.ensimag.deca.context.tags.SansObjetTest;
import fr.ensimag.deca.tree.AbstractBinaryExpr;
import fr.ensimag.deca.tree.AbstractExpr;
import fr.ensimag.deca.tree.AbstractLValue;
import fr.ensimag.deca.tree.AbstractOpArith;
import fr.ensimag.deca.tree.AbstractOpBool;
import fr.ensimag.deca.tree.AbstractOpCmp;
import fr.ensimag.deca.tree.AbstractUnaryExpr;
import fr.ensimag.deca.tree.And;
import fr.ensimag.deca.tree.Assign;
import fr.ensimag.deca.tree.BooleanLiteral;
import fr.ensimag.deca.tree.ConvFloat;
import fr.ensimag.deca.tree.Equals;
import fr.ensimag.deca.tree.FloatLiteral;
import fr.ensimag.deca.tree.Greater;
import fr.ensimag.deca.tree.Identifier;
import fr.ensimag.deca.tree.IntLiteral;
import fr.ensimag.deca.tree.Modulo;
import fr.ensimag.deca.tree.Multiply;
import fr.ensimag.deca.tree.Not;
import fr.ensimag.deca.tree.Plus;
import fr.ensimag.deca.tree.ReadFloat;
import fr.ensimag.deca.tree.ReadInt;
import fr.ensimag.deca.tree.StringLiteral;
import fr.ensimag.deca.tree.UnaryMinus;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

public class TestExpression {
    private static Logger log = Logger.getLogger(TestExpression.class);

    @HelloWorldTest
    @DisplayName("FloatLiteral test - Checks that a FloatLiteral does verify as the correct type")
    public void floatLiteral_test() {
        FloatLiteral fLiteral = new FloatLiteral(1.5f);
        DecacCompiler compiler = new DecacCompiler(null, null);
        assertDoesNotThrow(() -> fLiteral.verifyExpr(compiler, null, null), 
            "FloatLiteral cannot verify.");

        assertTrue(fLiteral.getType().isFloat(), "FloatLiteral is not a FloatType");
    }

    @HelloWorldTest
    @DisplayName("BooleanLiteral test - Checks that a BooleanLiteral does verify as the correct type")
    public void BooleanLiteral_test() {
        BooleanLiteral bLiteral = new BooleanLiteral(true);
        DecacCompiler compiler = new DecacCompiler(null, null);
        assertDoesNotThrow(() -> bLiteral.verifyExpr(compiler, null, null), 
            "BooleanLiteral cannot verify.");

        assertTrue(bLiteral.getType().isBoolean(), "BooleanLiteral is not a BooleanType");
    }

    @HelloWorldTest
    @DisplayName("IntLiteral test - Checks that a IntLiteral does verify as the correct type")
    public void intLiteral_test() {
        IntLiteral iLiteral = new IntLiteral(10);
        DecacCompiler compiler = new DecacCompiler(null, null);
        assertDoesNotThrow(() -> iLiteral.verifyExpr(compiler, null, null), 
            "IntLiteral cannot verify.");

        assertTrue(iLiteral.getType().isInt(), "IntLiteral is not a IntType");
    }

    @HelloWorldTest
    @DisplayName("StringLiteral test - Checks that a StringLiteral does verify as the correct type")
    public void stringLiteral_test() {
        StringLiteral sLiteral = new StringLiteral("test");
        DecacCompiler compiler = new DecacCompiler(null, null);
        assertDoesNotThrow(() -> sLiteral.verifyExpr(compiler, null, null), 
            "StringLiteral cannot verify.");

        assertTrue(sLiteral.getType().isString(), "StringLiteral is not a StringType");
    }

    @HelloWorldTest
    @DisplayName("Type identifier test - Checks that an identifier does verify as a valid type")
    public void type_identifier_test_success() {
        DecacCompiler compiler = new DecacCompiler(null, null);
        Identifier identifier = new Identifier(compiler.getOrAddSymbol("int"));

        Type identifierType = assertDoesNotThrow(() -> { return identifier.verifyType(compiler); },
            "Identifier cannot verify a valid type ('int').");
        
        assertTrue(identifierType.isInt(), "Identifier is of the wrong type");
        assertEquals(identifier.getDefinition().getType(), identifierType, "Identifier's type is not the same as its definition's one.");
    }

    @HelloWorldTest
    @DisplayName("Type identifier test - Checks that an identifier does not verify as an invalid type")
    public void type_identifier_test_failure() {
        DecacCompiler compiler = new DecacCompiler(null, null);
        Identifier identifier = new Identifier(compiler.getOrAddSymbol(" $ $ $ "));

        assertThrows( ContextualError.class, 
            () -> { identifier.verifyType(compiler); }, 
            "Identifier verified a type that doesn't exist (' $ $ $ ').");
    }

    @SansObjetTest
    @DisplayName("Assign test - Checks verify propagation & assign type")
    public void assign_test() {
        final Type INT = new IntType(null); 
        final Type FLOAT = new FloatType(null); 

        AbstractLValue lvalue = Mockito.mock(AbstractLValue.class);
        AbstractLValue lvalue_float = Mockito.mock(AbstractLValue.class);
        AbstractExpr rvalue = Mockito.mock(AbstractExpr.class);
        try {
            Mockito.when(lvalue.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(lvalue_float.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(FLOAT);
        } catch (ContextualError e) {
        }

        Assign assign_int = new Assign(lvalue, rvalue);
        assertDoesNotThrow(() -> {assign_int.verifyExpr(null, null, null);},
            "Assign could not verify properly with lvalue int and rvalue int.");
        assertTrue(assign_int.getType().isInt(),
            "Assign did not get type of lvalue (here IntType).");
    
        try {
            verify(lvalue).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
            verify(rvalue).verifyRValue(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        } catch (ContextualError e) {
        }

        Assign assign_float = new Assign(lvalue_float, rvalue);
        assertDoesNotThrow(() -> {assign_float.verifyExpr(null, null, null);},
            "Assign could not verify properly with lvalue float and rvalue int.");
        assertTrue(assign_float.getType().isFloat(),
            "Assign did not get type of lvalue (here FloatType).");
    }

    @SansObjetTest
    @DisplayName("RValue test - Checks that rValue can properly cast to expected type.")
    public void rvalue_success() {
        final Type BOOLEAN = new BooleanType(null);
        final Type FLOAT = new FloatType(null);

        AbstractExpr rValue_boolean = new BooleanLiteral(true);
        AbstractExpr rValue_int = new IntLiteral(0);

        DecacCompiler compiler = new DecacCompiler(null, null);
        AbstractExpr conv_bool;
        AbstractExpr conv_float;

        conv_bool = assertDoesNotThrow(() -> rValue_boolean.verifyRValue(compiler, null, null, BOOLEAN),
            "Boolean rValue could not verify with boolean expected type");
        conv_float = assertDoesNotThrow(() -> rValue_int.verifyRValue(compiler, null, null, FLOAT),
            "Int rValue could not verify with float expected type");

        assertTrue(rValue_boolean.getType().isBoolean(),
            "Boolean rValue does not have a BooleanType");
        assertTrue(rValue_int.getType().isInt(),
            "Int rValue does not have a IntType");
        assertNull(conv_bool,
            "Boolean rValue converted to float");
        assertTrue(conv_float instanceof ConvFloat,
            "Int rValue did not convert to float");
    }

    @SansObjetTest
    @DisplayName("RValue test - Checks that rValue cannot properly cast to incorrect type.")
    public void rvalue_failure() {
        final Type STRING = new StringType(null);
        final Type INT = new IntType(null);

        AbstractExpr rValue_boolean = new BooleanLiteral(true);
        AbstractExpr rValue_float = new FloatLiteral(0);

        DecacCompiler compiler = new DecacCompiler(null, null);

        assertThrows(ContextualError.class, () -> rValue_boolean.verifyRValue(compiler, null, null, STRING),
            "Boolean rValue could verify with string expected type");
        assertThrows(ContextualError.class, () -> rValue_float.verifyRValue(compiler, null, null, INT),
            "Float rValue could not verify with int expected type");
    }

    @SansObjetTest
    @DisplayName("Condition test - Checks that condition only verifies on Boolean expressions.")
    public void condition_test() {
        AbstractExpr rValue_boolean = new BooleanLiteral(true);
        AbstractExpr rValue_float = new FloatLiteral(0);

        DecacCompiler compiler = new DecacCompiler(null, null);

        assertDoesNotThrow(() -> rValue_boolean.verifyCondition(compiler, null, null),
            "Condition could not verify with boolean expression.");
        assertTrue(rValue_boolean.getType().isBoolean(), 
            "Condition is not boolean type");
        assertThrows(ContextualError.class, () -> rValue_float.verifyCondition(compiler, null, null),
            "Condition could verify with float expression.");
    }

    @SansObjetTest
    @DisplayName("Binary operator test - Checks operator's resulting type & checks verify propagation")
    public void op_arith_success() {
        // TODO : tests pour les utilitaires (genre type_binary_op, ....)

        final Type INT = new IntType(null); 
        final Type FLOAT = new FloatType(null); 
        
        AbstractExpr exprInt = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprInt2 = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprFloat = Mockito.mock(AbstractExpr.class);
        DecacCompiler compiler = new DecacCompiler(null, null);

        try {
            Mockito.when(exprInt.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprInt2.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprFloat.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(FLOAT);
            Mockito.when(exprInt.getType()).thenReturn(INT);
            Mockito.when(exprInt.getType()).thenReturn(INT);
            Mockito.when(exprInt2.getType()).thenReturn(INT);
            Mockito.when(exprFloat.getType()).thenReturn(FLOAT);
        } catch (ContextualError e) {
        }

        AbstractOpArith op_arith_fi = new Plus(exprFloat, exprInt);
        assertDoesNotThrow(() -> op_arith_fi.verifyExpr(compiler, null, null),
            "AbstractOpArith could not verify properly");
        assertTrue(op_arith_fi.getType().isFloat(),
            "AbstractOpArith's type is not FloatType with an int and a float as argument.");

        // Checks VerifyExpr propagation
        try {
            verify(exprInt).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
            verify(exprFloat).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
        } catch (ContextualError e) {
        }

        AbstractOpArith op_arith_ii = new Multiply(exprInt, exprInt2);
        assertDoesNotThrow(() -> op_arith_ii.verifyExpr(compiler, null, null),
            "AbstractOpArith could not verify properly");
        assertTrue(op_arith_ii.getType().isInt(),
            "AbstractOpArith's type is not IntType with two ints as argument.");
    }

    @SansObjetTest
    @DisplayName("Binary operator test - Checks exception if operand types are not compatible")
    public void op_arith_failure() {
        // TODO : tests pour les utilitaires (genre type_binary_op, ....)

        final Type INT = new IntType(null); 
        final Type BOOLEAN = new BooleanType(null); 
        final Type VOID = new VoidType(null); 
         
        AbstractExpr exprInt = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprBoolean = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprVoid = Mockito.mock(AbstractExpr.class);

        try {
            Mockito.when(exprInt.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprBoolean.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprVoid.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(VOID);
            Mockito.when(exprInt.getType()).thenReturn(INT);
            Mockito.when(exprBoolean.getType()).thenReturn(BOOLEAN);
            Mockito.when(exprVoid.getType()).thenReturn(VOID);
        } catch (ContextualError e) {
        }

        AbstractOpArith op_arith_bi = new Plus(exprBoolean, exprInt);
        assertThrows(ContextualError.class, () -> op_arith_bi.verifyExpr(null, null, null),
            "AbstractOpArith verified with a boolean and an int as arguments.");

        AbstractOpArith op_arith_vb = new Plus(exprVoid, exprBoolean);
        assertThrows(ContextualError.class, () -> op_arith_vb.verifyExpr(null, null, null),
            "AbstractOpArith verified with a void and a boolean as arguments.");
    }

    @SansObjetTest
    @DisplayName("Modulo operator test - Checks verify propagation & type - Checks exception if operand types are not compatible")
    public void op_mod() {
        // TODO : tests pour les utilitaires (genre type_binary_op, ....)

        final Type INT = new IntType(null); 
        final Type VOID = new VoidType(null); 
         
        AbstractExpr exprInt = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprInt2 = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprVoid = Mockito.mock(AbstractExpr.class);

        try {
            Mockito.when(exprInt.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprInt2.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprVoid.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(VOID);
            Mockito.when(exprInt.getType()).thenReturn(INT);
            Mockito.when(exprInt2.getType()).thenReturn(INT);
            Mockito.when(exprVoid.getType()).thenReturn(VOID);
        } catch (ContextualError e) {
        }

        Modulo po_mod_ii = new Modulo(exprInt, exprInt2);
        assertDoesNotThrow(() -> po_mod_ii.verifyExpr(null, null, null),
            "Modulo did not verify with two ints as arguments.");
        assertTrue(po_mod_ii.getType().isInt(),
            "Modulo is not of type IntType");

        Modulo po_mod_iv = new Modulo(exprVoid, exprInt);
        assertThrows(ContextualError.class, () -> po_mod_iv.verifyExpr(null, null, null),
            "Modulo verified with a void and an int as arguments.");
    }

    @SansObjetTest
    @DisplayName("Comparator test - Checks verify propagation & AbstractOpCmp's type")
    public void op_cmp_success() {
        final Type INT = new IntType(null); 
        final Type FLOAT = new FloatType(null); 
        final Type BOOLEAN = new BooleanType(null); 
         
        AbstractExpr exprInt = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprFloat = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprBoolean = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprBoolean2 = Mockito.mock(AbstractExpr.class);

        DecacCompiler compiler = new DecacCompiler(null, null);

        try {
            Mockito.when(exprInt.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprFloat.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(FLOAT);
            Mockito.when(exprBoolean.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprBoolean2.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprInt.getType()).thenReturn(INT);
            Mockito.when(exprFloat.getType()).thenReturn(FLOAT);
            Mockito.when(exprBoolean.getType()).thenReturn(BOOLEAN);
            Mockito.when(exprBoolean2.getType()).thenReturn(BOOLEAN);
        } catch (ContextualError e) {
        }

        AbstractOpCmp op_cmp_fi = new Greater(exprFloat, exprInt);
        assertDoesNotThrow(() -> op_cmp_fi.verifyExpr(compiler, null, null),
            "AbstractOpCmp could not verify properly");
        assertTrue(op_cmp_fi.getType().isBoolean(),
            "AbstractOpCmp's type is not a BooleanType.");

        // Checks VerifyExpr propagation
        try {
            verify(exprInt).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
            verify(exprFloat).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
        } catch (ContextualError e) {
        }

        AbstractOpCmp op_cmp_bb = new Equals(exprBoolean, exprBoolean2);
        assertDoesNotThrow(() -> op_cmp_bb.verifyExpr(compiler, null, null),
            "AbstractOpCmp could not verify properly");
        assertTrue(op_cmp_bb.getType().isBoolean(),
            "AbstractOpCmp's type is not a BooleanType.");
    }

    @SansObjetTest
    @DisplayName("Comparator test - Checks exception if operand types are not compatible")
    public void op_cmp_failure() {
        // TODO : tests pour les utilitaires (genre type_binary_op, ....)

        final Type INT = new IntType(null); 
        final Type BOOLEAN = new BooleanType(null); 
        final Type VOID = new VoidType(null); 
        final Type STRING = new StringType(null); 
         
        AbstractExpr exprString2 = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprBoolean = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprBoolean2 = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprVoid = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprString = Mockito.mock(AbstractExpr.class);

        DecacCompiler compiler = new DecacCompiler(null, null);

        try {
            Mockito.when(exprBoolean.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprBoolean2.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprVoid.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(VOID);
            Mockito.when(exprString.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(STRING);
            Mockito.when(exprString2.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(STRING);

            Mockito.when(exprBoolean.getType()).thenReturn(BOOLEAN);
            Mockito.when(exprBoolean2.getType()).thenReturn(BOOLEAN);
            Mockito.when(exprVoid.getType()).thenReturn(VOID);
            Mockito.when(exprString.getType()).thenReturn(STRING);
            Mockito.when(exprString2.getType()).thenReturn(STRING);
        } catch (ContextualError e) {
        }

        AbstractOpCmp op_cmd_bi = new Greater(exprBoolean, exprBoolean2);
        assertThrows(ContextualError.class, () -> op_cmd_bi.verifyExpr(compiler, null, null),
            "AbstractOpCmp verified with a boolean and an int as arguments.");

        AbstractOpCmp op_cmd_vb = new Equals(exprVoid, exprBoolean);
        assertThrows(ContextualError.class, () -> op_cmd_vb.verifyExpr(compiler, null, null),
            "AbstractOpCmp verified with a void and a boolean as arguments.");

        AbstractOpCmp op_cmd_ss = new Equals(exprString, exprString2);
        assertThrows(ContextualError.class, () -> op_cmd_ss.verifyExpr(compiler, null, null),
            "AbstractOpCmp verified with a void and a boolean as arguments.");
    }

    @SansObjetTest
    @DisplayName("Boolean operators test - Checks verify propagation & AbstractOpBool's type")
    public void op_bool_success() {
        final Type BOOLEAN = new BooleanType(null); 
         
        AbstractExpr exprBoolean = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprBoolean2 = Mockito.mock(AbstractExpr.class);

        try {
            Mockito.when(exprBoolean.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprBoolean2.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprBoolean.getType()).thenReturn(BOOLEAN);
            Mockito.when(exprBoolean2.getType()).thenReturn(BOOLEAN);
        } catch (ContextualError e) {
        }

        AbstractOpBool op_bool_fi = new And(exprBoolean, exprBoolean2);
        assertDoesNotThrow(() -> op_bool_fi.verifyExpr(null, null, null),
            "AbstractOpBool could not verify properly");
        assertTrue(op_bool_fi.getType().isBoolean(),
            "AbstractOpBool's type is not a BooleanType.");

        // Checks VerifyExpr propagation
        try {
            verify(exprBoolean).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
            verify(exprBoolean2).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
        } catch (ContextualError e) {
        }
    }

    @SansObjetTest
    @DisplayName("Boolean operators test - Checks exception if operand types are not booleans")
    public void op_bool_failure() {
        // TODO : tests pour les utilitaires (genre type_binary_op, ....)

        final Type INT = new IntType(null); 
        final Type BOOLEAN = new BooleanType(null); 
         
        AbstractExpr exprInt = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprInt2 = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprBoolean = Mockito.mock(AbstractExpr.class);

        try {
            Mockito.when(exprInt.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprInt2.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprBoolean.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprInt.getType()).thenReturn(INT);
            Mockito.when(exprInt2.getType()).thenReturn(INT);
            Mockito.when(exprBoolean.getType()).thenReturn(BOOLEAN);
        } catch (ContextualError e) {
        }

        AbstractOpBool op_bool_bi = new And(exprBoolean, exprInt);
        assertThrows(ContextualError.class, () -> op_bool_bi.verifyExpr(null, null, null),
            "And verified with a boolean and an int as arguments.");
        AbstractOpBool op_bool_ii = new And(exprInt, exprInt2);
        assertThrows(ContextualError.class, () -> op_bool_ii.verifyExpr(null, null, null),
            "And verified with an int and an int as arguments.");
    }

    @SansObjetTest
    @DisplayName("ReadInt & ReadFloat test - Checks if their type si right.")
    public void read_func() {
        ReadInt rInt = new ReadInt();
        ReadFloat rFloat = new ReadFloat();

        DecacCompiler compiler = new DecacCompiler(null, null);

        assertDoesNotThrow(() -> rInt.verifyExpr(compiler, null, null), "ReadInt could not verify properly.");
        assertDoesNotThrow(() -> rFloat.verifyExpr(compiler, null, null), "ReadFloat could not verify properly.");
        assertTrue(rInt.getType().isInt(), "ReadInt's type is not IntType");
        assertTrue(rFloat.getType().isFloat(), "ReadFloat's type is not FloatType");
    }


    @SansObjetTest
    @DisplayName("Unary operators test - Checks verify propagation & AbstractUnaryExpr's type")
    public void un_op_success() {
        final Type INT = new IntType(null); 
        final Type BOOLEAN = new BooleanType(null); 
         
        AbstractExpr exprBoolean = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprInt = Mockito.mock(AbstractExpr.class);

        try {
            Mockito.when(exprInt.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprBoolean.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
            Mockito.when(exprInt.getType()).thenReturn(INT);
            Mockito.when(exprBoolean.getType()).thenReturn(BOOLEAN);
        } catch (ContextualError e) {
        }

        AbstractUnaryExpr un_op_i = new UnaryMinus(exprInt);
        assertDoesNotThrow(() -> un_op_i.verifyExpr(null, null, null),
            "UnaryMinus could not verify properly");
        assertTrue(un_op_i.getType().isInt(),
            "UnaryMinus's type with an int as argument is not an IntType.");

        // Checks VerifyExpr propagation
        try {
            verify(exprInt).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
        } catch (ContextualError e) {
        }

        AbstractUnaryExpr un_op_b = new Not(exprBoolean);
        assertDoesNotThrow(() -> un_op_b.verifyExpr(null, null, null),
            "Not could not verify properly");
        assertTrue(un_op_b.getType().isBoolean(),
            "Not's type is not a BooleanType.");

        // Checks VerifyExpr propagation
        try {
            verify(exprBoolean).verifyExpr(Mockito.any(), Mockito.any(), Mockito.any());
        } catch (ContextualError e) {
        }
    }

    @SansObjetTest
    @DisplayName("Unary operators test - Checks exception if operand types are not valid")
    public void un_op_failure() {
        // TODO : tests pour les utilitaires (genre type_binary_op, ....)

        final Type INT = new IntType(null); 
        final Type VOID = new VoidType(null); 
         
        AbstractExpr exprInt = Mockito.mock(AbstractExpr.class);
        AbstractExpr exprVoid = Mockito.mock(AbstractExpr.class);

        try {
            Mockito.when(exprInt.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(INT);
            Mockito.when(exprVoid.verifyExpr(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(VOID);
            Mockito.when(exprInt.getType()).thenReturn(INT);
            Mockito.when(exprVoid.getType()).thenReturn(VOID);
        } catch (ContextualError e) {
        }

        AbstractUnaryExpr un_op_bi = new Not(exprInt);
        assertThrows(ContextualError.class, () -> un_op_bi.verifyExpr(null, null, null),
            "'Not' verified with an int as arguments.");
        AbstractUnaryExpr un_op_ii = new UnaryMinus(exprVoid);
        assertThrows(ContextualError.class, () -> un_op_ii.verifyExpr(null, null, null),
            "UnaryMinus verified with a void as arguments.");
    }
}