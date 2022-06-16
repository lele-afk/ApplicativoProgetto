package com.example.model;

public class Utente {
    private String nome;
    private String cognome;
    private static Integer codiceUnivoco;



    public Utente(String nome, String cognome, Integer codiceUnivoco) {
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

    public Integer getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(Integer codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }
}
