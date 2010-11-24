/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.io.Console;
import java.util.Random;

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
        runSimulation(Integer.valueOf(args[1]));
    }

    public static void configureSimulation(String algorithm) {
        pManager = new ProcessManager();
        AllocationAlgorithm algorithmType = AllocationAlgorithm.getInstance();
        algorithmType.setAlgorithm(algorithm);
    }

    public static void runSimulation(int processEnterProbability) {
        Random myRandomNumber = new Random();
        Random processSize = new Random();
        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        while ("c".equals(c.readLine())) {
            if(processEnterProbability <= myRandomNumber.nextInt(100)){
                System.out.println("adding process...");
                pManager.addNewProcess(processSize.nextInt(90)+10);
            }
            pManager.exitProcesses();
        }
        pManager.printMemory();
    }
}
