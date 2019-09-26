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
public class Population {
    
     // Holds population of tours
    Caminhos[] tours;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new Caminhos[populationSize];
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Caminhos newTour = new Caminhos();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // Saves a tour
    public void saveTour(int index, Caminhos tour) {
        tours[index] = tour;
    }
    
    // Gets a tour from population
    public Caminhos getTour(int index) {
        return tours[index];
    }

    // Gets the best tour in the population
    public Caminhos getFittest() {
        Caminhos fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }
    
}
