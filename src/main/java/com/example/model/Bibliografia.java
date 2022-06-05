package com.example.model;

import java.util.ArrayList;

public class Bibliografia {
    private String nome;
    private ArrayList<Utente> utente= new ArrayList<>();
    private ArrayList<RifBibliografico> rifBibliografico = new ArrayList<>();

    public Bibliografia(String nome, ArrayList<Utente> utente, ArrayList<RifBibliografico> rifBibliografico) {
        this.nome = nome;
        this.utente = utente;
        this.rifBibliografico = rifBibliografico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Utente> getUtente() {
        return utente;
    }

    public void setUtente(ArrayList<Utente> utente) {
        this.utente = utente;
    }

    public ArrayList<RifBibliografico> getRifBibliografico() {
        return rifBibliografico;
    }

    public void setRifBibliografico(ArrayList<RifBibliografico> rifBibliografico) {
        this.rifBibliografico = rifBibliografico;
    }
}
