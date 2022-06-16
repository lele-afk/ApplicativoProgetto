package com.example.controller;

import com.example.connection.DbConnection;
import com.example.modelDAO.RifBiblioDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EliminazioneController implements Initializable {
    @FXML
    private Button back;

    @FXML
    private Button delete;

    private Integer id;

    public void setId(Integer idRif){
        this.id = idRif;
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

        delete.setOnAction(event -> {
            try {
                RifBiblioDAO rifBiblioDAO = new RifBiblioDAO(DbConnection.getInstance().getConnection());
                rifBiblioDAO.deleteRifXAutore(id);
                rifBiblioDAO.deleteRifXRimando(id);
                rifBiblioDAO.deleteRimando(id);
                rifBiblioDAO.deleteRiferimento(id);

                Stage stage = new Stage();
                Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                currentScene.close();
            } catch (SQLException e) {

                Stage stage = new Stage();
                Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                currentScene.close();
                PopUpException popUp = new PopUpException(e.getMessage());

            }
        });

    }
}
