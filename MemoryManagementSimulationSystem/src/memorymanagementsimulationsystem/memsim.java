/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.io.Console;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lew Gordon
 */
public class memsim {

    public static ProcessManager pManager;

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: java memsim <algorithm type> <probability>");
            return;
        }
        configureSimulation(args[0]);
        runSimulation(Integer.valueOf(args[1]), false);
    }

    public static void configureSimulation(String algorithm) {
        Random processSize = new Random();
        pManager = new ProcessManager();
        AllocationAlgorithm algorithmType = AllocationAlgorithm.getInstance();
        algorithmType.setAlgorithm("first");
        for (int i = 0; i < 10; i++) {
            pManager.addNewProcess(processSize.nextInt(90) + 10);
        }
        algorithmType.setAlgorithm(algorithm);
    }

    public static void runSimulation(int processEnterProbability, boolean AUTOMATE) {
        Random myRandomNumber = new Random();
        Random processSize = new Random();
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        System.out.println("Running simulation. Type 'c' to continue and 'q' to quit.");
        while (true) {//"c".equals(c.readLine())) {
            if (processEnterProbability >= myRandomNumber.nextInt(100)) {
                pManager.addNewProcess(processSize.nextInt(90) + 10);
            }
            pManager.exitProcesses();
            pManager.printMemory();
            System.out.println("Type 'c' to continue and 'q' to quit.");
            if (AUTOMATE == true) {
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(memsim.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if ("q".equals(c.readLine())) {
                    break;
                }
            }
        }
    }
}
