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
public class Aresta {

    private int peso;
    private int u;

    public Aresta(int peso, int u) {
        this.peso = peso;
        this.u = u;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    @Override
    public String toString() {
        return "Aresta{" + "peso=" + peso + ", u=" + u + '}';
    }



}
