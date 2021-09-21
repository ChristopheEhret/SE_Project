package fr.ensimag.deca.context;

import static fr.ensimag.deca.context.LoggerColor.redText;
import static fr.ensimag.deca.context.LoggerColor.greenText;
import static fr.ensimag.deca.context.LoggerColor.orangeText;
import static fr.ensimag.deca.context.LoggerColor.yellowText;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.tags.HelloWorldTest;
import fr.ensimag.deca.context.tags.SansObjetTest;
import fr.ensimag.deca.tree.AbstractExpr;
import fr.ensimag.deca.tree.AbstractPrint;
import fr.ensimag.deca.tree.BooleanLiteral;
import fr.ensimag.deca.tree.FloatLiteral;
import fr.ensimag.deca.tree.IfThenElse;
import fr.ensimag.deca.tree.IntLiteral;
import fr.ensimag.deca.tree.ListExpr;
import fr.ensimag.deca.tree.ListInst;
import fr.ensimag.deca.tree.Println;
import fr.ensimag.deca.tree.StringLiteral;
import fr.ensimag.deca.tree.While;

public class TestIntruction {
    private static Logger log = Logger.getLogger(TestExpression.class);

    @HelloWorldTest
    @DisplayName("Print Test - Checks that the print instruction can verify with correct arguments")
    public void print_test_success() {
        assertDoesNotThrow(() -> {
            DecacCompiler compiler = new DecacCompiler(null, null);
            ListExpr arguments = new ListExpr();
            arguments.add(new StringLiteral("test"));
            arguments.add(new IntLiteral(0));
            arguments.add(new FloatLiteral(2.5f));
            AbstractPrint abstractPrint = new Println(false, arguments);
            abstractPrint.verifyInst(compiler, null, null, null);
        }, "Print instruction cannot verify with correct arguments.");
    }

    @HelloWorldTest
    @DisplayName("Print Test - Checks that the print instruction doesn't verify with incorrect arguments")
    public void print_test_failure() {
        assertThrows(ContextualError.class, () -> {
            DecacCompiler compiler = new DecacCompiler(null, null);
            ListExpr arguments = new ListExpr();
            arguments.add(new BooleanLiteral(true));
            AbstractPrint abstractPrint2 = new Println(false, arguments);
            abstractPrint2.verifyInst(compiler, null, null, null);
        }, "Print instruction verified with illegal arguments.");
    }

    @SansObjetTest
    @DisplayName("IfThenElse Test - Checks that IfThenElse accepts a boolean condition & checks verify propagation")
    public void ifThenElse_test_success() {
        final Type BOOLEAN = new BooleanType(null);

        DecacCompiler compiler = new DecacCompiler(null, null);        

        ListInst inst_if = Mockito.mock(ListInst.class);
        ListInst inst_else = Mockito.mock(ListInst.class);

        AbstractExpr condition = Mockito.mock(AbstractExpr.class);
        
        IfThenElse ifThenElse = new IfThenElse(condition, inst_if, inst_else);
        ListInst inst_ifThenElse = new ListInst();
        inst_ifThenElse.add(ifThenElse);

        assertDoesNotThrow(() -> inst_ifThenElse.verifyListInst(compiler, null, null, null), 
            "IfThenElse could not verify properly.");

        try {
            verify(inst_if).verifyListInst(Mockito.eq(compiler), Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());
            verify(inst_else).verifyListInst(Mockito.eq(compiler), Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());
            verify(condition).verifyCondition(Mockito.eq(compiler), Mockito.anyObject(), Mockito.anyObject());
        }   catch (ContextualError e) {
        }
    }

    @SansObjetTest
    @DisplayName("IfThenElse Test - Checks that IfThenElse doesn't accept a non-boolean condition")
    public void ifThenElse_test_failure() {
        DecacCompiler compiler = new DecacCompiler(null, null);        

        ListInst inst_if = Mockito.mock(ListInst.class);
        ListInst inst_else = Mockito.mock(ListInst.class);

        AbstractExpr condition = new FloatLiteral(0);
        
        IfThenElse ifThenElse = new IfThenElse(condition, inst_if, inst_else);
        ListInst inst_ifThenElse = new ListInst();
        inst_ifThenElse.add(ifThenElse);

        assertThrows(ContextualError.class, () -> inst_ifThenElse.verifyListInst(compiler, null, null, null), 
            "IfThenElse verified a non-boolean condition.");
    }

    @SansObjetTest
    @DisplayName("While Test - Checks that While accepts a boolean condition & checks verify propagation")
    public void whileDo_test_success() {
        final Type BOOLEAN = new BooleanType(null);
        DecacCompiler compiler = new DecacCompiler(null, null);        

        ListInst inst_while = Mockito.mock(ListInst.class);
        AbstractExpr condition = Mockito.mock(AbstractExpr.class);
        
        try {
            Mockito.when(condition.verifyExpr(Mockito.eq(compiler), Mockito.anyObject(), Mockito.anyObject())).thenReturn(BOOLEAN);
        } catch (ContextualError e) {
        }

        While whileDo = new While(condition, inst_while);
        ListInst inst_whileDo = new ListInst();
        inst_whileDo.add(whileDo);

        assertDoesNotThrow(() -> inst_whileDo.verifyListInst(compiler, null, null, null), 
            "While could not verify properly.");

        try {
            verify(inst_while).verifyListInst(Mockito.eq(compiler), Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject());
            verify(condition).verifyCondition(Mockito.eq(compiler), Mockito.anyObject(), Mockito.anyObject());
        }   catch (ContextualError e) {
        }
    }

    @SansObjetTest
    @DisplayName("While Test - Checks that While doesn't accept a non-boolean condition")
    public void whileDo_test_failure() {
        final Type INT = new IntType(null);
        DecacCompiler compiler = new DecacCompiler(null, null);        

        ListInst inst_while = Mockito.mock(ListInst.class);
        // AbstractExpr condition = Mockito.mock(AbstractExpr.class);
        AbstractExpr condition = new FloatLiteral(0);

        While whileDo = new While(condition, inst_while);
        ListInst inst_whileDo = new ListInst();
        inst_whileDo.add(whileDo);

        assertThrows(ContextualError.class, () -> inst_whileDo.verifyListInst(compiler, null, null, null), 
            "While verified a non-boolean condition.");
    }
}
