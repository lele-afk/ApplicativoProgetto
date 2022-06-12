package com.example.modelDAO;

import com.example.model.Tipologia;
import com.example.modelDAOInterface.TipologiaDAOI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipologiaDAO implements TipologiaDAOI {
    private Connection connection;
    private final PreparedStatement createTipologia;
    private final PreparedStatement getTipologia;

    public TipologiaDAO(Connection connection) throws SQLException {
        this.connection = connection;
        getTipologia= connection.prepareStatement("SELECT * FROM public.\"Tipologia\"");
        createTipologia = connection.prepareStatement("INSERT INTO public.\"Tipologia\" (nome) VALUES (?) RETURNING \"idTipologia\"");

    }


    @Override
    public ObservableList<Tipologia> getTipologia() throws SQLException {
        ObservableList<Tipologia> listaTipo = FXCollections.observableArrayList();
        ResultSet res = getTipologia.executeQuery();
        while(res.next()){
            //listaRif.add(new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("tipo"), res.getString("idUser"),res.getString("descrizione"), res.getInt("idRimando")/*,getRifXAutore(res.getInt("idAutore"))*/));
            listaTipo.add(new Tipologia(res.getInt("idTipologia"),res.getString("nome")));

        }
        return listaTipo;
    }

    @Override
    public Integer createTipologia(String nome) throws SQLException {
        createTipologia.setString(1,nome);
        ResultSet res = createTipologia.executeQuery();
        Integer id=0;
        while (res.next()){
            id= res.getInt("idTipologia");
        }

        return id;
    }
}
