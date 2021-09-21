package fr.ensimag.deca;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

/**
 * Main class for the command-line Deca compiler.
 *
 * @author gl27
 * @date 01/01/2021
 */
public class DecacMain {
    private static Logger LOG = Logger.getLogger(DecacMain.class);
    
    public static void main(String[] args) {
        // example log4j message.
        LOG.info("Decac compiler started");
        boolean error = false;
        final CompilerOptions options = new CompilerOptions();
        try {
            options.parseArgs(args);
        } catch (CLIException e) {
            System.err.println("Error during option parsing:\n"
                    + e.getMessage());
            options.displayUsage();
            System.exit(1);
        }
        if (options.getPrintBanner()) {
            System.out.println("Groupe 5 Equipe 27 - Adrien Kaufman, Mathis Lavigne, Berkan Kesman, Quentin Cousin, Christophe Ehret");
            System.exit(0);
        }
        if (options.getSourceFiles().isEmpty()) {
            throw new UnsupportedOperationException("decac without argument not yet implemented");
        }
        if (options.getParallel()) {
            // A FAIRE : instancier DecacCompiler pour chaque fichier à
            // compiler, et lancer l'exécution des méthodes compile() de chaque
            // instance en parallèle. Il est conseillé d'utiliser
            // java.util.concurrent de la bibliothèque standard Java.

            int currentSourceIndex = 0;
            ArrayList<Future<Boolean>> listeThreads = new ArrayList<Future<Boolean>>();
            int nbMaxThreads = Math.min(java.lang.Runtime.getRuntime().availableProcessors(), options.getSourceFiles().size());
            // On ajoute les premiers threads
            for(currentSourceIndex = 0; currentSourceIndex < nbMaxThreads; currentSourceIndex++) {
                ExecutorService executor = Executors.newFixedThreadPool(8);

                DecacCompiler compiler = new DecacCompiler(options, options.getSourceFiles().get(currentSourceIndex));
                listeThreads.add(executor.submit(() -> compiler.compile()));
            }

            ArrayList<Future<Boolean>> threadsToDelete = new ArrayList<Future<Boolean>>(); 
            while(currentSourceIndex < options.getSourceFiles().size()) { 
                // On parcours les threads en vérifiant si il y en a pas qui ont terminé
                for(Future<Boolean> f : listeThreads) {
                    try {
                        if(f.get(0, TimeUnit.SECONDS))
                            error = true;
                        // Si le thread a terminé, on le marque comme 'à supprimer'
                        threadsToDelete.add(f);
                    } catch (TimeoutException e) {
                    }  catch (Exception e) {
                        LOG.error(e);
                    }
                }

                for(Future<Boolean> f : threadsToDelete) {
                    // On enlève les thread supprimés  
                    listeThreads.remove(f);

                    // Lance le prochain thread
                    ExecutorService executor = Executors.newFixedThreadPool(1);

                    DecacCompiler compiler = new DecacCompiler(options, options.getSourceFiles().get(currentSourceIndex));
                    listeThreads.add(executor.submit(() -> compiler.compile()));
                    currentSourceIndex++;    
                }

                threadsToDelete.clear();
            }

            // A la fin, on attend que tous les threads finissent
            for(Future<Boolean> f : listeThreads) {
                try {
                    if(f.get())
                        error = true;
                } catch (Exception e) {
                    LOG.error(e);
                }
            }
            // throw new UnsupportedOperationException("Parallel build not yet implemented");
        } else {
            for (File source : options.getSourceFiles()) {
                DecacCompiler compiler = new DecacCompiler(options, source);
                if (compiler.compile()) {
                    error = true;
                }
            }
        }
        System.exit(error ? 1 : 0);
    }
}
