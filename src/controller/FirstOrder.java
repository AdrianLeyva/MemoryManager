/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.FreeArea;
import model.Partition;
import model.Process;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class FirstOrder {
    private TAL tal;
    private Partition partition;

    public FirstOrder() {
    }

    public FirstOrder(TAL tal, Partition partition) {
        this.tal = tal;
        this.partition = partition;
    }

    public void execute(){
        orderFreeA();
        for(FreeArea area : this.tal.getFreeA()){
            if(area.getSize() >= this.partition.getProcess().getSize()){
                this.partition.setLocation(area.getLocation());
                this.partition.getProcess().setState(Process.ASSIGNED);
                
                area.setLocation(area.getLocation() + this.partition.getProcess().getSize());
                area.setSize(area.getSize() - this.partition.getProcess().getSize());
                break;
            }
        }
    }
    
    //Ordena las áreas libres de manera ascendente...
    private void orderFreeA(){
        int i,j=0;
        FreeArea buffer;
        ArrayList<FreeArea> listAreas = this.tal.getFreeA();
        
        for(i=0;i<listAreas.size();i++){
            for(j=0;j<i;j++){
                if(listAreas.get(i).getSize() < listAreas.get(j).getSize()){
                    buffer = listAreas.get(j);
                    listAreas.set(j, listAreas.get(i));
                    listAreas.set(i, buffer);
                }
            }
        }
        
        for(i=0;i<listAreas.size();i++){
            listAreas.get(i).setPosition(i);
        }
    }
}
