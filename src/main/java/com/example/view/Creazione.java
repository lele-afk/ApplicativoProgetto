package com.example.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Creazione {
    public void start(Stage stage) throws IOException {
        stage.setTitle("Creazione Riferimento");

        URL urlDescrizione = getClass().getResource("/fxml/gestione-creazione.fxml");
        FXMLLoader loader = new FXMLLoader(urlDescrizione);
        Pane root = loader.load();



        stage.setScene(new Scene(root));
        stage.show();


    }
}
