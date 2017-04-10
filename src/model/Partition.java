/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class Partition {
    private int location;
    private Proceso process;

    public Partition(int location, Proceso process) {
        this.location = location;
        this.process = process;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Proceso getProcess() {
        return process;
    }

    public void setProcess(Proceso process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "Partition [location = " + getLocation()
                + getProcess().toString() + "]";
    }
}
