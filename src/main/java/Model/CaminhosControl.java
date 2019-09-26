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
public class CaminhosControl {
    
        // Holds our cities
    private static ArrayList destinationCities = new ArrayList<Vertice>();

    // Adds a destination city
    public static void addCliente(Vertice cliente) {
        destinationCities.add(cliente);
    }
    
    public static void clear() {
        destinationCities.clear();
    }
    
    // Get a city
    public static Vertice getCity(int index){
        return (Vertice)destinationCities.get(index);
    }
    
    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size();
    }
    
}
