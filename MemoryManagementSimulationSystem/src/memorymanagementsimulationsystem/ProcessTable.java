/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.util.ArrayList;

/**
 *
 * @author Lew Gordon
 */
public class ProcessTable {

    public ArrayList<Process> processes;
    
    public ProcessTable(){
        processes = new ArrayList<Process>();
    }
    public String getProcessses() {
        String toReturn = new String();
        toReturn += "*****PROCESS LIST*****\n";
        for(Process p : processes){
            toReturn += p.toString()+'\n';
        }
        return toReturn;
    }

    public void addNewProcess(String processName, Integer processSize) {
        processes.add(new Process(processName,processSize));
    }
}
