/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Vehicle;
import Model.Vertice;
import java.util.ArrayList;

/**
 *
 * @author Alison Rodolfo
 */
public class Solucao {
    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private Vehicle vehicle;

    public Solucao( Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }



    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        if(this.vehicle.getCaminhoFeito()>vehicle.getCaminhoFeito()){
            this.vehicle = vehicle;
        }
        
    }
    

    
}
