/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.FreeArea;
import model.Proceso;

/**
 *
 * @author Armando Carvajal
 */
public class Compactacion {

    private TAL tal;
    private TP tp;
    private Proceso process;

    public Compactacion(TAL tal, TP tp, Proceso process){
        this.tal = tal;
        this.tp = tp;
        this.process = process;
    }

    /**
     * Metodo para reorganizar la memoria con fragmentación.
     */
    public boolean compactacion() {
        boolean band  = false;
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            for (int j = 0; j < i; j++) {
                int val = this.tal.getFreeA().get(i).getSize()
                        + this.tal.getFreeA().get(i).getLocation();

                if (val == this.tal.getFreeA().get(j).getLocation()) {

                    this.tal.getFreeA().get(i).setSize(this.tal.getFreeA().
                            get(i).getSize()
                            + this.tal.getFreeA().get(j).getSize());

                    /*this.tal.getFreeA().get(i).setLocation(this.tal.getFreeA().
                            get(i).getLocation());*/

                    this.tal.getFreeA().get(i).setState(FreeArea.AVAILABLE);
                    this.tal.getFreeA().get(j).setState(FreeArea.UNAVAILABLE);
                    band = true;
                }
            }
        }
        return band;
    }
    private int totalSize() {
        int totalSize = 0;
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            if ((this.tal.getFreeA().get(i).getState()).equals(FreeArea.AVAILABLE)) {
                totalSize += this.tal.getFreeA().get(i).getSize();
            }
        }
        return totalSize;
    }
    public TAL getTAL() {
        return this.tal;
    }
    public TP getTp() {
        return tp;
    }
    public Proceso getProcess() {
        return this.process;
    }
}
