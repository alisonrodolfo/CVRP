package com.alisonbarreiro.cvrp;

import Model.Aresta;
import Model.Vehicle;
import Model.Vertice;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    private static final ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private static final ArrayList<ArrayList<Aresta>> arestas = new ArrayList<ArrayList<Aresta>>();

    private static final String NAME = "NAME:";
    private static final String DIMENSION = "DIMENSION:";
    private static final String VEHICLES = "VEHICLES:";
    private static final String CAPACITY = "CAPACITY:";

    /* A capacidade que cada veículo possui */
    public static int VEHICLE_CAPACITY;

    /* O número de vértices, o depósito e os clientes */
    public static int NUM_VERTICE;
    /* O número de veículos */
    public static int NUM_VEHICLES;

    private static final ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    @FXML
    private Label label;

    @FXML
    private ChoiceBox choiceBox;
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private TextField textname; 
    @FXML
    private TextField textdimension; 
    @FXML
    private TextField textvehicles; 
    @FXML
    private TextField textcapacity; 


    String optEscolhida[]
            = {"P-n19-k2.txt", "P-n20-k2.txt", "P-n23-k8.txt",
                "P-n45-k5.txt", "P-n50-k10.txt",
                "P-n51-k10.txt", "P-n55-k7.txt"};


    // Create action event 
    EventHandler<ActionEvent> value = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            vertices.clear();
            arestas.clear();
            vehicles.clear();
            VEHICLE_CAPACITY = 0;
            NUM_VERTICE = 0;
            NUM_VEHICLES = 0;
            
            textArea.setText("");
            
            addAll("src/main/resources/instancias_teste/" + choiceBox.getValue());
            // read the data

            vizinhoMaisProximo();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        choiceBox.setItems(FXCollections.observableArrayList(optEscolhida));

        choiceBox.setOnAction(value);

    }

    public void vizinhoMaisProximo() {
        int menorDistancia = 0;
        int verticeAtual = 0;
        int custoTotal = 0;
        int custoTotalAresta = 0;
        int clienteAtual = 0;
        int count = 0;

        for (int i = 0; i < NUM_VEHICLES; i++) {

            custoTotalAresta = 0;

            vertices.get(0).setIsVisitado(false);

            vehicles.add(new Vehicle(VEHICLE_CAPACITY, VEHICLE_CAPACITY, verticeAtual, false, 0));

            //adiciona o deposito no inicio da rota
            vehicles.get(i).newVertice(vertices.get(0));
            vertices.get(0).setIsVisitado(true);

            for (int j = 0; j < NUM_VERTICE;) {

                menorDistancia = Integer.MAX_VALUE;

                for (int n = 1; n < NUM_VERTICE; n++) {

                    //Se o elemento nao foi visitado
                    if (vertices.get(n).isIsVisitado() == false) {
                        if (vehicles.get(i).verificaCargaAtual(vertices.get(n).getDemanda())) {
                            if (arestas.get(j).get(n).getPeso() != 0 && arestas.get(j).get(n).getPeso() < menorDistancia) {
                                menorDistancia = arestas.get(j).get(n).getPeso();
                                //atualiza o cliente
                                verticeAtual = n;
                            }

                        }

                    }

                }

                if (verticeAtual == j) {
                    break;
                }
                if (vertices.get(verticeAtual).isIsVisitado() == false) {

                    vertices.get(verticeAtual).setIsVisitado(true);
                    vehicles.get(i).newVertice(vertices.get(verticeAtual));
                    vehicles.get(i).setCaminhoFeito(custoTotalAresta);

                    custoTotalAresta += arestas.get(j).get(verticeAtual).getPeso();
                    vehicles.get(i).setCaminhoFeito(custoTotalAresta);
                    //System.out.println("Custo total1: " + custoTotal);
                    j = verticeAtual;

                }

            }
            vehicles.get(i).newVertice(vertices.get(0));
            custoTotalAresta += arestas.get(verticeAtual).get(0).getPeso();
            vehicles.get(i).setCaminhoFeito(custoTotalAresta);

        }
        this.imprimiRota(vehicles);

        this.swapVizinhanca();
    }

    public void addAll(String file) {

        System.out.println("LENDO ARQUIVO");
        try {
            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String dados;
            while (linha != null) {
                dados = linha.trim().replaceAll("\t", "").replaceAll("\\s+", " ");

                if (dados.startsWith(NAME)) {
                    System.out.println("NOME: " + dados.substring(NAME.length()).replaceAll("\\s+", ""));
                    textname.setText(dados.substring(NAME.length()).replaceAll("\\s+", ""));
                } else if (dados.startsWith(DIMENSION)) {
                    System.out.println("DIMENSION: " + dados.substring(DIMENSION.length()).replaceAll("\\s+", ""));
                    NUM_VERTICE = Integer.parseInt(dados.substring(DIMENSION.length()).replaceAll("\\s+", ""));
                    textdimension.setText(dados.substring(DIMENSION.length()).replaceAll("\\s+", "")); 
                } else if (dados.startsWith(VEHICLES)) {
                    System.out.println("VEHICLES: " + dados.substring(VEHICLES.length()).replaceAll("\\s+", ""));
                    NUM_VEHICLES = Integer.parseInt(dados.substring(VEHICLES.length()).replaceAll("\\s+", ""));
                    textvehicles.setText(dados.substring(VEHICLES.length()).replaceAll("\\s+", "")); 
                } else if (dados.startsWith(CAPACITY)) {
                    System.out.println("CAPACITY: " + dados.substring(CAPACITY.length()).replaceAll("\\s+", ""));
                    VEHICLE_CAPACITY = Integer.parseInt(dados.substring(CAPACITY.length()).replaceAll("\\s+", ""));
                    textcapacity.setText(dados.substring(CAPACITY.length()).replaceAll("\\s+", "")); 
                } else if (dados.startsWith("DEMAND_SECTION:")) {
                    linha = lerArq.readLine();
                    break;
                }
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }

            for (int i = 0; i < NUM_VERTICE; i++) {

                dados = linha.trim().replaceAll("\t", "").replaceAll("\\s+", " ");
                String tokens[] = dados.split(" ");

                vertices.add(new Vertice(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), false));

                linha = lerArq.readLine();
            }
            linha = lerArq.readLine();
            dados = linha.trim().replaceAll("\t", "").replaceAll("\\s+", " ");
            if (dados.startsWith("EDGE_WEIGHT_SECTION")) {
                linha = lerArq.readLine();
            }

            for (int i = 0; i < NUM_VERTICE; i++) {

                ArrayList<Aresta> arestasFor = new ArrayList<Aresta>();

                dados = linha.trim().replaceAll("\t", "").replaceAll("\\s+", " ");
                String tokens[] = dados.split(" ");

                for (int j = 0; j < NUM_VERTICE; j++) {
                    arestasFor.add(new Aresta(Integer.parseInt(tokens[j]), j));

                }
                arestas.add(arestasFor);
                linha = lerArq.readLine();

            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }

    public void imprimiRota(ArrayList<Vehicle> auxVehicles) {

        int clientesAtendidos = 0;
        int custoTotalAresta;
        int custoTotal = 0;
        String print = "";
        for (int i = 0; i < NUM_VEHICLES; i++) {
            custoTotalAresta = 0;
            int clientes = 0;
            print += "[DEPOSITO -> ";
            for (int j = 0; j < auxVehicles.get(i).getRota().size(); j++) {
                print += auxVehicles.get(i).getRota().get(j).getId() + " -> ";
            }
            print += " DEPOSITO]\n";
            
            

            int aux1, aux2;
            for (int j1 = 0, j2 = 1; j1 < vehicles.get(i).getRota().size() - 1; j1++, j2++) {
                aux1 = auxVehicles.get(i).getRota().get(j1).getId();
                aux2 = auxVehicles.get(i).getRota().get(j2).getId();
                custoTotalAresta += arestas.get(aux1).get(aux2).getPeso();

            }

            custoTotal += custoTotalAresta;
            print += "Custo Total Aresta: " + custoTotalAresta+"\n";
            clientes = vehicles.get(i).getRota().size() - 2;
            print += "Clientes atendidos: " + clientes+"\n\n";
            clientesAtendidos += clientes;
        }
        print += "Custo total: " + custoTotal+"\n\n";
        print(print);
        textArea.setText(textArea.getText()+"\n----------------------------------\n"+print);
        
        

    }
    
    public void print(String print){
        System.out.print(print);
    }

    public void swapVizinhanca() {

        ArrayList<Vehicle> auxVehicles = new ArrayList<Vehicle>();
        Vehicle theAuxVehicles = null;

        ArrayList<Vehicle> definitiveVehicles = new ArrayList<Vehicle>();

        for (int i = 0; i < NUM_VEHICLES; i++) {
            theAuxVehicles = vehicles.get(i);
            int aux1, aux2, custoTotalAresta1 = 0, custoTotalAresta2 = 0;
            for (int j1 = 0, j2 = 1; j1 < vehicles.get(i).getRota().size() - 1; j1++, j2++) {
                aux1 = vehicles.get(i).getRota().get(j1).getId();
                aux2 = vehicles.get(i).getRota().get(j2).getId();
                custoTotalAresta1 += arestas.get(aux1).get(aux2).getPeso();
            }

            auxVehicles.clear();
            for (int n = 1; n < (vehicles.get(i).getRota().size() - 2); n++) {
                //System.out.println(" V: "+((vehicles.get(i).getRota().size() - 2)/2));

                auxVehicles.add(new Vehicle(vehicles.get(i).getCapacidadeTotal(), vehicles.get(i).getCargaAtual(),
                        vehicles.get(i).getVerticeAtual(), vehicles.get(i).isIsEntragando(), vehicles.get(i).getCaminhoFeito()));

                auxVehicles.get(n - 1).setRota((Vector<Vertice>) vehicles.get(i).getRota().clone());

                Vertice auxVertice;

                auxVertice = auxVehicles.get(n - 1).getRota().get(n);

                auxVehicles.get(n - 1).getRota().set(n, auxVehicles.get(n - 1).getRota().get(n + 1));
                auxVehicles.get(n - 1).getRota().set(n + 1, auxVertice);

                custoTotalAresta2 = 0;
                for (int j1 = 0, j2 = 1; j1 < vehicles.get(i).getRota().size() - 1; j1++, j2++) {
                    aux1 = auxVehicles.get(n - 1).getRota().get(j1).getId();
                    aux2 = auxVehicles.get(n - 1).getRota().get(j2).getId();
                    custoTotalAresta2 += arestas.get(aux1).get(aux2).getPeso();

                }

                if (custoTotalAresta2 < custoTotalAresta1) {
                    custoTotalAresta1 = custoTotalAresta2;
                    theAuxVehicles = auxVehicles.get(n - 1);
                }

            }

            Vehicle theAuxVehicles2 = new Vehicle(theAuxVehicles.getCapacidadeTotal(), theAuxVehicles.getCargaAtual(),
                    theAuxVehicles.getVerticeAtual(), theAuxVehicles.isIsEntragando(), theAuxVehicles.getCaminhoFeito());
            theAuxVehicles2.setRota((Vector<Vertice>) theAuxVehicles.getRota().clone());

            Vertice vertAux = theAuxVehicles2.getRota().get(1);
            theAuxVehicles2.getRota().set(1, theAuxVehicles2.getRota().get((theAuxVehicles2.getRota().size() - 2)));
            theAuxVehicles2.getRota().set((theAuxVehicles2.getRota().size() - 2), vertAux);

            /*
            int menorDistancia = Integer.MAX_VALUE, verticeAtual = 1;
            for (int x = 1; x < (theAuxVehicles2.getRota().size() - 1); x++) {

                if (arestas.get(x).get(0).getPeso() != 0 && arestas.get(x).get(0).getPeso() < menorDistancia) {
                    //System.out.println("X: " + x + " A: " + arestas.get(x).get(0).getU() + " P: " + arestas.get(x).get(0).getPeso());
                    menorDistancia = arestas.get(x).get(0).getPeso();
                    //atualiza o cliente
                    verticeAtual = x;
                }
            }
            

            for (int x = 1; x < (theAuxVehicles2.getRota().size() - 1); x++) {
                if (theAuxVehicles2.getRota().get(x).getId() == verticeAtual) {
                    Vertice vertAux = theAuxVehicles2.getRota().get(x);
                    theAuxVehicles2.getRota().set(x, theAuxVehicles2.getRota().get((theAuxVehicles2.getRota().size() - 2)));
                    theAuxVehicles2.getRota().set((theAuxVehicles2.getRota().size() - 2), vertAux);
                }
            }
             */
            int custoTotalAresta3 = 0;
            for (int j1 = 0, j2 = 1; j1 < theAuxVehicles2.getRota().size() - 1; j1++, j2++) {
                aux1 = theAuxVehicles2.getRota().get(j1).getId();
                aux2 = theAuxVehicles2.getRota().get(j2).getId();
                custoTotalAresta3 += arestas.get(aux1).get(aux2).getPeso();

            }

            if (custoTotalAresta3 < custoTotalAresta1) {

                // System.out.println("D: " + custoTotalAresta3 + " DU: " + custoTotalAresta1);
                custoTotalAresta1 = custoTotalAresta3;
                theAuxVehicles = theAuxVehicles2;
            }

            definitiveVehicles.add(theAuxVehicles);

        }

        this.imprimiRota(definitiveVehicles);
    }

}
