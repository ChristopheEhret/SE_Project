package fr.ensimag.deca.context;

import static fr.ensimag.deca.context.LoggerColor.redText;
import static fr.ensimag.deca.context.LoggerColor.greenText;
import static fr.ensimag.deca.context.LoggerColor.orangeText;
import static fr.ensimag.deca.context.LoggerColor.yellowText;

import java.time.Clock;

import org.apache.log4j.Logger;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.DecacInternalError;

public class TestType {
    private static Logger log = Logger.getLogger(TestExpression.class);
    public static void main(String[] args) {


        int totalTests = 0;
        int completedTests = 0;
        Clock clock = Clock.systemDefaultZone();
        long currentMs = clock.millis();

        DecacCompiler compiler = new DecacCompiler(null, null);

        try {
            totalTests++;
            log.debug("Test #" + totalTests + " - IntType - Begin");

            log.debug("SubTest");
            TypeDefinition def = compiler.getTypeDef(compiler.getOrAddSymbol("int"));
            Type type = compiler.getType(compiler.getOrAddSymbol("int"));

            if(def.getType() != type)
                throw new InternalError("Type is not tied to TypeDefiniton");

            if(!(type instanceof IntType))
                throw new InternalError("type is not an IntType");

            log.debug("Test #" + totalTests + " - IntType - "+ greenText("OK"));
            completedTests++;
        } catch (DecacInternalError e) {
            log.error("Test #" + totalTests + " - IntType - DecacInternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        } catch (InternalError e) {
            log.error("Test #" + totalTests + " - IntType - InternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        }
        log.debug("Test #" + totalTests + " IntType - END ------------------------------");

        try {
            totalTests++;
            log.debug("Test #" + totalTests + " - FloatType - Begin");

            log.debug("SubTest");
            TypeDefinition def = compiler.getTypeDef(compiler.getOrAddSymbol("float"));
            Type type = compiler.getType(compiler.getOrAddSymbol("float"));

            if(def.getType() != type)
                throw new InternalError("Type is not tied to TypeDefiniton");

            if(!(type instanceof FloatType))
                throw new InternalError("type is not an FloatType");
                
            log.debug("Test #" + totalTests + " - FloatType - "+ greenText("OK"));
            completedTests++;
        } catch (DecacInternalError e) {
            log.error("Test #" + totalTests + " - FloatType - DecacInternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        } catch (InternalError e) {
            log.error("Test #" + totalTests + " - FloatType - InternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        }
        log.debug("Test #" + totalTests + " IntType - END ------------------------------");

        try {
            totalTests++;
            log.debug("Test #" + totalTests + " - BooleanType - Begin");

            log.debug("SubTest");
            TypeDefinition def = compiler.getTypeDef(compiler.getOrAddSymbol("boolean"));
            Type type = compiler.getType(compiler.getOrAddSymbol("boolean"));

            if(def.getType() != type)
                throw new InternalError("Type is not tied to TypeDefiniton");

            if(!(type instanceof BooleanType))
                throw new InternalError("type is not an BooleanType");
                
            log.debug("Test #" + totalTests + " - BooleanType - "+ greenText("OK"));
            completedTests++;
        } catch (DecacInternalError e) {
            log.error("Test #" + totalTests + " - BooleanType - DecacInternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        } catch (InternalError e) {
            log.error("Test #" + totalTests + " - BooleanType - InternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        }
        log.debug("Test #" + totalTests + " BooleanType - END ------------------------------");

        try {
            totalTests++;
            log.debug("Test #" + totalTests + " - StringType - Begin");

            log.debug("SubTest");
            TypeDefinition def = compiler.getTypeDef(compiler.getOrAddSymbol("string"));
            Type type = compiler.getType(compiler.getOrAddSymbol("string"));

            if(def.getType() != type)
                throw new InternalError("Type is not tied to TypeDefiniton");

            if(!(type instanceof StringType))
                throw new InternalError("type is not an StringType");
                
            log.debug("Test #" + totalTests + " - StringType - "+ greenText("OK"));
            completedTests++;
        } catch (DecacInternalError e) {
            log.error("Test #" + totalTests + " - StringType - DecacInternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        } catch (InternalError e) {
            log.error("Test #" + totalTests + " - StringType - InternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        }
        log.debug("Test #" + totalTests + " StringType - END ------------------------------");

        try {
            totalTests++;
            log.debug("Test #" + totalTests + " - VoidType - Begin");

            log.debug("SubTest");
            TypeDefinition def = compiler.getTypeDef(compiler.getOrAddSymbol("void"));
            Type type = compiler.getType(compiler.getOrAddSymbol("void"));
            
            if(!(type instanceof VoidType))
                throw new InternalError("type is not an VoidType");
                
            if(def.getType() != type)
                throw new InternalError("Type is not tied to TypeDefiniton");

            log.debug("Test #" + totalTests + " - VoidType - "+ greenText("OK"));
            completedTests++;
        } catch (DecacInternalError e) {
            log.error("Test #" + totalTests + " - VoidType - DecacInternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        } catch (InternalError e) {
            log.error("Test #" + totalTests + " - VoidType - InternalError ERROR : " + redText("FAIL"));
            log.error(orangeText(e.toString()));
        }
        log.debug("Test #" + totalTests + " VoidType - END ------------------------------");

        float pourcentage = ((float)completedTests)/totalTests;
        String pourcentageStr = String.valueOf(pourcentage * 100) + "%";
        if(pourcentage >= 0.75) {
            pourcentageStr = greenText(pourcentageStr);
        } else if (pourcentage >= 0.50) {
            pourcentageStr = yellowText(pourcentageStr);

        } else if (pourcentage >= 0.25) {
            pourcentageStr = orangeText(pourcentageStr);

        } else {
            pourcentageStr = redText(pourcentageStr);
        }

        log.debug("All test completed - TOTAL : " + greenText(String.valueOf(totalTests)) + " - COMPLETED : " + greenText(String.valueOf(completedTests)) + " - FAILED : " + redText(String.valueOf(totalTests - completedTests)) + " - POURCENTAGE " + pourcentageStr);
        log.debug("Time taken : " + (((double)clock.millis()) - currentMs)/1000 + "s");
    }
}
