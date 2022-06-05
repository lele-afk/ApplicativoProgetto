package com.example.controller;

import com.example.view.Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceltaController implements Initializable {
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                Home home= new Home();

                Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                currentScene.close();
                /*stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(gestione);
                System.out.println(scene);
                System.out.println(stage);
                stage.setScene(scene);
                stage.show();*/
                try {
                    home.start(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
