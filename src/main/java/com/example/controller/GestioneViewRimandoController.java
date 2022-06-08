package com.example.controller;

import com.example.connection.DbConnection;
import com.example.model.Autori;
import com.example.model.RifBibliografico;
import com.example.modelDAO.RifAutoriDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestioneViewRimandoController implements Initializable {

    @FXML
    private Button back;


    @FXML
    private TableView<RifBibliografico> tableViewRimando;

    @FXML
    private TableColumn<RifBibliografico, String> tipo;

    @FXML
    private TableColumn<RifBibliografico, String> titolo;

    @FXML
    private TableColumn autori;

    @FXML
    private TableColumn descrizione;

    @FXML
    private TableColumn<RifBibliografico, String> data;

    @FXML
    private TableColumn<RifBibliografico, String> indirizzoURL;


    @FXML
    private TableColumn<RifBibliografico, String> doi;

    private ObservableList<Autori> rifXAutori = FXCollections.observableArrayList();

    public void setRifs(ObservableList<RifBibliografico> rifs) {
        System.out.println(rifs);

        tableViewRimando.setItems(rifs);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tipo.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("tipo"));
        titolo.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("titolo"));
        autori.setCellFactory(ActionButtonTableCell.<RifBibliografico>forTableColumn("Autori",(RifBibliografico rif) -> {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/gestione-autori.fxml"
                    )
            );
            Stage stage = new Stage();
            String contentAutori="";
            try {
                RifAutoriDAO rifAut = new RifAutoriDAO(DbConnection.getInstance().getConnection());
                rifXAutori = rifAut.getRifXAutori(rif.getId());
                for(Integer i=0; i<rifXAutori.size();i++){
                    contentAutori =contentAutori+ "Autore "+(i+1)+" "+rifXAutori.get(i).getNome()+" "+rifXAutori.get(i).getCognome()+"\n";
                }


                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            GestioneAutoriController controller = loader.getController();

            controller.setRif(contentAutori);
            stage.show();
            return rif;
        }));

        data.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("data"));
        indirizzoURL.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("URL"));
        doi.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("DOI"));

        descrizione.setCellFactory(ActionButtonTableCell.<RifBibliografico>forTableColumn("Info",(RifBibliografico rif) -> {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/gestione-descrizione.fxml"
                    )
            );
            Stage stage = new Stage();
            try {
                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            GestioneDescrizioneController controller = loader.getController();
            controller.setLabel(rif.getDescrizione());
            stage.show();
            return rif;
        }));

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
