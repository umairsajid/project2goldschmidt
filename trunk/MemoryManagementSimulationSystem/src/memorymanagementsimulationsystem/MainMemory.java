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
public class MainMemory {

    private final int OUT_OF_MEMORY = -1;
    private final String osRepresentation = "#";  //What to display if the space is owned by the OS
    private final String freeRepresentation = ".";  //What to display if the space is free
    private final String OP_SYS = "OS";
    private MemoryCell[] memoryCells;
    private Integer freeMemory;

    public MainMemory() {
        memoryCells = new MemoryCell[2400];
        for (int i = 0; i < 2400; i++) {
            memoryCells[i] = new MemoryCell(i);
            /*First 80 cells are OS owned.*/
            if (i < 80) {
                memoryCells[i].setOwnedProcessType(OP_SYS);
            }
        }
        freeMemory = 2320;
    }

    public void displayMemory() {
        for (MemoryCell m : memoryCells) {
            if (m.isOccupied()) {
                if (OP_SYS.equals(m.getOwnedProcessType())) {
                    System.out.print(osRepresentation);
                } else {
                    System.out.print(m.getOwnedProcessType());
                }
            } else {
                System.out.print(freeRepresentation);
            }
            if (m.getPosition() % 80 == 79) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public Integer addNewProcess(String processName, Integer processSize) {
        return addNewProcessFirst(processName, processSize);
    }

    public void removeProcess(Integer startingPosition, Integer size) {
        for (int i = startingPosition; i < startingPosition + size; i++) {
            memoryCells[i].freeCell();
        }
    }

    private void allocateProcess(String processName, int processSize, int processStartingPosition) {
        for (int i = processStartingPosition; i < processStartingPosition + processSize; i++) {
            memoryCells[i].setOwnedProcessType(processName);
        }
        freeMemory -= processSize;
    }

    private Integer getFirstFreeCellLocation(int startingLocation) {
        for (int i = startingLocation; i < memoryCells.length; i++) {
            if (!memoryCells[i].isOccupied()) {
                return memoryCells[i].getPosition();
            }
        }
        return OUT_OF_MEMORY;
    }

    private int getSizeOfMemoryBlock(Integer startingPosition) {
        int size = 0;
        int position = startingPosition;
        try {
            while (!memoryCells[position].isOccupied()) {
                size++;
                position++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return size;
    }

    public int availableMemory() {
        return freeMemory;
    }

    public int addNewProcessFirst(String processName, Integer processSize) {
        int startingFreePosition = 0;
        int processStartingPosition;
        int freeMemBlockSize;

        /*Check for first free memory cell*/
        if ((startingFreePosition = getFirstFreeCellLocation(0)) < 0) {
            return OUT_OF_MEMORY;
        }

        /*Check size of free memory block.*/
        while ((freeMemBlockSize = getSizeOfMemoryBlock(startingFreePosition)) < processSize) {
            /*Memory block too small check next one.*/
            if ((startingFreePosition = getFirstFreeCellLocation(startingFreePosition + freeMemBlockSize)) < 0) {
                return OUT_OF_MEMORY;
            }
            System.out.println("Free mem block size is " + freeMemBlockSize);
        }
        processStartingPosition = startingFreePosition;

        /*Allocate process at starting position.*/
        allocateProcess(processName, processSize, processStartingPosition);

        return processStartingPosition;
    }

    public int addNewProcessNext(String processName, Integer processSize) {
        return -1;
    }

    public int addNewProcessWorst(String processName, Integer processSize) {
        return -1;
    }

    public int addNewProcessBest(String processName, Integer processSize) {
        int startingFreePosition = 0;
        int processStartingPosition;
        int freeMemBlockSize;
        ArrayList<MemoryBlock> freeMemoryBlocks = new ArrayList<MemoryBlock>();

        /*Find all free memory blocks*/
        

        /*Check for first free memory cell*/
        if ((startingFreePosition = getFirstFreeCellLocation(0)) < 0) {
            return OUT_OF_MEMORY;
        }

        /*Check size of free memory block.*/
        while ((freeMemBlockSize = getSizeOfMemoryBlock(startingFreePosition)) < processSize) {
            /*Memory block too small check next one.*/
            if ((startingFreePosition = getFirstFreeCellLocation(startingFreePosition + freeMemBlockSize)) < 0) {
                return OUT_OF_MEMORY;
            }
            System.out.println("Free mem block size is " + freeMemBlockSize);
        }
        processStartingPosition = startingFreePosition;

        /*Allocate process at starting position.*/
        allocateProcess(processName, processSize, processStartingPosition);

        return processStartingPosition;
    }
}
