/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package memorymanagementsimulationsystem;

/**
 * Represents the last allocated block starting position.
 * @author Lew Gordon
 */
public class LastAllocatedBlockPosition {

    private int position;
    private LastAllocatedBlockPosition() {
        /*Assuming the OS always takes up cells 0-79*/
        position = 0;
    }

    public static LastAllocatedBlockPosition getInstance() {
        return LastAllocatedBlockPositionHolder.INSTANCE;
    }

    private static class LastAllocatedBlockPositionHolder {
        private static final LastAllocatedBlockPosition INSTANCE = new LastAllocatedBlockPosition();
    }
    public void setPosition(int position){
        this.position = position;
    }
    public int getPosition(){
        return position;
    }
 }
