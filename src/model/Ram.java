/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class Ram {
    private int totalSize;
    private int sizeOS;
    private ArrayList<Partition> partitions;

    public Ram() {
    }

    public Ram(int totalSize, int sizeOS) {
        this.totalSize = totalSize;
        this.sizeOS = sizeOS;
    }
    
    public Ram(int totalSize, int sizeOS, ArrayList<Partition> partitions) {
        this.totalSize = totalSize;
        this.sizeOS = sizeOS;
        this.partitions = partitions;
    }
    
    
    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getSizeOS() {
        return sizeOS;
    }

    public void setSizeOS(int sizeOS) {
        this.sizeOS = sizeOS;
    }

    public ArrayList<Partition> getPartitions() {
        return partitions;
    }

    public void setPartitions(ArrayList<Partition> partitions) {
        this.partitions = partitions;
    }

   
    
    
}
