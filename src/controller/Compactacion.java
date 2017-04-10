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
 * @author Armando Carvajal
 */
public class Compactacion {

    private TAL tal;

    public Compactacion(){
        this.tal = null;
    }
    
    /**
     * Metodo para reorganizar la memoria con fragmentación.
     */
    public void compactacion() {
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            for (int j = 0; j < i; j++) {
                int val = this.tal.getFreeA().get(i).getSize()
                        + this.tal.getFreeA().get(i).getPosition();

                if (val == this.tal.getFreeA().get(j).getPosition()) {

                    this.tal.getFreeA().get(i).setSize(this.tal.getFreeA().
                            get(i).getSize() 
                            + this.tal.getFreeA().get(j).getSize());

                    this.tal.getFreeA().get(i).setPosition(this.tal.getFreeA().
                            get(i).getPosition());   
                    
                    this.tal.getFreeA().get(j).setState("UNAVAILABLE");
                }


            }
        }
    }
}
