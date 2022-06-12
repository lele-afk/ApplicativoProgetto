package com.example.controller;

import com.example.connection.DbConnection;
import com.example.modelDAO.TipologiaDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TipologiaController implements Initializable {
    @FXML
    private Button back;

    @FXML
    private Button creazione;

    @FXML
    private TextField tipo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        back.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();

                Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                currentScene.close();
            }
        });

        creazione.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                try {
                    DbConnection db = DbConnection.getInstance();
                    TipologiaDAO tipologiaDAO = new TipologiaDAO(DbConnection.getInstance().getConnection());

                    Integer id = tipologiaDAO.createTipologia(tipo.getText());
                    System.out.println(id);
                    Stage stage = new Stage();
                    Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                    currentScene.close();
                }catch (Exception err){

                    System.out.println("err>>"+err.getMessage());
                }
            }
        });
    }
}
