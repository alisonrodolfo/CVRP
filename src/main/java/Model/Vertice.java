/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Alison Rodolfo
 */
public class Vertice {
    private int id;
    private int demanda;
    private boolean isVisitado;
    private double x;
    private double y;

    public Vertice(int id, int demanda, boolean isVisitado, double x, double y) {
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vertice{" + "id=" + id + ", demanda=" + demanda + ", isVisitado=" + isVisitado + ", x=" + x + ", y=" + y + '}';
    }

    
    
    
    
}
