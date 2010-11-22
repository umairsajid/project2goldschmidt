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
public class SortByPosition implements Comparator {

    public int compare(Object o1, Object o2) {
        MemoryBlock memBlock1, memBlock2;
        LastAllocatedBlockPosition startPos = LastAllocatedBlockPosition.getInstance();
        int position = startPos.getPosition();
        memBlock1 = (MemoryBlock) o1;
        memBlock2 = (MemoryBlock) o2;
        if (Math.abs(memBlock1.getStartingPosition() - position) < Math.abs(memBlock2.getStartingPosition() - position)) {
            return -1;
        }
        if (Math.abs(memBlock1.getStartingPosition() - position) > Math.abs(memBlock2.getStartingPosition() - position)) {
            return 1;
        }
        return 0;
    }
}
