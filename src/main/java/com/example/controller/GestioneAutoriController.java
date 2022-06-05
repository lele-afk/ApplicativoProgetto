package com.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GestioneAutoriController implements Initializable {
    @FXML
    private Button back;

    @FXML
    private Label contentAutori;

    private Integer rif;

    public void setRif(String content) {

        contentAutori.setText(content);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();


                Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                currentScene.close();

            }
        });
    }
}
