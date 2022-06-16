package com.example.modelDAO;

import com.example.model.Utente;
import com.example.modelDAOInterface.UtenteDAOI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO implements UtenteDAOI {
    private Connection connection;
    private final PreparedStatement getUtente;

    public UtenteDAO(Connection connection) throws SQLException {
        this.connection = connection;
        getUtente = connection.prepareStatement("SELECT * FROM \"User\" WHERE \"idUser\"= ?");
    }

    public Utente getUtente( Integer idUser) throws SQLException {
        getUtente.setInt(1,idUser);
        ResultSet res = getUtente.executeQuery();
        Utente utente = null;
        while(res.next()){
            utente=  new Utente(res.getString("nome"),res.getString("cognome"),res.getInt("idUser") );
        }
        return utente;

    }


}
