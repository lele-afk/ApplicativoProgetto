package com.example.modelDAOInterface;

import java.sql.SQLException;

public interface LoginDAOI {
    Integer login(String nome, String cognome) throws SQLException ;

}
