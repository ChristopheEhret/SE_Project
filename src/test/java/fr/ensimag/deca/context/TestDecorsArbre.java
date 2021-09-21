package fr.ensimag.deca.context;

import com.sun.tools.sjavac.Log;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tree.ListDeclClass;
import fr.ensimag.deca.tree.ListDeclVar;
import fr.ensimag.deca.tree.ListExpr;
import fr.ensimag.deca.tree.ListInst;
import fr.ensimag.deca.tree.Main;
import fr.ensimag.deca.tree.Println;
import fr.ensimag.deca.tree.Program;
import fr.ensimag.deca.tree.StringLiteral;

public class TestDecorsArbre {
    public static void main(String[] args) {
        ListDeclVar vars = new ListDeclVar();
        ListDeclClass classes = new ListDeclClass();

        ListExpr arguments = new ListExpr();
        arguments.add(new StringLiteral("Hello World"));
        ListInst insts = new ListInst();
        insts.add(new Println(false, arguments));
        Main main = new Main(vars, insts);
        Program prog = new Program(classes, main);

        DecacCompiler compiler = new DecacCompiler(null, null);
        try {
            prog.verifyProgram(compiler);
        } catch(ContextualError e){
            Log.debug("Erreur de vérification !!");
        }

    }    
}
