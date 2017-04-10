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
public class FreeArea {
    public static String AVAILABLE = "AVAILABLE";
    public static String UNAVAILABLE = "UNAVAILABLE";

    private int number;
    private int size;
    private int location;
    private String state;
    private int position; //Es el orden...

    public FreeArea() {
    }


    public FreeArea(int number, int size, int location, String state, int position) {
        this.number = number;
        this.size = size;
        this.location = location;
        this.state = state;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "FreeArea [number = " + getNumber() + " | size = " + getSize() + " | location = " + getLocation() + " | state = " + getState() + " | position = " + getPosition() + "]";
    }
}
