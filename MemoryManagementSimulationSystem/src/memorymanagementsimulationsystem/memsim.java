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
    public static void main(String args[]){
        ProcessManager pManager = new ProcessManager();
        for(int i=0;i<26;i++){
            pManager.addNewProcess(80);
        }
        pManager.printMemory();
        pManager.printProcesses();
    }
}
