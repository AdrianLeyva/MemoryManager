/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Proceso;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class WorstOrder extends Order {

    private TAL tal;
    private TP tp;
    private Proceso process;

    public WorstOrder() {
        this.tal = null;
        this.tp = null;
        this.process = null;
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
    }

    @Override
    public void endProcess() {
        super.endProcess();
    }
}
