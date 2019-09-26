/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.alisonbarreiro.cvrp.FXMLController;
import static com.alisonbarreiro.cvrp.FXMLController.arestas;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Alison Rodolfo
 */
public class Caminhos {

    // Holds our tour of cities
    private ArrayList<Vertice> tour = new ArrayList<Vertice>();
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    // Cache
    private double fitness = 0;
    private int distance = 0;

    // Constructs a blank tour
    public Caminhos() {
        for (int i = 1; i < CaminhosControl.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Caminhos(ArrayList tour) {
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        this.toString();
        int caminhosAresta = 0;

        for (int cityIndex = 0; cityIndex < CaminhosControl.numberOfCities() - 1; cityIndex++) {
            setCity(cityIndex, CaminhosControl.getCity(cityIndex + 1));
        }

        /*
        
        tour.add(CaminhosControl.getCity(0));
        for (int cityIndex = 1; cityIndex < CaminhosControl.numberOfCities(); cityIndex++) {
            if (FXMLController.VEHICLE_CAPACITY > FXMLController.vertices.get(cityIndex).getDemanda()
                    && (FXMLController.vertices.get(cityIndex).getDemanda() != 0
                    && !(FXMLController.vertices.get(cityIndex).isIsVisitado()))) {
                tour.add(CaminhosControl.getCity(cityIndex));
                FXMLController.vertices.get(cityIndex).setIsVisitado(true);
                FXMLController.VEHICLE_CAPACITY -= FXMLController.vertices.get(cityIndex).getDemanda();
                caminhosAresta++;
            }

        }
        tour.add(CaminhosControl.getCity(0));
         */
        //System.out.println(tour.toString());
        // Randomly reorder the tour
        //Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public Vertice getCity(int tourPosition) {
        return (Vertice) tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, Vertice city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / (double) getDistance();
        }
        return fitness;
    }

    // Gets the total distance of the tour
    public int getDistance() {
        if (distance == 0) {
            int tourDistance = 0;
            int aux1, aux2;
           
            tourDistance += FXMLController.arestas.get(0).get(getCity(0).getId()).getPeso();
            for (int j1 = 0, j2 = 1; j1 < tourSize() - 1; j1++, j2++) {
                aux1 = getCity(j1).getId();
                aux2 = getCity(j2).getId();
                tourDistance += FXMLController.arestas.get(aux1).get(aux2).getPeso();

            }/*
            // Loop through our tour's cities
            for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
                // Get city we're travelling from
                Vertice fromCity = getCity(cityIndex);
                // City we're travelling to
                Vertice destinationCity;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if (cityIndex + 1 < tourSize()) {
                    destinationCity = getCity(cityIndex + 1);
                } else {
                    destinationCity = getCity(0);
                }
                // Get the distance between the two cities
                //System.out.println("D: " + tourSize());
                tourDistance += FXMLController.arestas.get(fromCity.getId()).get(destinationCity.getId()).getPeso();
                //tourDistance += fromCity.distanceTo(destinationCity);
            }
            */
            distance = tourDistance;
        }
        return distance;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsCity(Vertice cliente) {
        return tour.contains(cliente);
    }

    @Override
    public String toString() {
        
        String geneString = "0";
        for (int i = 0; i < tourSize(); i++) {
            geneString += "," + getCity(i);
        }
        //geneString += ";";
        return geneString;
    }

}
