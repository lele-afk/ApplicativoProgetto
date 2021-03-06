package com.example.model;

import com.example.connection.DbConnection;
import com.example.modelDAO.UtenteDAO;

import java.sql.SQLException;

public class RifBibliografico  {
    private Integer idRiferimento;
    private String titolo;
    private String data;
    private String URL;
    private String DOI;
    private String tipo;
    private Utente utente;
    private String descrizione;
    private  Integer idRimando;

    public RifBibliografico(Integer idRiferimento,String titolo, String data, String URL, String DOI, String tipo, String idUtente, String descrizione, Integer idRimando) throws SQLException {
        this.titolo = titolo;
        this.idRiferimento=idRiferimento;
        this.data = data;
        this.URL = URL;
        this.DOI = DOI;
        this.tipo = tipo;
        UtenteDAO utenteDAO = new UtenteDAO(DbConnection.getInstance().getConnection());
        this.utente = utenteDAO.getUtente(1);
        this.descrizione = descrizione;
        this.idRimando = idRimando;

    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getId() {
        return idRiferimento;
    }



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDOI() {
        return DOI;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    public String getTipo() {
        return tipo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }



    public Boolean isSelected() {
        return false;
    }

    public void setSelected(Boolean new_val) {
        System.out.println(new_val);
    }

    public Integer getIdRimando() {
        return idRimando;
    }

    public void setIdRimando(Integer idRimando) {
        this.idRimando = idRimando;
    }

  /*  public List<Autori> getAutori() {
        return autori;
    }

    public void setAutori(List<Autori> autori) {
        this.autori = autori;
    }

   */
}
