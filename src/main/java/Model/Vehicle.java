/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Vector;

/**
 *
 * @author Alison Rodolfo
 */
public class Vehicle {
        
    private int id;
    private Vector<Vertice> rota = new Vector<Vertice>();
    private int capacidadeTotal;
    private int cargaAtual;
    private int verticeAtual;
    private boolean isEntragando;
    private int caminhoFeito;

    public Vehicle(int id, int capacidadeTotal, int cargaAtual, int verticeAtual, boolean isEntragando, int caminhoFeito) {
        this.id = id;
        this.capacidadeTotal = capacidadeTotal;
        this.cargaAtual = cargaAtual;
        this.verticeAtual = verticeAtual;
        this.isEntragando = isEntragando;
        this.caminhoFeito = caminhoFeito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public Vector<Vertice> getRota() {
        return rota;
    }

    public void setRota(Vector<Vertice> rota) {
        this.rota = rota;
    }

    public int getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(int capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public int getCargaAtual() {
        return cargaAtual;
    }

    public void setCargaAtual(int cargaAtual) {
        this.cargaAtual = cargaAtual;
    }

    public int getVerticeAtual() {
        return verticeAtual;
    }

    public void setVerticeAtual(int verticeAtual) {
        this.verticeAtual = verticeAtual;
    }

    public boolean isIsEntragando() {
        return isEntragando;
    }

    public void setIsEntragando(boolean isEntragando) {
        this.isEntragando = isEntragando;
    }

    public int getCaminhoFeito() {
        return caminhoFeito;
    }

    public void setCaminhoFeito(int caminhoFeito) {
        this.caminhoFeito = caminhoFeito;
    }

    public boolean verificaCargaAtual(int demandaCliente) {
        return (demandaCliente <= cargaAtual);
    }
   
    public void newVertice(Vertice vertice) {
        rota.add(vertice);
        this.cargaAtual -= vertice.getDemanda();
        this.verticeAtual = vertice.getId();
    }

   
}
