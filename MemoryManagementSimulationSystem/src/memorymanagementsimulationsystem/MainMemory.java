/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

import java.util.ArrayList;
import java.util.Collections;

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

    protected ArrayList<Process> defragment() {
        System.out.println("Defragmenting memory...");
        /*Find first memory block*/
        ArrayList<Process> updatedProcesses = new ArrayList<Process>();
        Process updatedProcess;
        MemoryBlock freeMemoryBlock;
        while ((freeMemoryBlock = findFirstFreeMemoryBlock(0)) != null) {
            if ((updatedProcess = moveClosestProcessToFreeBlock(freeMemoryBlock)) == null) {
                break;
            }
            updatedProcesses.add(updatedProcess);
        }
        if (updatedProcesses.size() > 0) {
            System.out.println("Relocated " + updatedProcesses.size() + " processes to "
                + "create free memory block of " + freeMemoryBlock.getSize() + " units. "
                + "(" + (double) freeMemoryBlock.getSize() / 240.0 + "% of total memory.)");
        }else{
            System.out.println("No processes could be moved.");
        }
        return updatedProcesses;
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
        AllocationAlgorithm algorithmType = AllocationAlgorithm.getInstance();
        String algorithm = algorithmType.getAlgorithm();
        if (algorithm.equals("first")) {
            return addNewProcessFirst(processName, processSize);
        } else if (algorithm.equals("best")) {
            return addNewProcessBest(processName, processSize);
        } else if (algorithm.equals("next")) {
            return addNewProcessNext(processName, processSize);
        } else if (algorithm.equals("worst")) {
            return addNewProcessWorst(processName, processSize);
        }
        return -1;
    }

    public void removeProcess(Integer startingPosition, Integer size) {
        for (int i = startingPosition; i < startingPosition + size; i++) {
            memoryCells[i].freeCell();
        }
        freeMemory += size;
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
        }
        processStartingPosition = startingFreePosition;

        /*Allocate process at starting position.*/
        allocateProcess(processName, processSize, processStartingPosition);

        return processStartingPosition;
    }

    public int addNewProcessNext(String processName, Integer processSize) {
        LastAllocatedBlockPosition blockPos = LastAllocatedBlockPosition.getInstance();
        int startingFreePosition = 0;
        int processStartingPosition = -1;
        int freeMemBlockSize;
        ArrayList<MemoryBlock> freeMemoryBlocks = new ArrayList<MemoryBlock>();

        /*Find all free memory blocks*/

        /*Check for first free memory cell*/
        if ((startingFreePosition = getFirstFreeCellLocation(0)) < 0) {
            return OUT_OF_MEMORY;
        }

        /*Check size of free memory block.*/
        while ((freeMemBlockSize = getSizeOfMemoryBlock(startingFreePosition)) > 0) {
            freeMemoryBlocks.add(new MemoryBlock(startingFreePosition, freeMemBlockSize));
            /*Check next free memory block.*/
            if ((startingFreePosition = getFirstFreeCellLocation(startingFreePosition + freeMemBlockSize)) < 0) {
                break;
            }
        }

        /*Find the closest free memory block to the last allocation.*/
        if ((processStartingPosition = findNextMemoryBlock(freeMemoryBlocks, processSize)) != -1) {
            blockPos.setPosition(processStartingPosition);
            allocateProcess(processName, processSize, processStartingPosition);
            return processStartingPosition;
        }

        return -1;
    }

    public int addNewProcessWorst(String processName, Integer processSize) {
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
        while ((freeMemBlockSize = getSizeOfMemoryBlock(startingFreePosition)) > 0) {
            freeMemoryBlocks.add(new MemoryBlock(startingFreePosition, freeMemBlockSize));
            /*Check next free memory block.*/
            if ((startingFreePosition = getFirstFreeCellLocation(startingFreePosition + freeMemBlockSize)) < 0) {
                break;
            }
        }
        Collections.sort(freeMemoryBlocks, new SortByLargestMemoryBlock());

        /*Find smallest memory block that process can fit into*/
        if((processStartingPosition = findLargestFreeMemoryBlock(freeMemoryBlocks, processSize))==OUT_OF_MEMORY){
            return OUT_OF_MEMORY;
        }
        /*Allocate process at starting position.*/
        System.out.println("process starting pos: "+processStartingPosition);
        System.out.println("process size: "+processSize);
        allocateProcess(processName, processSize, processStartingPosition);
        return processStartingPosition;
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
        while ((freeMemBlockSize = getSizeOfMemoryBlock(startingFreePosition)) > 0) {
            freeMemoryBlocks.add(new MemoryBlock(startingFreePosition, freeMemBlockSize));
            /*Check next free memory block.*/
            if ((startingFreePosition = getFirstFreeCellLocation(startingFreePosition + freeMemBlockSize)) < 0) {
                break;
            }
        }
        Collections.sort(freeMemoryBlocks, new SortBySmallestSize());

        /*Find smallest memory block that process can fit into*/
        if((processStartingPosition = findSmallestFreeMemoryBlock(freeMemoryBlocks, processSize))==OUT_OF_MEMORY){
            return OUT_OF_MEMORY;
        }
        /*Allocate process at starting position.*/
        allocateProcess(processName, processSize, processStartingPosition);

        return processStartingPosition;
    }
    /*
     * @return Returns starting position of smallest memory block that process can fit into.
     */

    private int findSmallestFreeMemoryBlock(ArrayList<MemoryBlock> freeMemoryBlocks, int processSize) {
        for (MemoryBlock m : freeMemoryBlocks) {
            /*If the memory block is large enough to hold the process.*/
            if (m.getSize() >= processSize) {
                return m.getStartingPosition();
            }
        }
        return -1;
    }

    private int findLargestFreeMemoryBlock(ArrayList<MemoryBlock> freeMemoryBlocks, Integer processSize) {
        MemoryBlock m = freeMemoryBlocks.get(0);
        if (m.getSize() >= processSize) {
            return m.getStartingPosition();
        }
        return OUT_OF_MEMORY;
    }

    private int findNextMemoryBlock(ArrayList<MemoryBlock> freeMemoryBlocks, Integer processSize) {
        Collections.sort(freeMemoryBlocks, new SortByPosition());
        for (MemoryBlock m : freeMemoryBlocks) {
            if (m.getSize() >= processSize) {
                return m.getStartingPosition();
            }
        }
        return -1;
    }

    /*Finds the first available memory block from the starting position*/
    private MemoryBlock findFirstFreeMemoryBlock(int startingPosition) {
        MemoryBlock freeMemoryBlock = new MemoryBlock();
        int blockStartingPosition = getFirstFreeCellLocation(0);
        int blockSize = getSizeOfMemoryBlock(blockStartingPosition);
        freeMemoryBlock.setStartingPosition(blockStartingPosition);
        freeMemoryBlock.setSize(blockSize);
        if (blockSize == 0) {
            return null;
        }
        return freeMemoryBlock;
    }

    private Process moveClosestProcessToFreeBlock(MemoryBlock freeMemoryBlock) {
        /*Find closest process*/
        int firstMemCell = freeMemoryBlock.getStartingPosition() + freeMemoryBlock.getSize() + 1;
        if (firstMemCell > 2400) {
            return null;
        }
        /*Move closest process to free memory space.*/

        Process updatedProcess = ProcessManager.lookup(memoryCells[firstMemCell].getOwnedProcessType());
        this.removeProcess(updatedProcess.getStartingPosition(), updatedProcess.getSize());
        this.allocateProcess(updatedProcess.getName(), updatedProcess.getSize(), freeMemoryBlock.getStartingPosition());
        updatedProcess.setStartingPosition(freeMemoryBlock.getStartingPosition());
        return updatedProcess;
    }
}
