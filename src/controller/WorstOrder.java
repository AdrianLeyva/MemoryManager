/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.FreeArea;
import model.Partition;
import model.Proceso;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class WorstOrder extends Order {

    private TAL tal;
    private TP tp;
    private Proceso process;
    private boolean print;

    public WorstOrder() {
        this.tal = null;
        this.tp = null;
        this.process = null;
        this.print = true;
    }
    /**
     * Ordena en orden ascendente
     */
    private void sortTAL(){
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            for (int j = 0; j < i; j++) {
                if (this.tal.getFreeA().get(j).getSize()
                        < this.tal.getFreeA().get(i).getSize()
                        && (this.tal.getFreeA().get(i).getState())
                                .equals(FreeArea.AVAILABLE)) {
                    FreeArea temp = this.tal.getFreeA().get(i);
                    this.tal.getFreeA().set(i, this.tal.getFreeA().get(j));
                    this.tal.getFreeA().set(j, temp);
                }
            }
        }
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            if ((this.tal.getFreeA().get(i).getState()).equals(
                    FreeArea.AVAILABLE)) {
                this.tal.getFreeA().get(i).setPosition(i + 1);
            }
        }
        
    }
    /**
     * Nueva Particion
     */
    private void setProcess(){
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            if (this.process.getSize()
                    <= this.tal.getFreeA().get(i).getSize()
                    && (this.tal.getFreeA().get(i).getState())
                                .equals(FreeArea.AVAILABLE)) {
                this.tp.addProcess(new Partition(
                        this.tal.getFreeA().get(i).getLocation()
                        , this.process));
                updateTAL();
                break;
            }
        }
    }
    /**
     * Actualizacion de Tabla de Areas Libres despues que se genera una nueva particion a la 
     * Tabla de Particiones.
     */
    private void updateTAL(){
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            for (int j = 0; j < this.tp.getPartitions().size(); j++) {
                if (this.tp.getPartitions().get(j).getLocation() == this.tal.getFreeA().get(i).getLocation()
                        && (this.tal.getFreeA().get(i).getState()).equals(FreeArea.AVAILABLE)
                        && (this.tp.getPartitions().get(j).getProcess().getState()).equals(Proceso.ASSIGNED)) {
                    this.tal.getFreeA().get(i).setLocation(this.tal.getFreeA().get(i).getLocation()
                                    + this.tp.getPartitions().get(j).getProcess().getSize());
                    this.tal.getFreeA().get(i).setSize(this.tal.getFreeA().get(i).getSize()
                                    - this.tp.getPartitions().get(j).getProcess().getSize());
                    if (this.tal.getFreeA().get(i).getSize() == 0) {
                        this.tal.getFreeA().get(i).setState(FreeArea.UNAVAILABLE);
                    }
                    sortTAL();
                    break;
                }
            }
        }
        
    }
    private void unassignProcess() {
        for (int i = 0; i < this.tp.getPartitions().size(); i++) {
            if ((this.process.getName()).equals(
                    this.tp.getPartitions().get(i).getProcess().getName())
                    && (this.tp.getPartitions().get(i).getProcess()
                            .getState()).equals(Proceso.ASSIGNED)) {
                this.tp.getPartitions().get(i).getProcess()
                        .setState(Proceso.UNASSIGNED);
                createNewAL(this.tp.getPartitions().get(i).getLocation(),
                        this.tp.getPartitions().get(i).getProcess().getSize());
                break;
            }
        }
    }
    private void createNewAL(int location, int size) {
        this.tal.addAL(new FreeArea(NumberAL(), size, location
                , FreeArea.AVAILABLE, this.tal.getFreeA().size()));
        sortTAL();
    }

    private int NumberAL() {
        int i = this.tal.getFreeA().size();
        return this.tal.getFreeA().get(i - 1).getNumber() + 1;
    }

    @Override
    public TAL getTal() {
        return this.tal;
    }

    @Override
    public TP getTp() {
        return this.tp;
    }

    @Override
    public boolean getPrint() {
        return this.print;
    }

    @Override
    public void setProcess(Proceso process) {
        super.setProcess(process);
        this.process = process;
    }

    @Override
    public void setTp(TP tp) {
        super.setTp(tp);
        this.tp = tp;
    }

    @Override
    public void setTal(TAL tal) {
        super.setTal(tal);
        this.tal = tal;
    }

    @Override
    public void commingProcess() {
        super.commingProcess();
        this.print = true;
        sortTAL();
        setProcess();
    }

    @Override
    public void endProcess() {
        super.endProcess();
        this.print = true;
        unassignProcess();
    }
}
