/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/*import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;*/
import model.FreeArea;
//import model.Partition;
import model.Proceso;
import model.Ram;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class ControllerRam {

    private Ram memory;
    private TAL tal;
    private TP tp;
    //private ArrayList<Partition> processes;
    //private Queue processQueue;
    private Order order;

    public ControllerRam() {
        this.memory = new Ram(1024,100); //Tamaño total / Tamaño SO
        this.tal = new TAL();
        this.tp = new TP();
        //this.processes = listProcesses;
        //this.processQueue = new LinkedList();
        initialConfiguration();
    }

    public void commingProcess(Proceso process) {
        this.order.setTal(this.tal);
        this.order.setTp(this.tp);
        this.order.setProcess(process);
        this.order.commingProcess();
        this.tal = this.order.getTal();
        this.tp = this.order.getTp();
        printTAL();
        printTP();
        System.out.println("");
    }

    public void endProcess(String name) {
        Proceso process = processFound(name);
        if (process != null) {
            this.order.setTal(this.tal);
            this.order.setTp(this.tp);
            this.order.setProcess(process);
            this.order.endProcess();
            this.tal = this.order.getTal();
            this.tp = this.order.getTp();
            printTAL();
            printTP();
            System.out.println("");
        } else {
            System.out.println(name + " no existe ese proceso en la Tabla de Procesos");
        }
    }

    public void setFirstOrder() {
        this.order = new FirstOrder();
    }
    public void setBestOrder() {
        this.order = new BestOrder();
    }
    public void setWorstOrder() {
        this.order = new WorstOrder();
    }

    private Proceso processFound(String name) {
        Proceso processFound = null;
        for (int i = 0; i < this.tp.getPartitions().size(); i++) {
            if ((this.tp.getPartitions().get(i).getProcess().getName())
                    .equals(name) && (this.tp.getPartitions()
                            .get(i).getProcess().getState())
                            .equals(Proceso.ASSIGNED)) {
                processFound = this.tp.getPartitions().get(i).getProcess();
            }
        }
        return processFound;
    }

    private void printTAL() {
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            System.out.println(this.tal.getFreeA().get(i).toString());
        }
    }

    private void printTP() {
        for (int i = 0; i < this.tp.getPartitions().size(); i++) {
            System.out.println(this.tp.getPartitions().get(i).toString());
        }
    }

    /*public void execute(){
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
        
    }*/

    private void initialConfiguration(){
        //convertListToQueue();
        int availableSize = this.memory.getTotalSize()
                - this.memory.getSizeOS();
        FreeArea area = new FreeArea(1, availableSize
                , this.memory.getSizeOS(), FreeArea.AVAILABLE, 1);
        this.tal.addAL(area);
        printTAL();
        printTP();
        System.out.println("");
    }

    /*private void convertListToQueue(){
        for(Partition partition : processes){
            processQueue.add(partition);
        }
    }*/
}
