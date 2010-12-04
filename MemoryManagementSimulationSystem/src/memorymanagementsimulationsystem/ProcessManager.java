/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Lew Gordon
 */
public class ProcessManager {

    private final int OUT_OF_MEMORY = -1;
    private ArrayList<Process> processes;
    public MainMemory mainMemory;
    public Character processNamer = 'A';

    public ProcessManager() {
        mainMemory = new MainMemory();
        processes = new ArrayList<Process>();
    }

    public void defragmentMemory() {
        System.out.println("Defragmenting...");
        /**/
        //updateProcesses = mainMemory.defragment();
    }

    public void addNewProcess(Integer processSize) {
        int processStartingPosition;
        /*If we can actually fit the process in.*/
        if (mainMemory.availableMemory() >= processSize) {
            if ((processStartingPosition = mainMemory.addNewProcess(processNamer.toString(), processSize)) == OUT_OF_MEMORY) {
                System.out.println("Please defragment, out of memory.");
                return;
            }
            System.out.println("Adding process "+processNamer);
            processes.add(new Process(processNamer.toString(), processSize, processStartingPosition));
            if (processNamer.equals('\\')) {
                processNamer = 'a';
            } else if (processNamer.equals('z')) {
                processNamer = 'A';
            } else {
                processNamer++;
            }
            return;
        }
        defragmentMemory();
    }

    public void printMemory() {
        mainMemory.displayMemory();
    }

    public void printProcesses() {
        System.out.println(processes.toString());
    }

    public void removeProcessFromMemory(String processName) {
        Process p;
        if ((p = lookup(processName)) == null) {
            return;
        }
        mainMemory.removeProcess(p.getStartingPosition(), p.getSize());
        processes.remove(p);
    }

    private Process lookup(String processName) {
        for (Process p : processes) {
            if (processName.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    public void exitProcesses() {
        Random processExitRNG = new Random();
        for (int i = 0; i < processes.size(); i++) {
            if (10 >= processExitRNG.nextInt(100)) {
                System.out.println("Process "+processes.get(i).getName()+" exiting.");
                mainMemory.removeProcess(processes.get(i).getStartingPosition(), processes.get(i).getSize());
                processes.remove(processes.get(i));
            }
        }
    }
}
