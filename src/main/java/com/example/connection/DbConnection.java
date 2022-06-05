package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection
{
    private static DbConnection instance;
    private Connection connection = null;
    private final String USERNAME = "postgres";
    private final String PASSWORD = "root";
    private final String IP = "localhost";
    private final String PORT = "5432";
    private final String database ="Traccia2[OOBD1]";
    private String url = "jdbc:postgresql://"+IP+":"+PORT+"/"+database;

    public DbConnection() throws SQLException {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);

        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static DbConnection getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new DbConnection();
        }
        else if (instance.getConnection().isClosed())
        {
            instance = new DbConnection();
        }

        return instance;
    }
}
