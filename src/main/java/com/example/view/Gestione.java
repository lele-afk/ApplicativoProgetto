package com.example.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Gestione  {
    public void start(Stage stage) throws IOException {
        stage.setTitle("Gestone bibliografia");

        URL urlGestione = getClass().getResource("/fxml/gestione.fxml");
        FXMLLoader loader = new FXMLLoader(urlGestione);
        Pane root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();


    }

    /*@Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }*/
}
