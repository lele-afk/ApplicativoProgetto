package com.example.model;

public class Tipologia {
    private  Integer idTipologia;
    private String nome;

    public Tipologia(Integer idTipologia, String nome) {
        this.idTipologia = idTipologia;
        this.nome = nome;
    }

    public Integer getIdTipologia() {
        return idTipologia;
    }

    public void setIdTipologia(Integer idTipologia) {
        this.idTipologia = idTipologia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
