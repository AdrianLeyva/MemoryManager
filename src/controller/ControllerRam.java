/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.FreeArea;
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
    private Order order;

    public ControllerRam() {
        this.memory = new Ram(256,32); //Tamaño total / Tamaño SO
        this.tal = new TAL();
        this.tp = new TP();
        initialConfiguration();
    }

    public void commingProcess(Proceso process) {
        this.order.setTal(this.tal);
        this.order.setTp(this.tp);
        this.order.setProcess(process);
        this.order.commingProcess();
        this.tal = this.order.getTal();
        this.tp = this.order.getTp();
        if (this.order.getPrint()) {
            printTAL();
            printTP();
            System.out.println("");
        }
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
            if (this.order.getPrint()) {
                printTAL();
                printTP();
                System.out.println("");
            }
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

    private void initialConfiguration(){
        int availableSize = this.memory.getTotalSize()
                - this.memory.getSizeOS();
        FreeArea area = new FreeArea(1, availableSize
                , this.memory.getSizeOS(), FreeArea.AVAILABLE, 1);
        this.tal.addAL(area);
        printTAL();
        printTP();
        System.out.println("");
    }
}
