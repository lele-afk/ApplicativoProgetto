package com.example.model;

public class Autori {
    private String nome;
    private String cognome;
    private Integer codiceUnivoco;
    private Boolean check;

    public Autori(String nome, String cognome, Integer codiceUnivoco,Boolean check) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceUnivoco = codiceUnivoco;
        this.check = check;
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

    public Integer getItem() {
        return this.codiceUnivoco;
    }

    public boolean getCheck() {
        return check.booleanValue();
    }
}
