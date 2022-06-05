package com.example.modelDAOInterface;

import com.example.model.RifBibliografico;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface RifBiblioDAOI {

    public ObservableList<RifBibliografico> getRif() throws SQLException;
    public Integer postRif(String titolo, String url, String doi, String descrizione, String dataCreazione/* Autori autore*/, String tipo) throws SQLException;
}
