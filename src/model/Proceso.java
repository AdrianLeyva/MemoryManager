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
public class Proceso {
    public static String ASSIGNED = "ASSIGNED";
    public static String UNASSIGNED = "UNASSIGNED";

    private String name;
    private int size;
    private String state;

    
    public Proceso() {
    }

    public Proceso(String name, int size, String state) {
        this.name = name;
        this.size = size;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return " | name = " + getName() + " | size = " + getSize() + " | state = " + getState();
    }
}
