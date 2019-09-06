package com.alisonbarreiro.cvrp;

import Model.Vertice;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {

    private static final ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    
    private static final Pattern natValida = Pattern.compile("\\s*[0-9]*");
    private static final Pattern realValida = Pattern.compile("\\s*[0-9]*[.][0-9]*");

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addAll("src/main/resources/instances-kruskal/n1500A.txt");
    }

    public static void addAll(String file) {
        System.out.println("LENDO ARQUIVO");
        try {
            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                String dados[] = linha.split("\\r?\\n");

                for (int i = 0; i < dados.length; i++) {
                    String tokens[] = dados[i].trim().replaceAll("\t", "").replaceAll("\\s+", " ").split(" ");;

                    if (tokens[i].equalsIgnoreCase("NAME:")) {
                        System.out.println("NOME: " + tokens[i+1]);
                    } else if (tokens[i].equalsIgnoreCase("DIMENSION:")) {
                        System.out.println("DIMENSION: " + tokens[i+1]);
                    } else if (natValida.matcher(tokens[i]).matches()) {
                        if(realValida.matcher(tokens[i+1]).matches()){
                            System.out.print("X: " + tokens[i+1]);
                        }
                        if(realValida.matcher(tokens[i+2]).matches()){
                            System.out.print("Y: " + tokens[i+2]);
                        }
                        vertices.add(new Vertice(Integer.parseInt(tokens[i]) , Double.parseDouble(tokens[i+1]) , Double.parseDouble(tokens[i+2])));
                    }
                }
                System.out.println("");

                /*
                for(int i = 0; i < dados.length;i++){
                    String replaceAll = dados[i].trim().replaceAll("\t", "").replaceAll("\\s+", " ");
                }
                

                
                if(dados[0].equalsIgnoreCase("NAME:")){
                    System.out.println("NOME: "+dados[1]);
                }else if(dados[0].equalsIgnoreCase("DIMENSION:")){
                    System.out.println("DIMENSION: "+dados[1]);
                }else if(!dados[0].equalsIgnoreCase("DISPLAY_DATA_SECTION")||!dados[0].equalsIgnoreCase("EOF")){
                    System.out.println("X: "+dados[0]);
                }
                 */
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            
            
            System.out.println(Math.sqrt(Math.pow(vertices.get(1).getX()-vertices.get(0).getX(),2)+Math.pow(vertices.get(1).getY()-vertices.get(0).getY(),2)));
            
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }

}
