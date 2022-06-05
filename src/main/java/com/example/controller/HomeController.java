package com.example.controller;

import com.example.view.Gestione;
import com.example.view.Scelta;
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

public class HomeController implements Initializable {
    @FXML
    private Button choose;
    @FXML
    private Button visualize;

   /* private Stage stage;
    private Parent root;
    private Scene scene;*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                Scelta scelta = new Scelta();


                Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                currentScene.close();
                /*stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(gestione);
                System.out.println(scene);
                System.out.println(stage);
                stage.setScene(scene);
                stage.show();*/
                try {
                    scelta.start(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        visualize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Stage stage = new Stage();
                Gestione gestione = new Gestione();

                Stage currentScene = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                currentScene.close();
                /*stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(gestione);
                System.out.println(scene);
                System.out.println(stage);
                stage.setScene(scene);
                stage.show();*/
                try {
                    gestione.start(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    //gestisce le azioni dell'utente dall'interfaccia
   // Connection connessione = //classe che creo in connection

}
