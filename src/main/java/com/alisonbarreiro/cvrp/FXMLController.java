package com.alisonbarreiro.cvrp;

import Model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {

    public static final ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    public static final ArrayList<ArrayList<Aresta>> arestas = new ArrayList<ArrayList<Aresta>>();

    public int NUM_CLIENTES = 0;
    public int MELHOR = 0;

    private static final String NAME = "NAME:";
    private static final String DIMENSION = "DIMENSION:";
    private static final String VEHICLES = "VEHICLES:";
    private static final String CAPACITY = "CAPACITY:";
    private static final String OTIMA = "OTIMA:";

    public static int NUM_OTIMA;

    /* A capacidade que cada veículo possui */
    public static int VEHICLE_CAPACITY;

    /* O número de vértices, o depósito e os clientes */
    public static int NUM_VERTICE;
    /* O número de veículos */
    public static int NUM_VEHICLES;

    private static final ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public static final ArrayList<Vehicle> definitiveVehicles = new ArrayList<Vehicle>();
    
     public static final ArrayList<Vehicle> definitiveVehicles2 = new ArrayList<Vehicle>();

    private long inicio = System.currentTimeMillis();
    private long fim = System.currentTimeMillis();

    //VARIÁVEIS DEPENDENTES DO PROBLEMA
    private int distanciaPercorrida = 0;
    private boolean isPrimeiro = true;

    private Random random;

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

    @FXML
    private Button handleMenuAnalisar;
    @FXML
    private Button handleMenuCode;
    @FXML
    private Button handleMenuLimpar;

    @FXML
    private TableView<Heuristica> tableView = new TableView<>();
    @FXML
    private TableColumn<Heuristica, Integer> tableColumNome;
    @FXML
    private TableColumn<Heuristica, Integer> tableColumOtima;
    @FXML
    private TableColumn<Heuristica, Integer> tableColumMedia;
    @FXML
    private TableColumn<Heuristica, Integer> tableColumMelhor;
    @FXML
    private TableColumn<Heuristica, String> tableColumTempo;
    @FXML
    private TableColumn<Heuristica, String> tableColumnGap;
    @FXML
    private TableColumn<Heuristica, Integer> tableColumMediaMeta;
    @FXML
    private TableColumn<Heuristica, Integer> tableColumMelhorMeta;
    @FXML
    private TableColumn<Heuristica, String> tableColumTempoMeta;
    @FXML
    private TableColumn<Heuristica, String> tableColumnGapMeta;

    private List<Heuristica> listCodes = new ArrayList<>();
    private ObservableList<Heuristica> observableListCodes;

    private Heuristica heuristica;

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
            definitiveVehicles.clear();
            VEHICLE_CAPACITY = 0;
            NUM_VERTICE = 0;
            NUM_VEHICLES = 0;
            NUM_OTIMA = 0;

            textArea.setText("");

            heuristica = new Heuristica();
            // + choiceBox.getValue()
            addAll("src/main/resources/instancias_teste/" + choiceBox.getValue());
            //addCvrpCup("src/main/resources/cvrp-cup/cvrp3.txt");
            // read the data

            vizinhoMaisProximo();

        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        choiceBox.setItems(FXCollections.observableArrayList(optEscolhida));

        choiceBox.setOnAction(value);

        System.out.println(Math.sqrt(Math.pow(37 - 30, 2) + Math.pow(52 - 40, 2)));

    }

    @FXML
    public void handleMenuLimpar(ActionEvent event) throws IOException {
        /*textArea.setText("");
        textname.setText("");
        textdimension.setText("");
        textvehicles.setText("");
        textcapacity.setText("");
        textArea.setText("");
        carregarTableViewCliente();*/

        System.out.println(NUM_CLIENTES);

    }

    @FXML
    public void handleMenuLimparTabela(ActionEvent event) throws IOException {
        listCodes.clear();
        carregarTableViewCliente();

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        textArea.setText("");
        textname.setText("");
        textdimension.setText("");
        textvehicles.setText("");
        textcapacity.setText("");
        textArea.setText("");

        vertices.clear();
        arestas.clear();
        vehicles.clear();
        definitiveVehicles.clear();
        VEHICLE_CAPACITY = 0;
        NUM_VERTICE = 0;
        NUM_VEHICLES = 0;
        NUM_OTIMA = 0;

        textArea.setText("");

        heuristica = new Heuristica();
        // + choiceBox.getValue()
        addAll("src/main/resources/instancias_teste/" + choiceBox.getValue());
        //addCvrpCup("src/main/resources/cvrp-cup/cvrp3.txt");
        // read the data

        vizinhoMaisProximo();
    }

    public void carregarTableViewCliente() {

        tableColumNome.setCellValueFactory(new PropertyValueFactory<Heuristica, Integer>("instancia"));
        tableColumOtima.setCellValueFactory(new PropertyValueFactory<Heuristica, Integer>("otimo"));
        tableColumMedia.setCellValueFactory(new PropertyValueFactory<Heuristica, Integer>("mediaSolucao"));
        tableColumMelhor.setCellValueFactory(new PropertyValueFactory<Heuristica, Integer>("melhorSolucao"));
        tableColumTempo.setCellValueFactory(new PropertyValueFactory<Heuristica, String>("tempo"));
        tableColumnGap.setCellValueFactory(new PropertyValueFactory<Heuristica, String>("gap"));
        tableColumMediaMeta.setCellValueFactory(new PropertyValueFactory<Heuristica, Integer>("metaMedia"));
        tableColumMelhorMeta.setCellValueFactory(new PropertyValueFactory<Heuristica, Integer>("metaMelhor"));
        tableColumTempoMeta.setCellValueFactory(new PropertyValueFactory<Heuristica, String>("metaTempo"));
        tableColumnGapMeta.setCellValueFactory(new PropertyValueFactory<Heuristica, String>("metaGap"));

        observableListCodes = FXCollections.observableArrayList(listCodes);
        tableView.setItems(observableListCodes);

    }

    public int custoAresta(ArrayList<Vehicle> auxVehicles) {
        int custoTotalAresta = 0;
        for (int n = 0; n < auxVehicles.size(); n++) {
            int aux1, aux2;
            for (int j1 = 0, j2 = 1; j1 < auxVehicles.get(n).getRota().size() - 1; j1++, j2++) {
                aux1 = auxVehicles.get(n).getRota().get(j1).getId();
                aux2 = auxVehicles.get(n).getRota().get(j2).getId();
                custoTotalAresta += arestas.get(aux1).get(aux2).getPeso();

            }
        }
        return custoTotalAresta;

    }

    // FUNÇÃO QUE GERA OS NOVOS VIZINHOS
    public void genetica() {
        ArrayList<Vehicle> auxVehicles = new ArrayList<Vehicle>();

        definitiveVehicles2.clear();

        int MAX_GENETICA = NUM_VERTICE * 1000;
        int MAX_TIME = NUM_VERTICE * 5;

        random = new Random();
        int custoInicial = 0;

        inicio = System.currentTimeMillis();

        MELHOR = 0;

        for (int j = 0; j < NUM_VEHICLES; j++) {
            CaminhosControl.clear();
            //System.out.println("RRRR: "+vehicles.get(j).getRota().get(0).getId());
            for (int i = 0; i < vehicles.get(j).getRota().size() - 1; i++) {

                Vertice vertice = new Vertice(vehicles.get(j).getRota().get(i).getId(), vehicles.get(j).getRota().get(i).getDemanda(), vehicles.get(j).getRota().get(i).isIsVisitado(), vehicles.get(j).getRota().get(i).getX(), vehicles.get(j).getRota().get(i).getY());

                CaminhosControl.addCliente(vertice);
            }

            // Initialize population
            Population pop = new Population(50, true);
            custoInicial += (pop.getFittest().getDistance() + arestas.get(pop.getFittest().getCity(pop.getFittest().tourSize() - 1).getId()).get(0).getPeso());
            System.out.println("Custo Inicial: " + (pop.getFittest().getDistance() + arestas.get(pop.getFittest().getCity(pop.getFittest().tourSize() - 1).getId()).get(0).getPeso()));
            //System.out.println(pop.getFittest());
            System.out.println();
            // Evolve population for generations
            pop = GA.evolvePopulation(pop);
            for (int i = 0; i < 10000; i++) {
                pop = GA.evolvePopulation(pop);
            }

            System.out.println("Solução:");

            MELHOR += pop.getFittest().getDistance();
            MELHOR += arestas.get(pop.getFittest().getCity(pop.getFittest().tourSize() - 1).getId()).get(0).getPeso();

            System.out.println("Custo Final " + MELHOR);
            String print = pop.getFittest().toString();

            System.out.println("--------------------------------------------------------------------------------");
            //System.out.println("POP: @@ "+pop.getFittest().getCity(pop.getFittest().tourSize()-1).getId());
            String tokens[] = print.split(",");
            Vehicle vertics = new Vehicle(j, VEHICLE_CAPACITY, VEHICLE_CAPACITY, 0, false, 0);
            for (int h = 0; h < tokens.length; h++) {
                //System.out.println(tokens[h]);
                vertics.getRota().add(vertices.get(Integer.parseInt(tokens[h])));

            }
            vertics.getRota().add(vertices.get(0));
            auxVehicles.add(vertics);
            definitiveVehicles2.add(auxVehicles.get(j));

        }
        // addCvrpCup("src/main/resources/cvrp-cup/cvrp1.txt");
        //System.out.println("Custo Total: @@ "+MELHOR);

        /*
        while (max <= MAX_GENETICA) {

            // PARA TODOS OS NÓS DE UM CAMINHO SÃO GERADAS TROCAS DE ARESTAS
            for (int i = 0; i < NUM_VEHICLES; i++) {

                auxVehicles.clear();
                theAuxVehicles.clear();

                theAuxVehicles.add(new Vehicle(definitiveVehicles.get(i).getId(), definitiveVehicles.get(i).getCapacidadeTotal(), definitiveVehicles.get(i).getCargaAtual(),
                        definitiveVehicles.get(i).getVerticeAtual(), definitiveVehicles.get(i).isIsEntragando(), definitiveVehicles.get(i).getCaminhoFeito()));
                theAuxVehicles.get(0).setRota((Vector<Vertice>) definitiveVehicles.get(i).getRota().clone());

                int NUM_ROTA = definitiveVehicles.get(i).getRota().size();

                auxVehicles.add(new Vehicle(definitiveVehicles.get(i).getId(), definitiveVehicles.get(i).getCapacidadeTotal(), definitiveVehicles.get(i).getCargaAtual(),
                        definitiveVehicles.get(i).getVerticeAtual(), definitiveVehicles.get(i).isIsEntragando(), definitiveVehicles.get(i).getCaminhoFeito()));
                auxVehicles.get(0).setRota((Vector<Vertice>) definitiveVehicles.get(i).getRota().clone());

                for (int n = 0; n < (definitiveVehicles.get(i).getRota().size() - 2); n++) {

                    Vertice auxVertice;

                    if (NUM_ROTA > 4) {
                        int random1 = random.nextInt(((NUM_ROTA - 3) - 2) + 1) + 2;
                        int random2 = random.nextInt(((NUM_ROTA - 3) - 2) + 1) + 3;

                        while (random1 == random2) {
                            random2 = random.nextInt(((NUM_ROTA - 3) - 2) + 1) + 2;
                        }

                        auxVertice = auxVehicles.get(0).getRota().get(random1);
                        auxVehicles.get(0).getRota().set(random1, auxVehicles.get(0).getRota().get(random2));
                        auxVehicles.get(0).getRota().set(random2, auxVertice);
                    } else {
                        auxVertice = auxVehicles.get(0).getRota().get(1);
                        auxVehicles.get(0).getRota().set(1, auxVehicles.get(0).getRota().get(NUM_ROTA - 1));
                        auxVehicles.get(0).getRota().set(NUM_ROTA - 1, auxVertice);
                    }

                    theAuxVehicles.add(auxVehicles.get(0));

                }

                int custoTotalArestaAux1 = Integer.MAX_VALUE, custoTotalArestaAux2 = 0, arestaAux = 0;

                int aux1, aux2;
                for (int j = 0; j < theAuxVehicles.size(); j++) {
                    custoTotalArestaAux2 = 0;

                    if (!(theAuxVehicles.get(j).getRota().size() == 0)) {
                        for (int j1 = 0, j2 = 1; j1 < theAuxVehicles.get(j).getRota().size() - 1; j1++, j2++) {
                            aux1 = theAuxVehicles.get(j).getRota().get(j1).getId();
                            aux2 = theAuxVehicles.get(j).getRota().get(j2).getId();
                            custoTotalArestaAux2 += arestas.get(aux1).get(aux2).getPeso();

                        }

                        if (custoTotalArestaAux2 < custoTotalArestaAux1) {
                            custoTotalArestaAux1 = custoTotalArestaAux2;
                            custoTotalAresta = custoTotalArestaAux1;
                            arestaAux = j;
                        }
                    }

                }

                definitiveVehicles.set(i, theAuxVehicles.get(arestaAux));
            }
            max++;
        }*/
        fim = System.currentTimeMillis();

        heuristica.setMetaMedia(custoInicial);

        heuristica.setMetaMelhor(custoAresta(definitiveVehicles2));

        heuristica.setMetaTempo((fim - inicio) + "ms");

        float gap = (((float) heuristica.getMetaMelhor() - (float) heuristica.getOtimo()) / (float) heuristica.getOtimo());

        double d = Math.rint(gap * 100.0);

        heuristica.setMetaGap((int) d + "%");

        listCodes.add(heuristica);

        this.imprimiRota(definitiveVehicles2);
    }

    public void vizinhoMaisProximo() {
        int menorDistancia = 0;
        int verticeAtual = 0;
        int custoTotal = 0;
        int custoTotalAresta = 0;
        int clienteAtual = 0;
        int count = 0;

        inicio = System.currentTimeMillis();

        for (int i = 0; i < NUM_VEHICLES; i++) {

            custoTotalAresta = 0;

            vertices.get(0).setIsVisitado(false);

            vehicles.add(new Vehicle(i, VEHICLE_CAPACITY, VEHICLE_CAPACITY, verticeAtual, false, 0));

            //adiciona o deposito no inicio da rota
            vehicles.get(i).newVertice(vertices.get(0));
            vertices.get(0).setIsVisitado(true);

            for (int j = 0; j < NUM_VERTICE;) {

                menorDistancia = Integer.MAX_VALUE;

                for (int n = 1; n < NUM_VERTICE; n++) {

                    //Se o elemento nao foi visitado
                    if (vertices.get(n).isIsVisitado() == false) {
                        if (vehicles.get(i).verificaCargaAtual(vertices.get(n).getDemanda())) {
                            //System.out.println("Valor de J:"+j);
                            if (arestas.get(j).get(n).getPeso() != 0 && arestas.get(j).get(n).getPeso() < menorDistancia) {
                                //System.out.println("PESO"+arestas.get(j).get(n).getPeso());
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

            heuristica.setMediaSolucao(custoAresta(vehicles));

        }

       

        this.imprimiRota(vehicles);

        this.swapVizinhanca();
    }

    public Solucao solucao(Solucao solucao, int menorDistancia, int verticeAtual, int i, int custoTotalAresta) {

        Vehicle atualVehicle = new Vehicle(solucao.getVehicle().getId(), solucao.getVehicle().getCapacidadeTotal(), solucao.getVehicle().getCargaAtual(), solucao.getVehicle().getVerticeAtual(),
                solucao.getVehicle().isIsEntragando(), solucao.getVehicle().getCaminhoFeito());
        atualVehicle.setRota((Vector<Vertice>) solucao.getVehicle().getRota().clone());

        ArrayList<Vertice> verticesaux = new ArrayList<Vertice>();
        verticesaux = (ArrayList<Vertice>) solucao.getVertices().clone();

        for (int j = verticeAtual; j < NUM_VERTICE;) {

            menorDistancia = Integer.MAX_VALUE;

            for (int n = 1; n < NUM_VERTICE; n++) {

                //Se o elemento nao foi visitado
                if (verticesaux.get(n).isIsVisitado() == false) {
                    if (atualVehicle.verificaCargaAtual(verticesaux.get(n).getDemanda())) {
                        //System.out.println("Valor de J:"+j);

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
            if (verticesaux.get(verticeAtual).isIsVisitado() == false) {

                verticesaux.get(verticeAtual).setIsVisitado(true);
                atualVehicle.newVertice(verticesaux.get(verticeAtual));
                atualVehicle.setCaminhoFeito(custoTotalAresta);

                custoTotalAresta += arestas.get(j).get(verticeAtual).getPeso();
                atualVehicle.setCaminhoFeito(custoTotalAresta);
                //System.out.println("Custo total1: " + custoTotal);
                j = verticeAtual;

            }

        }
        Solucao definitiva = new Solucao(atualVehicle);
        definitiva.setVertices((ArrayList<Vertice>) verticesaux.clone());

        return definitiva;
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

                if (dados.startsWith(OTIMA)) {
                    System.out.println("OTIMA: " + dados.substring(OTIMA.length()).replaceAll("\\s+", ""));
                    textname.setText(dados.substring(OTIMA.length()).replaceAll("\\s+", ""));
                    NUM_OTIMA = Integer.parseInt(dados.substring(OTIMA.length()).replaceAll("\\s+", ""));
                    heuristica.setOtimo(NUM_OTIMA);
                } else if (dados.startsWith(NAME)) {
                    System.out.println("NOME: " + dados.substring(NAME.length()).replaceAll("\\s+", ""));
                    textname.setText(dados.substring(NAME.length()).replaceAll("\\s+", ""));
                    heuristica.setInstancia(dados.substring(NAME.length()).replaceAll("\\s+", ""));
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
                /*
                for(int gg = 0; gg < arestasFor.size() ; gg++){
                    System.out.print("L: "+i+" C: "+gg+" ");
                    System.out.print("Peso: "+arestasFor.get(gg).getPeso()+"|");
                }
                System.out.println();
                 */
                arestas.add(arestasFor);
                linha = lerArq.readLine();

            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }

    public void addCvrpCup(String file) {

        System.out.println("LENDO ARQUIVO");
        try {
            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String dados;
            while (linha != null) {
                dados = linha.trim().replaceAll("\t", "").replaceAll("\\s+", " ");

                if (dados.startsWith(OTIMA)) {
                    System.out.println("OTIMA: " + dados.substring(OTIMA.length()).replaceAll("\\s+", ""));
                    // textname.setText(dados.substring(OTIMA.length()).replaceAll("\\s+", ""));
                    NUM_OTIMA = Integer.parseInt(dados.substring(OTIMA.length()).replaceAll("\\s+", ""));
                    //heuristica.setOtimo(NUM_OTIMA);
                } else if (dados.startsWith(NAME)) {
                    System.out.println("NOME: " + dados.substring(NAME.length()).replaceAll("\\s+", ""));
                    //textname.setText(dados.substring(NAME.length()).replaceAll("\\s+", ""));
                    //heuristica.setInstancia(dados.substring(NAME.length()).replaceAll("\\s+", ""));
                } else if (dados.startsWith(DIMENSION)) {
                    System.out.println("DIMENSION: " + dados.substring(DIMENSION.length()).replaceAll("\\s+", ""));
                    NUM_VERTICE = Integer.parseInt(dados.substring(DIMENSION.length()).replaceAll("\\s+", ""));
                    // textdimension.setText(dados.substring(DIMENSION.length()).replaceAll("\\s+", ""));
                } else if (dados.startsWith(VEHICLES)) {
                    System.out.println("VEHICLES: " + dados.substring(VEHICLES.length()).replaceAll("\\s+", ""));
                    NUM_VEHICLES = Integer.parseInt(dados.substring(VEHICLES.length()).replaceAll("\\s+", ""));
                    // textvehicles.setText(dados.substring(VEHICLES.length()).replaceAll("\\s+", ""));
                } else if (dados.startsWith(CAPACITY)) {
                    System.out.println("CAPACITY: " + dados.substring(CAPACITY.length()).replaceAll("\\s+", ""));
                    VEHICLE_CAPACITY = Integer.parseInt(dados.substring(CAPACITY.length()).replaceAll("\\s+", ""));
                    //textcapacity.setText(dados.substring(CAPACITY.length()).replaceAll("\\s+", ""));
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
            if (dados.startsWith("NODE_COORD_SECTION:")) {
                linha = lerArq.readLine();

            }

            for (int i = 0; i < NUM_VERTICE; i++) {

                dados = linha.trim().replaceAll("\t", "").replaceAll("\\s+", " ");
                String tokens[] = dados.split(" ");

                if (tokens[0].equalsIgnoreCase("" + i)) {
                    vertices.get(i).setX(Integer.parseInt(tokens[1]));
                    vertices.get(i).setY(Integer.parseInt(tokens[2]));
                }
                linha = lerArq.readLine();
            }

            for (int i = 0; i < NUM_VERTICE; i++) {

                ArrayList<Aresta> arestasFor = new ArrayList<Aresta>();

                for (int j = 0; j < NUM_VERTICE; j++) {

                    arestasFor.add(new Aresta((int) Math.sqrt(Math.pow(vertices.get(j).getX() - vertices.get(i).getX(), 2) + Math.pow(vertices.get(j).getY() - vertices.get(i).getY(), 2)), j));

                }

                arestas.add(arestasFor);
                linha = lerArq.readLine();

            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        } finally {

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

            for (int n = 0; n < auxVehicles.size(); n++) {
                if (auxVehicles.get(n).getId() == i) {
                    print += "[DEPOSITO -> ";
                    for (int j = 0; j < auxVehicles.get(n).getRota().size(); j++) {
                        print += auxVehicles.get(n).getRota().get(j).getId() + " -> ";
                    }
                    print += " DEPOSITO]\n";

                    int aux1, aux2;
                    for (int j1 = 0, j2 = 1; j1 < auxVehicles.get(n).getRota().size() - 1; j1++, j2++) {
                        aux1 = auxVehicles.get(n).getRota().get(j1).getId();
                        aux2 = auxVehicles.get(n).getRota().get(j2).getId();
                        custoTotalAresta += arestas.get(aux1).get(aux2).getPeso();

                    }

                    custoTotal += custoTotalAresta;
                    print += "Custo Total Aresta: " + custoTotalAresta + "\n";
                    clientes = vehicles.get(n).getRota().size() - 2;
                    print += "Clientes atendidos: " + clientes + "\n\n";
                    clientesAtendidos += clientes;

                }
            }

        }
        NUM_CLIENTES += clientesAtendidos;

        print += "Custo total: " + custoTotal + "\n\n";
        print(print);
        textArea.setText(textArea.getText() + "\n----------------------------------\n" + print);

        carregarTableViewCliente();

    }

    public void print(String print) {
        System.out.print(print);
    }

    public void swapVizinhanca() {

        ArrayList<Vehicle> auxVehicles = new ArrayList<Vehicle>();
        Vehicle theAuxVehicles = null;
        int custoTotalAresta1 = 0;
        for (int i = 0; i < NUM_VEHICLES; i++) {
            custoTotalAresta1 = 0;
            theAuxVehicles = null;
            theAuxVehicles = vehicles.get(i);
            int aux1, aux2, custoTotalAresta2 = 0;
            for (int j1 = 0, j2 = 1; j1 < theAuxVehicles.getRota().size() - 1; j1++, j2++) {
                aux1 = vehicles.get(i).getRota().get(j1).getId();
                aux2 = vehicles.get(i).getRota().get(j2).getId();
                custoTotalAresta1 += arestas.get(aux1).get(aux2).getPeso();
            }
            
   

            auxVehicles.clear();
            for (int n = 1; n < (theAuxVehicles.getRota().size() - 2); n++) {
                //System.out.println(" V: "+((vehicles.get(i).getRota().size() - 2)/2));

                auxVehicles.add(new Vehicle(theAuxVehicles.getId(), theAuxVehicles.getCapacidadeTotal(), theAuxVehicles.getCargaAtual(),
                        theAuxVehicles.getVerticeAtual(), theAuxVehicles.isIsEntragando(), theAuxVehicles.getCaminhoFeito()));

                auxVehicles.get(n - 1).setRota((Vector<Vertice>) theAuxVehicles.getRota().clone());

                Vertice auxVertice;

                auxVertice = auxVehicles.get(n - 1).getRota().get(n);

                auxVehicles.get(n - 1).getRota().set(n, auxVehicles.get(n - 1).getRota().get(n + 1));
                auxVehicles.get(n - 1).getRota().set(n + 1, auxVertice);

                custoTotalAresta2 = 0;
                for (int j1 = 0, j2 = 1; j1 < auxVehicles.get(n - 1).getRota().size() - 1; j1++, j2++) {
                    aux1 = auxVehicles.get(n - 1).getRota().get(j1).getId();
                    aux2 = auxVehicles.get(n - 1).getRota().get(j2).getId();
                    custoTotalAresta2 += arestas.get(aux1).get(aux2).getPeso();

                }
                System.out.println(custoTotalAresta2+" < "+custoTotalAresta1);
                if (custoTotalAresta2 < custoTotalAresta1) {
                    
                    custoTotalAresta1 = custoTotalAresta2;
                    theAuxVehicles = auxVehicles.get(n - 1);
                }

            }

            Vehicle theAuxVehicles2 = new Vehicle(theAuxVehicles.getId(), theAuxVehicles.getCapacidadeTotal(), theAuxVehicles.getCargaAtual(),
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
            } */
            System.out.println("----------------");
            definitiveVehicles.add(theAuxVehicles);

        }

        fim = System.currentTimeMillis();

        heuristica.setMelhorSolucao(custoAresta(definitiveVehicles));

        heuristica.setTempo(fim - inicio + "ms");

        float gap = (((float) heuristica.getMelhorSolucao() - (float) heuristica.getOtimo()) / (float) heuristica.getOtimo());

        double d = Math.rint(gap * 100.0);

        heuristica.setGap((int) d + "%");


        this.imprimiRota(definitiveVehicles);

        this.genetica();
    }

}
