package com.example.modelDAOInterface;

import com.example.model.RifBibliografico;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RifBiblioDAOI {

    public ObservableList<RifBibliografico> getRif(Integer idUser) throws SQLException;
    public Integer postRif(String titolo, String url, String doi, String descrizione, String dataCreazione, Integer tipo, Integer idUser) throws SQLException;
    public ResultSet postRifxRif(Integer idRiferimento, Integer idRimando) throws SQLException;
    public ObservableList<RifBibliografico> getRifxRif(Integer idRiferimento) throws SQLException;
    public ObservableList<RifBibliografico> setRimandi(Integer idRif) throws SQLException;
    public Integer deleteRimando(Integer idRif) throws SQLException ;
    public Integer deleteRiferimento(Integer idRif) throws SQLException;
    public Integer deleteRifXAutore(Integer idRif) throws SQLException;
    public Integer deleteRifXRimando(Integer idRif) throws SQLException;
    public ObservableList<RifBibliografico> getAdvanceRif(Integer idUser,String filtro) throws SQLException;

    }
