package com.example.modelDAO;

import com.example.model.Autori;
import com.example.model.RifBibliografico;
import com.example.modelDAOInterface.RifBiblioDAOI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RifBiblioDAO implements RifBiblioDAOI {
    private Connection connection;
    private final PreparedStatement getRif;
    private PreparedStatement getRifWithTitle;
    private final PreparedStatement getRifXAutore;
    private final PreparedStatement postRif;
    private final PreparedStatement postRifxRif;
    public RifBiblioDAO(Connection connection) throws SQLException {
        this.connection = connection;
        postRifxRif = connection.prepareStatement("INSERT INTO \"Rimando\"(\"idRiferimento\", \"idRimando\") VALUES (?, ?) RETURNING \"id\"");
        getRif = connection.prepareStatement("SELECT * FROM \"Riferimento\"");
        getRifWithTitle = connection.prepareStatement("SELECT * FROM \"Riferimento\" WHERE \"titolo\" = ?");
        postRif = connection.prepareStatement("INSERT INTO \"Riferimento\"(titolo, data, descrizione, doi, url, \"idUser\", tipo) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING \"idRiferimento\";");
        getRifXAutore = connection.prepareStatement("SELECT * FROM RiferimentoXAutore WHERE idAutore=");
    }

    @Override
    public ObservableList<RifBibliografico> getRif() throws SQLException {
        ObservableList<RifBibliografico> listaRif = FXCollections.observableArrayList();
        ResultSet res = getRif.executeQuery();
        while(res.next()){
            listaRif.add(new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("tipo"), res.getString("idUser"),res.getString("descrizione"), res.getString("idRiferimento")/*,getRifXAutore(res.getInt("idAutore"))*/));

        }
        return listaRif;
    }

    @Override
    public Integer postRif(String titolo, String url, String doi, String descrizione, String dataCreazione /*Autori autore*/, String tipo) throws SQLException {
        postRif.setString(1,titolo);
        postRif.setString(2, dataCreazione.toString());
        postRif.setString(3,descrizione);
        postRif.setString(4,doi);
        postRif.setInt(6,1);
        postRif.setString(5,url);
        postRif.setString(7,tipo);
        ResultSet res= postRif.executeQuery();
        Integer id=0;
        while (res.next()){
            id= res.getInt("idRiferimento");
        }

    return id;
    }

    public ResultSet postRifxRif(Integer idRif, Integer idRimando)throws SQLException{
        postRifxRif.setInt(1,idRif);
        postRifxRif.setInt(2,idRimando);
        ResultSet res = postRifxRif.executeQuery();
        return res;
    }


    public RifBibliografico getRifWithTitle(String title) throws SQLException {
        getRifWithTitle.setString(1,title);

        ResultSet res = getRifWithTitle.executeQuery();

        return new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("tipo"), res.getString("idUser"),res.getString("descrizione"), res.getString("idRiferimento")/*,getRifXAutore(res.getInt("idAutore"))*/);
    }

    public Autori getRifXAutore(Integer idAutore) throws SQLException {
        getRifXAutore.executeQuery("SELECT * FROM \"RiferimentoXAutore\" WHERE \"idAutore\"=" +idAutore);
        ResultSet res = getRifXAutore.executeQuery();
        return new Autori(res.getString("nome"), res.getString("cognome"),res.getInt("idAutore"),false);
        }

}
