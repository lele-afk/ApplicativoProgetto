package com.example.modelDAOInterface;

import com.example.model.RifBibliografico;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RifBiblioDAOI {

    public ObservableList<RifBibliografico> getRif() throws SQLException;
    public Integer postRif(String titolo, String url, String doi, String descrizione, String dataCreazione, String tipo) throws SQLException;
    public ResultSet postRifxRif(Integer idRiferimento, Integer idRimando) throws SQLException;
    public ObservableList<RifBibliografico> getRifxRif(Integer idRiferimento) throws SQLException;
    public ObservableList<RifBibliografico> setRimandi(Integer idRif) throws SQLException;
    public Integer deleteRimando(Integer idRif) throws SQLException ;
    }
