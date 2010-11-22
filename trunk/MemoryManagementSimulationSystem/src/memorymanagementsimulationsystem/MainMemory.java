/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

/**
 *
 * @author Lew Gordon
 */
public class MainMemory {

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
        int startingFreePosition = 0;
        int processStartingPosition;
        int freeMemBlockSize;
        /*Check for first free memory cell*/
        if ((startingFreePosition = getFirstFreeCellLocation()) < 0) {
            return -1;
        }
        /*Check size of free memory block.*/
        freeMemBlockSize = getSizeOfMemoryBlock(startingFreePosition);
        processStartingPosition = startingFreePosition;
        /*Allocate process at starting position.*/
        allocateProcess(processName, processSize, processStartingPosition);

        return processStartingPosition;
    }

    public void removeProcess(Integer startingPosition, Integer size) {
        for(int i=startingPosition;i<startingPosition+size;i++){
            memoryCells[i].freeCell();
        }
    }
    private void allocateProcess(String processName, int processSize, int processStartingPosition) {
        for (int i = processStartingPosition; i < processStartingPosition + processSize; i++) {
            memoryCells[i].setOwnedProcessType(processName);
        }
        freeMemory -= processSize;
    }

    private Integer getFirstFreeCellLocation() {
        for (MemoryCell m : memoryCells) {
            if (!m.isOccupied()) {
                return m.getPosition();
            }
        }
        return -1;
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

    public void addNewProcessFirst(String processName, Integer processSize) {
    }

    public void addNewProcessNext(String processName, Integer processSize) {
    }

    public void addNewProcessWorst(String processName, Integer processSize) {
    }

    public void addNewProcessBest(String processName, Integer processSize) {
    }

}
