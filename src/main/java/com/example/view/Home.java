package com.example.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Home
{


    public void start(Stage stage) throws IOException {
        stage.setTitle("Home");

        URL urlHome = getClass().getResource("/fxml/home.fxml");
        FXMLLoader loader = new FXMLLoader(urlHome);
        Pane root = loader.load();


        stage.setScene(new Scene(root));
        stage.show();


    }

}