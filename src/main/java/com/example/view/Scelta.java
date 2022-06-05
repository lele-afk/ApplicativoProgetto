package com.example.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Scelta {
    public void start(Stage stage) throws IOException {
        stage.setTitle("Scelta bibliografia");

        URL urlGestione = getClass().getResource("/fxml/scelta.fxml");
        FXMLLoader loader = new FXMLLoader(urlGestione);
        Pane root = loader.load();


        stage.setScene(new Scene(root));
        stage.show();
    }
}
