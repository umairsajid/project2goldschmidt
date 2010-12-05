/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.util.Comparator;

/**
 * Largest process comes first in the array.
 * @author lgordon2
 */
public class SortByLargestProcess implements Comparator {

    public SortByLargestProcess() {
    }

    public int compare(Object t1, Object t2) {
        Process p1 = (Process) t1;
        Process p2 = (Process) t2;
        if (p1.getSize() < p2.getSize()) {
            return 1;
        }
        if (p1.getSize() > p2.getSize()) {
            return -1;
        }
        return 0;
    }
}

