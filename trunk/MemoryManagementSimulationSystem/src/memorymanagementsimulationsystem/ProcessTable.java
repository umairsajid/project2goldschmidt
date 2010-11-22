/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.util.HashMap;

/**
 *
 * @author Lew Gordon
 */
public class ProcessTable {

    public HashMap<String,Process> processes;
    
    public ProcessTable(){
        processes = new HashMap<String,Process>();
    }
    public String getProcessses() {
        return processes.toString();
    }

    public void addNewProcess(String processName, Integer processSize, int processStartingPosition) {
        processes.put(processName,new Process(processName,processSize,processStartingPosition));
    }

    protected void removeProcess(Process p) {
        processes.remove(p.getName());
    }

    protected Process lookup(String processName) {
        return processes.get(processName);
    }
}
