package com.example.modelDAO;

import com.example.controller.WrapperCheck;
import com.example.model.Autori;
import com.example.modelDAOInterface.AutoriDAOI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoriDAO implements AutoriDAOI {
    private Connection connection;
    private final PreparedStatement getAutori;


    public AutoriDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.getAutori= connection.prepareStatement("SELECT * FROM \"Autore\"");
    }

    @Override
    public ObservableList<WrapperCheck<Autori>> getAutori() throws SQLException {
        ObservableList<WrapperCheck<Autori>> listaAutori= FXCollections.observableArrayList();
        ResultSet res = getAutori.executeQuery();
        while(res.next()){
            listaAutori.add(new WrapperCheck<>(new Autori(res.getString("nome"), res.getString("cognome"),res.getInt("idAutore"),false)));
        }
        return listaAutori;
    }
}
