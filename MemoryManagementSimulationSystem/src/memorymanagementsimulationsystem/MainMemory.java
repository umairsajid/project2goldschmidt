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
            if(m.getPosition()%80==79){
                System.out.println();
            }
        }
        System.out.println();
    }

    public void addNewProcess(String processName, Integer processSize) {
        int startingFreePosition = 0;
        for (MemoryCell m : memoryCells) {
            if (!m.isOccupied()) {
                startingFreePosition = m.getPosition();
                break;
            }
        }
        for (int i = startingFreePosition; i < startingFreePosition + processSize; i++) {
            memoryCells[i].setOwnedProcessType(processName);
        }
        freeMemory -= processSize;
    }

    public int availableMemory() {
        return freeMemory;
    }
}
