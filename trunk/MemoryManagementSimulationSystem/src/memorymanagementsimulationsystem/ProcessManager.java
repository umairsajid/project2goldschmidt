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

    public MainMemory mainMemory;
    public ProcessTable processTable;
    public Character processNamer = 'A';

    public ProcessManager(){
        mainMemory = new MainMemory();
        processTable = new ProcessTable();
    }
    public void defragmentMemory() {
    }

    public void addNewProcess(Integer processSize) {
        /*If we can actually fit the process in.*/
        if(mainMemory.availableMemory()>processSize){
            mainMemory.addNewProcess(processNamer.toString(),processSize);
            processTable.addNewProcess(processNamer.toString(), processSize);
            processNamer++;
            return;
        }
        System.out.println("Process Manager: No room for process.");
    }
    public void printMemory(){
        mainMemory.displayMemory();
    }
    public void printProcesses(){
        System.out.println(processTable.getProcessses());
    }
}
