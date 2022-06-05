package com.example.modelDAOInterface;

import com.example.model.Autori;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface RifAutoriDAOI {
    public ObservableList<Autori> getRifXAutori(Integer idRifXAutori) throws SQLException;

    public int postRifXAutori(Integer idAutore, Integer idRif) throws SQLException;
}
