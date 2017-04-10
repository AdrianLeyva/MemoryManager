/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.FreeArea;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class TAL {
    private ArrayList<FreeArea> freeA;


    public TAL() {
        this.freeA = new ArrayList<>();
    }

    public TAL(ArrayList<FreeArea> freeA) {
        this.freeA = freeA;
    }




    public ArrayList<FreeArea> getFreeA() {
        return freeA;
    }

    public void setFreeA(ArrayList<FreeArea> freeA) {
        this.freeA = freeA;
    }

    public void addAL(FreeArea area){
        this.freeA.add(area);
    }
}
