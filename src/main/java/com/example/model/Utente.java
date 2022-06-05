package com.example.model;

import java.util.ArrayList;

public class Utente {
    private String nome;
    private String cognome;
    private String codiceUnivoco;
    //assaciazione utente con bibliografia
    private ArrayList<RifBibliografico> assBiblioUtente = new ArrayList<>();

    public Utente(String nome, String cognome, String codiceUnivoco) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceUnivoco = codiceUnivoco;
    }
    public Utente(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }
}
