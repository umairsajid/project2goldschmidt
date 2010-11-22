/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorymanagementsimulationsystem;

/**
 *
 * @author Lew Gordon
 */
public class Process {

    private String name;
    private Integer size;
    private Integer startingPosition;
    public Process(String name,Integer size, Integer startingPosition){
        this.name = name;
        this.size = size;
        this.startingPosition = startingPosition;

    }
    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }
    public Integer getStartingPosition(){
        return startingPosition;
    }
    @Override
    public String toString(){
        return "Process ID: "+name+" Size: "+size;
    }
}
