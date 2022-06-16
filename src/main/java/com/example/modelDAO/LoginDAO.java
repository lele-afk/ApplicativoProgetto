package com.example.modelDAO;

import com.example.model.Utente;
import com.example.modelDAOInterface.LoginDAOI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements LoginDAOI {
    private Connection connection;
    private final PreparedStatement login;

    public LoginDAO(Connection connection) throws SQLException {
        this.connection = connection;
        login= connection.prepareStatement("SELECT * FROM \"User\" WHERE nome= ? AND cognome= ?");
    }

    public Integer login(String nome, String cognome) throws SQLException {
        login.setString(1,nome);
        login.setString(2,cognome);
        ResultSet res = login.executeQuery();
        Utente utente = null;
        while(res.next()){
            utente=  new Utente(res.getString("nome"),res.getString("cognome"),res.getInt("idUser") );
        }
        return utente.getCodiceUnivoco();

    }
}
