/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

/**
 *
 * @author Lew Gordon
 */
public class ProcessManager {

    private final int OUT_OF_MEMORY = -1;
    public MainMemory mainMemory;
    public ProcessTable processTable;
    public Character processNamer = 'A';

    public ProcessManager() {
        mainMemory = new MainMemory();
        processTable = new ProcessTable();
    }

    public void defragmentMemory() {
    }

    public void addNewProcess(Integer processSize) {
        int processStartingPosition;
        /*If we can actually fit the process in.*/
        if (mainMemory.availableMemory() >= processSize) {
            if ((processStartingPosition = mainMemory.addNewProcess(processNamer.toString(), processSize)) == OUT_OF_MEMORY) {
                System.out.println("Please defragment, out of memory.");
                return;
            }
            processTable.addNewProcess(processNamer.toString(), processSize, processStartingPosition);
            if (processNamer.equals('\\')) {
                processNamer = 'a';
            }
            processNamer++;
            return;
        }
        System.out.println("Process Manager: No room for process, please defragment.");
    }

    public void printMemory() {
        mainMemory.displayMemory();
    }

    public void printProcesses() {
        System.out.println(processTable.getProcessses());
    }

    public void removeProcessFromMemory(String processName) {
        Process p;
        if ((p = processTable.lookup(processName)) == null) {
            return;
        }
        mainMemory.removeProcess(p.getStartingPosition(), p.getSize());
        processTable.removeProcess(p);
    }
}
