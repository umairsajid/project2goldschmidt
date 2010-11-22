/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

/**
 *
 * @author Lew Gordon
 */
public class memsim {

    public static ProcessManager pManager;

    public static void main(String args[]) {
        configureSimulation(args);
        runSimulation();
    }

    public static void configureSimulation(String args[]) {
        pManager = new ProcessManager();
        AllocationAlgorithm algorithmType = AllocationAlgorithm.getInstance();
        algorithmType.setAlgorithm("best");
    }

    public static void runSimulation() {
        for (int i = 0; i < 13; i++) {
            pManager.addNewProcess(80);
        }
        pManager.removeProcessFromMemory("A");
        pManager.removeProcessFromMemory("D");
        for (int i = 0; i < 13; i++) {
            pManager.addNewProcess(80);
        }
        pManager.printMemory();
        //pManager.printProcesses();
    }
}
