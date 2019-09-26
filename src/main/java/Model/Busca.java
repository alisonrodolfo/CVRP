/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Alison Rodolfo
 */
public class Busca {
    private int idCar;
    ArrayList<Vehicle> theAuxVehicles = new ArrayList<Vehicle>();

    public Busca(int idCar) {
        this.idCar = idCar;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public ArrayList<Vehicle> getTheAuxVehicles() {
        return theAuxVehicles;
    }

    public void setTheAuxVehicles(ArrayList<Vehicle> theAuxVehicles) {
        this.theAuxVehicles = theAuxVehicles;
    }
    
    
    
}
