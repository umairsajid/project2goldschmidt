/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

/**
 *
 * @author Lew Gordon
 */
public class MemoryCell {

    private Boolean occupied;
    private String ownedProcessType;
    private Integer position;

    public MemoryCell(Integer position){
        ownedProcessType = new String();
        occupied = false;
        this.position = position;
    }
    public boolean isOccupied() {
        return occupied;
    }

    public void setOwnedProcessType(String processType) {
        occupied = true;
        ownedProcessType = processType;
    }

    public String getOwnedProcessType() {
        return ownedProcessType;
    }

    public void freeCell() {
        occupied = false;
        ownedProcessType = "";
    }

    public Integer getPosition() {
        return position;
    }
}
