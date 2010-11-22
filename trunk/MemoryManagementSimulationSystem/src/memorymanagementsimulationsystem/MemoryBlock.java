/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package memorymanagementsimulationsystem;

/**
 *
 * @author Lew Gordon
 */
public class MemoryBlock {
    private int size;
    private int startingPosition;
    MemoryBlock(){
        size = 0;
        startingPosition = 0;
    }
    MemoryBlock(int startingPosition,int size){
        this.startingPosition = startingPosition;
        this.size = size;
    }
    protected int getSize(){
        return size;
    }
    protected void setSize(int size){
        this.size = size;
    }
    protected int getStartingPosition(){
        return startingPosition;
    }
    protected void setStartingPosition(){
        this.startingPosition = startingPosition;
    }
}
