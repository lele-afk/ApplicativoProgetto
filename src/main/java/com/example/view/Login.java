package com.example.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Login {
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login applicazione");

        URL urlLogin = getClass().getResource("/fxml/login.fxml");
        FXMLLoader loader = new FXMLLoader(urlLogin);
        Pane root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();


    }
}
