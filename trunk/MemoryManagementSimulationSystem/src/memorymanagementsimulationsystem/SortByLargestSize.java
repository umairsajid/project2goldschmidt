/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.util.Comparator;

/**
 *
 * @author Lew Gordon
 */
public class SortByLargestSize implements Comparator {

    public int compare(Object o1, Object o2) {
        MemoryBlock memBlock1, memBlock2;
        memBlock1 = (MemoryBlock) o1;
        memBlock2 = (MemoryBlock) o2;
        if (memBlock1.getSize() < memBlock2.getSize()) {
            return 1;
        }
        if (memBlock1.getSize() > memBlock2.getSize()) {
            return -1;
        }
        return 0;
    }
}
