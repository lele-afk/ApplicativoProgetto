package com.example.model;

import java.util.ArrayList;
import java.util.Date;

public class CategoriaPersonalizzata {
    private String nome;
    private ArrayList<Autori> autori= new ArrayList<>();
    private ArrayList<RifBibliografico> rifBibliografico = new ArrayList<>();
    private Date data;
    private String parolaChiave;

    public CategoriaPersonalizzata(String nome, ArrayList<Autori> autori, ArrayList<RifBibliografico> rifBibliografico, Date data, String parolaChiave) {
        this.nome = nome;
        this.autori = autori;
        this.rifBibliografico = rifBibliografico;
        this.data = data;
        this.parolaChiave = parolaChiave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Autori> getAutori() {
        return autori;
    }

    public void setAutori(ArrayList<Autori> autori) {
        this.autori = autori;
    }

    public ArrayList<RifBibliografico> getRifBibliografico() {
        return rifBibliografico;
    }

    public void setRifBibliografico(ArrayList<RifBibliografico> rifBibliografico) {
        this.rifBibliografico = rifBibliografico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getParolaChiave() {
        return parolaChiave;
    }

    public void setParolaChiave(String parolaChiave) {
        this.parolaChiave = parolaChiave;
    }
}
