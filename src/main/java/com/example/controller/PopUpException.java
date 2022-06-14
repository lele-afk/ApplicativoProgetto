package com.example.controller;

import javafx.scene.control.Alert;

public class PopUpException extends Exception{
    private static final long serialVersionUID = 1885653349235601203L;



    public PopUpException(String message) {
        super(message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore sulla chiamata");
        alert.setContentText(message);
        alert.showAndWait();
    }


}
