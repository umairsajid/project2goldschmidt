/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.util.Random;

/**
 *
 * @author Lew Gordon
 */
public class memsim {

    public static ProcessManager pManager;

    public static void main(String args[]) {
        if(args.length != 1){
            System.out.println("Usage: java memsim <algorithm type>");
            return;
        }
        configureSimulation(args);
        runSimulation();
    }

    public static void configureSimulation(String args[]) {
        pManager = new ProcessManager();
        AllocationAlgorithm algorithmType = AllocationAlgorithm.getInstance();
        algorithmType.setAlgorithm(args[0]);
    }

    public static void runSimulation() {
        Random myRandomNumber = new Random();
        for (int i = 0; i < 13; i++) {
            pManager.addNewProcess(myRandomNumber.nextInt(90)+10);
        }
        pManager.removeProcessFromMemory("A");
        pManager.removeProcessFromMemory("D");
        for (int i = 0; i < 25; i++) {
            pManager.addNewProcess(myRandomNumber.nextInt(90)+10);
        }
        pManager.printMemory();
    }
}
