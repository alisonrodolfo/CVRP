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
public class Heuristica {
    private String instancia;
    private int otimo;
    private int mediaSolucao;
    private int melhorSolucao;
    private String tempo;
    private String gap;
    private int metaMedia;
    private int metaMelhor;
    private String metaTempo;
    private String metaGap;

    public Heuristica() {
    }
    
    

    public Heuristica(String instancia, int otimo, int mediaSolucao, int melhorSolucao, String tempo, String gap, int metaMedia, int metaMelhor, String metaTempo, String metaGap) {
        this.instancia = instancia;
        this.otimo = otimo;
        this.mediaSolucao = mediaSolucao;
        this.melhorSolucao = melhorSolucao;
        this.tempo = tempo;
        this.gap = gap;
        this.metaMedia = metaMedia;
        this.metaMelhor = metaMelhor;
        this.metaTempo = metaTempo;
        this.metaGap = metaGap;
    }

    public String getInstancia() {
        return instancia;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

    public int getOtimo() {
        return otimo;
    }

    public void setOtimo(int otimo) {
        this.otimo = otimo;
    }

    public int getMediaSolucao() {
        return mediaSolucao;
    }

    public void setMediaSolucao(int mediaSolucao) {
        this.mediaSolucao = mediaSolucao;
    }

    public int getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(int melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }


    public int getMetaMedia() {
        return metaMedia;
    }

    public void setMetaMedia(int metaMedia) {
        this.metaMedia = metaMedia;
    }

    public int getMetaMelhor() {
        return metaMelhor;
    }

    public void setMetaMelhor(int metaMelhor) {
        this.metaMelhor = metaMelhor;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getMetaTempo() {
        return metaTempo;
    }

    public void setMetaTempo(String metaTempo) {
        this.metaTempo = metaTempo;
    }


    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap;
    }

    public String getMetaGap() {
        return metaGap;
    }

    public void setMetaGap(String metaGap) {
        this.metaGap = metaGap;
    }


    
  
    
    
    
}
