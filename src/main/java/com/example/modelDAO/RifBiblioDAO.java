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
    private final PreparedStatement getRifxRif;
    private final PreparedStatement setRimandi;
    private final PreparedStatement deleteRimando;

    public RifBiblioDAO(Connection connection) throws SQLException {
        this.connection = connection;
       getRifxRif=connection.prepareStatement("SELECT * FROM \"Rimando\" INNER JOIN \"Riferimento\" ON \"Rimando\".\"idRimando\" = \"Riferimento\".\"idRiferimento\" WHERE \"Rimando\".\"idRiferimento\" = ?");
        postRifxRif = connection.prepareStatement("INSERT INTO \"Rimando\"(\"idRiferimento\", \"idRimando\") VALUES (?, ?) RETURNING \"id\"");
        getRif = connection.prepareStatement("SELECT * FROM \"Riferimento\" INNER JOIN \"Tipologia\" ON \"Riferimento\".\"tipo\" = \"Tipologia\".\"idTipologia\"");
        setRimandi = connection.prepareStatement("SELECT * FROM \"Riferimento\" WHERE \"idRiferimento\" != ? ");
        deleteRimando = connection.prepareStatement("DELETE FROM public.\"Rimando\" WHERE \"idRimando\" = ? Returning \"idRimando\"");
        // getRif = connection.prepareStatement("SELECT DISTINCT  * FROM \"Riferimento\" FULL JOIN \"Rimando\" ON \"Rimando\".\"idRiferimento\" = \"Riferimento\".\"idRiferimento\"");
        getRifWithTitle = connection.prepareStatement("SELECT * FROM \"Riferimento\" WHERE \"titolo\" = ?");
        postRif = connection.prepareStatement("INSERT INTO \"Riferimento\"(titolo, data, descrizione, doi, url, \"idUser\", tipo) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING \"idRiferimento\";");
        getRifXAutore = connection.prepareStatement("SELECT * FROM RiferimentoXAutore WHERE idAutore=");
    }

    @Override
    public ObservableList<RifBibliografico> getRif() throws SQLException {
        ObservableList<RifBibliografico> listaRif = FXCollections.observableArrayList();
        ResultSet res = getRif.executeQuery();
        while(res.next()){
            //listaRif.add(new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("tipo"), res.getString("idUser"),res.getString("descrizione"), res.getInt("idRimando")/*,getRifXAutore(res.getInt("idAutore"))*/));
            listaRif.add(new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("nome"), res.getString("idUser"),res.getString("descrizione"), null/*,getRifXAutore(res.getInt("idAutore"))*/));

        }
        return listaRif;
    }

    @Override
    public Integer deleteRimando(Integer idRif) throws SQLException {

        deleteRimando.setInt(1,idRif);
        ResultSet res = deleteRimando.executeQuery();
        Integer id=0;
        while (res.next()){
            id= res.getInt("idRimando");
        }

        return id;

    }
    @Override
    public ObservableList<RifBibliografico> setRimandi(Integer idRif) throws SQLException {
        ObservableList<RifBibliografico> listaRif = FXCollections.observableArrayList();
        setRimandi.setInt(1,idRif);
        ResultSet res = setRimandi.executeQuery();
        while(res.next()){
            //listaRif.add(new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("tipo"), res.getString("idUser"),res.getString("descrizione"), res.getInt("idRimando")/*,getRifXAutore(res.getInt("idAutore"))*/));
            listaRif.add(new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("tipo"), res.getString("idUser"),res.getString("descrizione"), null/*,getRifXAutore(res.getInt("idAutore"))*/));

        }
        return listaRif;
    }
    @Override
    public ObservableList<RifBibliografico> getRifxRif(Integer idRiferimento) throws SQLException {
        ObservableList<RifBibliografico> listaRif = FXCollections.observableArrayList();
        getRifxRif.setInt(1,idRiferimento);
        ResultSet res = getRifxRif.executeQuery();

        while(res.next()){

            listaRif.add(new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("tipo"), res.getString("idUser"),res.getString("descrizione"), res.getInt("idRimando")));

        }

        return listaRif;
    }

    @Override
    public Integer postRif(String titolo, String url, String doi, String descrizione, String dataCreazione , Integer tipo) throws SQLException {
        postRif.setString(1,titolo);
        postRif.setString(2, dataCreazione.toString());
        postRif.setString(3,descrizione);
        postRif.setString(4,doi);
        postRif.setInt(6,1);
        postRif.setString(5,url);
        postRif.setInt(7,tipo);
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
        this.getRifxRif(idRimando);
        return res;
    }


   /* public RifBibliografico getRifWithTitle(String title) throws SQLException {
        getRifWithTitle.setString(1,title);

        ResultSet res = getRifWithTitle.executeQuery();

        return new RifBibliografico(res.getInt("idRiferimento"),res.getString("titolo"),res.getString("data"), res.getString("url"), res.getString("doi"), res.getString("tipo"), res.getString("idUser"),res.getString("descrizione"), res.getString("idRiferimento"));
    }*/

    public Autori getRifXAutore(Integer idAutore) throws SQLException {
        getRifXAutore.executeQuery("SELECT * FROM \"RiferimentoXAutore\" WHERE \"idAutore\"=" +idAutore);
        ResultSet res = getRifXAutore.executeQuery();
        return new Autori(res.getString("nome"), res.getString("cognome"),res.getInt("idAutore"),false);
        }

}
