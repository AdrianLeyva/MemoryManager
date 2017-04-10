/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.ControllerRam;
import java.util.ArrayList;
import model.FreeArea;
import model.Proceso;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class Main {
    public static void main(String[] args) {
        ControllerRam controller = new ControllerRam();
        controller.setWorstOrder();
        //controller.setFirstOrder();
        controller.commingProcess(new Proceso("P1", 48, Proceso.ASSIGNED));
        controller.commingProcess(new Proceso("P2", 24, Proceso.ASSIGNED));
        controller.commingProcess(new Proceso("P3", 64, Proceso.ASSIGNED));
        controller.endProcess("P2");
        controller.commingProcess(new Proceso("P4", 10, Proceso.ASSIGNED));
        controller.commingProcess(new Proceso("P5", 18, Proceso.ASSIGNED));
        controller.endProcess("P1");
        controller.endProcess("P4");
        
    }
}
