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

    public Process(String name,Integer size){
        this.name = name;
        this.size = size;
    }
    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }
    @Override
    public String toString(){
        return "Process ID: "+name+" Size: "+size;
    }
}
