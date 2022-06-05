package com.example.modelDAOInterface;

import com.example.controller.WrapperCheck;
import com.example.model.Autori;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface AutoriDAOI {
    public ObservableList<WrapperCheck<Autori>> getAutori() throws SQLException;
}
