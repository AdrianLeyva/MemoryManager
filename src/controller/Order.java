/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Proceso;

/**
 *
 * @author b1796
 */
public abstract class Order {
    public void commingProcess() { }
    public void endProcess() { }
    public abstract TAL getTal();
    public abstract TP getTp();
    public abstract boolean getPrint();
    public void setTal(TAL tal) { }
    public void setTp(TP tp) { }
    public void setProcess(Proceso process) { }
}
