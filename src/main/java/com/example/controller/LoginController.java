package com.example.controller;

import com.example.connection.DbConnection;
import com.example.modelDAO.LoginDAO;
import com.example.view.Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private TextField cognome;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    LoginDAO loginDAO = new LoginDAO(DbConnection.getInstance().getConnection());
                    Integer idUser = loginDAO.login(username.getText(),cognome.getText());
                    Stage stage = new Stage();
                    Home home= new Home();

                    Stage currentScene = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    currentScene.close();
                    try {
                        home.start(stage);
                    } catch (IOException e) {
                        PopUpException exception = new PopUpException(e.getMessage());
                    }

                }catch (Exception ex){
                    PopUpException exception = new PopUpException("Nome o cognome non validi");
                }

            }
        });
    }
}
