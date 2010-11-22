/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package memorymanagementsimulationsystem;

/**
 *
 * @author Lew Gordon
 */
public class AllocationAlgorithm {

    private String type;
    private AllocationAlgorithm() {
    }

    public static AllocationAlgorithm getInstance() {
        return AllocationAlgorithmHolder.INSTANCE;
    }

    public String getAlgorithm(){
        return type;
    }
    public void setAlgorithm(String type){
        this.type = type;
    }
    private static class AllocationAlgorithmHolder {
        private static final AllocationAlgorithm INSTANCE = new AllocationAlgorithm();
    }
 }
