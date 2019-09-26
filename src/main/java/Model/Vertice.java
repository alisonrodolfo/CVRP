/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.alisonbarreiro.cvrp.FXMLController;

/**
 *
 * @author Alison Rodolfo
 */
public class Vertice {
    private int id;
    private int demanda;
    private boolean isVisitado;
    private int x;
    private int y;

    public Vertice(int id, int demanda, boolean isVisitado, int x, int y) {
        this.id = id;
        this.demanda = demanda;
        this.isVisitado = isVisitado;
        this.x = x;
        this.y = y;
    }
    
    public Vertice(int id, int demanda, boolean isVisitado) {
        this.id = id;
        this.demanda = demanda;
        this.isVisitado = isVisitado;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDemanda() {
        return demanda;
    }

    public void setDemanda(int demanda) {
        this.demanda = demanda;
    }

    public boolean isIsVisitado() {
        return isVisitado;
    }

    public void setIsVisitado(boolean isVisitado) {
        this.isVisitado = isVisitado;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "" + id;
    }
    
    public double distanceTo(Vertice cliente) {
        
        return FXMLController.arestas.get(this.id).get(cliente.id).getPeso();
    }

    
    
    
    
}