package com.example.modelDAOInterface;

import com.example.model.Tipologia;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface TipologiaDAOI {
    public Integer createTipologia(String nome) throws SQLException;
    public ObservableList<Tipologia> getTipologia() throws SQLException ;
}
