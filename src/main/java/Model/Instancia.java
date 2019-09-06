package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alison Rodolfo
 */
public class Instancia {
    private String name;
    private int dimension;
    private Vertice vertices[];

    public Instancia(String name, int dimension, Vertice[] vertices) {
        this.name = name;
        this.dimension = dimension;
        this.vertices = vertices;
    }    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Vertice[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertice[] vertices) {
        this.vertices = vertices;
    }
    
    
    
    
}
