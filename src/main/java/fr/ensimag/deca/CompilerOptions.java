package fr.ensimag.deca;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * User-specified options influencing the compilation.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class CompilerOptions {
    public static final int QUIET = 0;
    public static final int INFO  = 1;
    public static final int DEBUG = 2;
    public static final int TRACE = 3;
    public int getDebug() {
        return debug;
    }

    public boolean getParallel() {
        return parallel;
    }

    public boolean getPrintBanner() {
        return printBanner;
    }
    
    public boolean getpOption() {
		return poption;
	}
    
    public boolean getNoCkeck() {
    	return noCheck;
    }
    
    public boolean getVerif() {
    	return verif;
    }
    
    public boolean isForGBA() {
    	return forGBA;
    }

    public int getNbRegs() {
    	return nbRegs;
    }
    
    public List<File> getSourceFiles() {
        return Collections.unmodifiableList(sourceFiles);
    }
    private boolean poption = false;
    private int debug = 0;
    private boolean parallel = false;
    private boolean printBanner = false;
    private int nbRegs;
    private boolean noCheck = false;
    private boolean verif = false;
    private boolean rChecked = false;
    private boolean forGBA = false;
    private List<File> sourceFiles = new ArrayList<File>();
    private HashMap<String, File> nameFiles = new HashMap<String, File>();

    
    public void parseArgs(String[] args) throws CLIException {
        boolean isRArg = false;
    	for(String arg : args) {
    		if(isRArg && !rChecked) {
    			int nb;
    			try {
    				nb = Integer.parseInt(arg);
    			} catch(NumberFormatException e) {
    				throw new CLIException("L'option -r prend en paramètre un nombre entier entre 4 et 16 inclus.");
    			}
    			nbRegs = nb;
    			rChecked = true;
    			isRArg = false;
    			continue;
    		} else if(isRArg && rChecked)
    			throw new CLIException("L'option -r a été utilisée plus d'une fois.");
    		
        	switch(arg) {
        	case "-b":
        		printBanner = true;
        		break;
        	case "-p":
        		poption = true;
        		break;
        	case "-v":
        		verif = true;
        		break;
        	case "-n":
        		noCheck = true;
        		break;
        	case "-r":
        		isRArg = true;
        		break;
        	case "-d":
        		debug++;
        		break;
        	case "-P":
        		parallel = true;
        		break;
        	case "--gba":
        		forGBA = true;
        		break;
        	default:
        		// Nom de fichier
        		if(arg.lastIndexOf(".deca") != arg.length()-5)
        			throw new CLIException("decac n'accepte que les fichiers d'extension .deca.");
        		File f = new File(arg);
        		if(!f.exists())
        			throw new CLIException("Le fichier "+arg+" n'existe pas.");
        		nameFiles.put(arg, f);
        	}
        }
    	
    	if(!rChecked) {
    		if(isForGBA())
    			nbRegs = 11;
    		else
    			nbRegs = 16;
    	}
    	if(!isForGBA() && (nbRegs<4 || nbRegs>16))
			throw new CLIException("L'option -r n'accepte que des nombres entre 4 et 16 inclus pour IMA.");
    	if(isForGBA() && (nbRegs<4 || nbRegs>11))
			throw new CLIException("L'option -r n'accepte que des nombres entre 4 et 11 inclus pour GBA.");
    	if(verif && poption)
    		throw new CLIException("Les options -v et -p sont incompatibles.");
    	if(forGBA && (verif || poption))
    		throw new CLIException("L'option --gba n'est pas compatible avec les options -v et -p.");
    	if(printBanner && (parallel || debug!=0 || rChecked || noCheck || verif || poption || !nameFiles.isEmpty() || forGBA))
    		throw new CLIException("L'option -b est incompatibles avec les autres options.");
    	
    	/* L'utilisation de la HashMap nous permet d'être certain que chaque fichier n'est compilé qu'une fois */
    	for(HashMap.Entry<String, File> entry : nameFiles.entrySet())
    		sourceFiles.add(entry.getValue());
    	
        Logger logger = Logger.getRootLogger();
        // map command-line debug option to log4j's level.
        switch (getDebug()) {
        case QUIET: break; // keep default
        case INFO:
            logger.setLevel(Level.INFO); break;
        case DEBUG:
            logger.setLevel(Level.DEBUG); break;
        case TRACE:
            logger.setLevel(Level.TRACE); break;
        default:
            logger.setLevel(Level.ALL); break;
        }
        logger.info("Application-wide trace level set to " + logger.getLevel());

        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect!!!
        if (assertsEnabled) {
            logger.info("Java assertions enabled");
        } else {
            logger.info("Java assertions disabled");
        }
    }

    protected void displayUsage() {
        PrintStream e = System.err;
    	String usage = "Usage : decac [[-p | -v] [-n] [-r X] [-d]* [-P] [--gba] <fichier deca>...] | [-b]";
        e.println(usage);
    }
}
