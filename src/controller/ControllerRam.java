/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import model.FreeArea;
import model.Partition;
import model.Ram;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class ControllerRam {
    
    private Ram memory;
    private TAL tal;
    private TP tp;
    private ArrayList<Partition> processes;
    private Queue processQueue;
    
    public ControllerRam(ArrayList<Partition> listProcesses) {
        this.memory = new Ram(1024,100); //Tamaño total / Tamaño SO
        this.tal = new TAL();
        this.tp = new TP();
        this.processes = listProcesses;
        this.processQueue = new LinkedList();
    }
    
    public void execute(){
        initialConfiguration();
        Partition process = (Partition)getComingProcesses();
        
        //Si esta llegando el proceso
        if(process.getProcess().isComing()){
            addTAL(process);
            addTP(process);
            addRAM(process);
        }else{//Si está terminando el proceso
            releaseMemory();
        }
    }
    
    private Object getComingProcesses(){
        return processQueue.poll();
    }
    
    //Agrega la partición a TAL
    private void addTAL(Partition partition){
        
    }
    
    //Agrega la partición a TP
    private void addTP(Partition partition){
        
    }
    //Agrega la partición a la RAM
    private void addRAM(Partition partition){
        
    }
    
    private void releaseMemory(){
        
    }
    
    private void initialConfiguration(){
        convertListToQueue();
        
        int availableSize = this.memory.getTotalSize() - this.memory.getSizeOS();
        FreeArea area = new FreeArea(1, availableSize, this.memory.getSizeOS(), FreeArea.AVAILABLE, 1);
        
        this.tal.addAL(area);
    }
    
    private void convertListToQueue(){
        for(Partition partition : processes){
            processQueue.add(partition);
        }
    }
    
    
}
