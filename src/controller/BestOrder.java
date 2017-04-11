/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.FreeArea;
import model.Partition;
import model.Proceso;

/**
 *
 * @author Braulio Betancourt
 */
public class BestOrder extends Order {
    private TAL tal;
    private TP tp;
    private Proceso process;
    private boolean print;

    /**
     * Constructor principal.
     */
    public BestOrder() {
        this.tal = null;
        this.tp = null;
        this.process = null;
        this.print = true;
    }

    /**
     * Ordena la Tabla de Areas Libres por orden de tamaño menor -> mayor.
     */
    private void sortTAL() {
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            for (int j = 0; j < i; j++) {
                if (this.tal.getFreeA().get(j).getSize()
                        > this.tal.getFreeA().get(i).getSize()
                        && (this.tal.getFreeA().get(i).getState())
                                .equals(FreeArea.AVAILABLE)) {
                    FreeArea temp = this.tal.getFreeA().get(i);
                    this.tal.getFreeA().set(i, this.tal.getFreeA().get(j));
                    this.tal.getFreeA().set(j, temp);
                }
            }
        }
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            if ((this.tal.getFreeA().get(i).getState()).equals(
                    FreeArea.AVAILABLE)) {
                this.tal.getFreeA().get(i).setPosition(i + 1);
            }
        }
    }

    /**
     * Coloca una nueva particion en la Tabla de Particiones.
     */
    private void setPartition(Proceso process) {
        boolean band = false;
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            if (process.getSize()
                    <= this.tal.getFreeA().get(i).getSize()
                    && (this.tal.getFreeA().get(i).getState())
                                .equals(FreeArea.AVAILABLE)) {
                this.tp.addProcess(new Partition(
                        this.tal.getFreeA().get(i).getLocation()
                        , process));
                band = true;
                updateTAL();
                break;
            }
        }
        if (!band) {
            checkCompaction(process);
        }
    }

    private void checkCompaction(Proceso process) {
        if (this.process.getSize() <= totalSizeAL()) {
            Compactacion compaq = new Compactacion(this.tal, this.tp, process);
            boolean band = compaq.compactacion();
            if (band) {
                this.tal = compaq.getTAL();
                setPartition(process);
            } else {
                updateTables(compaq);
                setPartition(process);
            }
        } else {
            System.out.println("No hay espacio suficiente");
            this.print = false;
        }
    }

    private int totalSizeAL() {
        int totalSize = 0;
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            if ((this.tal.getFreeA().get(i).getState()).equals(FreeArea.AVAILABLE)) {
                totalSize += this.tal.getFreeA().get(i).getSize();
            }
        }
        return totalSize;
    }

    private void updateTables(Compactacion compaq) {
        Proceso process = foundProcess();
        if (process != null) {
            unassignProcess(process);
            compaq.compactacion();
            this.tal = compaq.getTAL();
            setPartition(process);
        }
    }

    private Proceso foundProcess() {
        Proceso process = null;
        int val;
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            val = this.tal.getFreeA().get(i).getLocation()
                    + this.tal.getFreeA().get(i).getSize();
            for (int j = 0; j < this.tp.getPartitions().size(); j++) {
                if (val == this.tp.getPartitions().get(j).getLocation()) {
                    process = this.tp.getPartitions().get(j).getProcess();
                    break;
                }
            }
        }
        return process;
    }

    /**
     * Actualiza la Tabla de Areas Libres despues de ingresar
     * una nueva particion a la Tabla de Particiones.
     */
    private void updateTAL() {
        for (int i = 0; i < this.tal.getFreeA().size(); i++) {
            for (int j = 0; j < this.tp.getPartitions().size(); j++) {
                if (this.tp.getPartitions().get(j).getLocation() == this.tal.getFreeA().get(i).getLocation()
                        && (this.tal.getFreeA().get(i).getState()).equals(FreeArea.AVAILABLE)
                        && (this.tp.getPartitions().get(j).getProcess().getState()).equals(Proceso.ASSIGNED)) {
                    this.tal.getFreeA().get(i).setLocation(this.tal.getFreeA().get(i).getLocation()
                                    + this.tp.getPartitions().get(j).getProcess().getSize());
                    this.tal.getFreeA().get(i).setSize(this.tal.getFreeA().get(i).getSize()
                                    - this.tp.getPartitions().get(j).getProcess().getSize());
                    if (this.tal.getFreeA().get(i).getSize() == 0) {
                        this.tal.getFreeA().get(i).setState(FreeArea.UNAVAILABLE);
                    }
                    sortTAL();
                    break;
                }
            }
        }
    }

    private void unassignProcess(Proceso process) {
        for (int i = 0; i < this.tp.getPartitions().size(); i++) {
            if ((process.getName()).equals(
                    this.tp.getPartitions().get(i).getProcess().getName())
                    && (this.tp.getPartitions().get(i).getProcess()
                            .getState()).equals(Proceso.ASSIGNED)) {
                this.tp.getPartitions().get(i).getProcess()
                        .setState(Proceso.UNASSIGNED);
                createNewAL(this.tp.getPartitions().get(i).getLocation(),
                        this.tp.getPartitions().get(i).getProcess().getSize());
                break;
            }
        }
    }

    private void createNewAL(int location, int size) {
        this.tal.addAL(new FreeArea(NumberAL(), size, location
                , FreeArea.AVAILABLE, this.tal.getFreeA().size()));
        sortTAL();
    }

    private int NumberAL() {
        int i = this.tal.getFreeA().size();
        return this.tal.getFreeA().get(i - 1).getNumber() + 1;
    }

    @Override
    public TAL getTal() {
        return tal;
    }

    @Override
    public TP getTp() {
        return tp;
    }

    @Override
    public boolean getPrint() {
        return this.print;
    }

    @Override
    public void setTal(TAL tal) {
        super.setTal(tal);
        this.tal = tal;
    }

    @Override
    public void setTp(TP tp) {
        super.setTp(tp);
        this.tp = tp;
    }

    @Override
    public void setProcess(Proceso process) {
        super.setProcess(process);
        this.process = process;
    }

    /**
     * Ejecuta los metodos necesarios para ordenar un proceso en el CPU
     * creando una particion nueva en la Tabla de Particiones y actualiza la
     * Tabla de Areas Libres.
     */
    @Override
    public void commingProcess() {
        super.commingProcess();
        this.print = true;
        sortTAL();
        setPartition(this.process);
    }

    /**
     * Ejecuta los metodos necesarios para liberar un espacio en la
     * Tabla de Particiones y crear una nueva Area Libre.
     */
    @Override
    public void endProcess() {
        super.endProcess();
        this.print = true;
        unassignProcess(this.process);
    }
}
