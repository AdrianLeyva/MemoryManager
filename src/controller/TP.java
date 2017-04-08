/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Partition;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class TP {
    private ArrayList<Partition> partitions;

    
    
    public TP() {
        this.partitions = new ArrayList<>();
    }

    public TP(ArrayList<Partition> partitions) {
        this.partitions = partitions;
    }

    
    
    public ArrayList<Partition> getPartitions() {
        return partitions;
    }

    public void setPartitions(ArrayList<Partition> partitions) {
        this.partitions = partitions;
    }
    
    public void addProcess(Partition partition){
        this.partitions.add(partition);
    }
}
