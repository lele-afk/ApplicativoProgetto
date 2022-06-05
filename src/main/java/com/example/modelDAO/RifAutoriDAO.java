package com.example.modelDAO;

import com.example.model.Autori;
import com.example.modelDAOInterface.RifAutoriDAOI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RifAutoriDAO implements RifAutoriDAOI {
    private Connection connection;
    private final PreparedStatement getRifXAutori;
    private final PreparedStatement postRifXAutori;

    public RifAutoriDAO(Connection connection)throws SQLException {
        this.connection = connection;
        this.postRifXAutori = connection.prepareStatement("INSERT INTO \"RiferimentoXAutore\"(\"idAutore\", \"idRiferimento\") VALUES (?, ?)");
        this.getRifXAutori = connection.prepareStatement("SELECT * FROM public.\"RiferimentoXAutore\" INNER JOIN \"Autore\" ON \"RiferimentoXAutore\".\"idAutore\" = \"Autore\".\"idAutore\" WHERE \"idRiferimento\" =?");
    }



    @Override
    public ObservableList<Autori> getRifXAutori(Integer idRifXAutore) throws SQLException {
        getRifXAutori.setInt(1,idRifXAutore);
        ObservableList<Autori> listaAutori= FXCollections.observableArrayList();
        ResultSet res = getRifXAutori.executeQuery();

        while(res.next()){
            listaAutori.add( new Autori( res.getString("nome"),res.getString("cognome"),res.getInt("idAutore"),false));
        }

     
        return listaAutori;
    }
    @Override
    public int postRifXAutori(Integer idAutore, Integer idRif) throws SQLException {
        postRifXAutori.setInt(1,idAutore);
        postRifXAutori.setInt(2, idRif);
        return postRifXAutori.executeUpdate();
    }
}
