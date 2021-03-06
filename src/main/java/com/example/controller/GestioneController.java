package com.example.controller;

import com.example.connection.DbConnection;
import com.example.model.Autori;
import com.example.model.RifBibliografico;
import com.example.model.Tipologia;
import com.example.model.Utente;
import com.example.modelDAO.AutoriDAO;
import com.example.modelDAO.RifAutoriDAO;
import com.example.modelDAO.RifBiblioDAO;
import com.example.modelDAO.TipologiaDAO;
import com.example.view.Home;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestioneController implements Initializable {
    @FXML
    private Button back;

    @FXML
    private Button createRif;

    @FXML
    private TableView<RifBibliografico> tableGestione = new TableView<>();

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
    private TableColumn rimando;

    @FXML
    private TableColumn<RifBibliografico, String> doi;

    @FXML
    private TableColumn select;

    @FXML
    private TableColumn delete;

    @FXML
    private TextField filter;

    @FXML
    private Button search;

    private ObservableList<RifBibliografico> listRif;
    private ObservableList<RifBibliografico> listRifxRif;
    private ObservableList<RifBibliografico> listSetRimandi;
    private ObservableList<WrapperCheck<Autori>> listaAutore;
    private ObservableList<Autori> rifXAutori;
    private ObservableList<Tipologia> listaTipologia;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DbConnection db = DbConnection.getInstance();
            Utente utente = new Utente();
            RifBiblioDAO rifBiblioDAO = new RifBiblioDAO(DbConnection.getInstance().getConnection());
            AutoriDAO aut = new AutoriDAO(DbConnection.getInstance().getConnection());
            TipologiaDAO tipologiaDAO = new TipologiaDAO(DbConnection.getInstance().getConnection());
            listaTipologia = tipologiaDAO.getTipologia();
            listRif = rifBiblioDAO.getRif(utente.getCodiceUnivoco());
            listaAutore = aut.getAutori();

        } catch (Exception err) {
            PopUpException popUp = new PopUpException(err.getMessage());
        }


       /* Image img = new Image("img/kisspng-computer-icons-magnifying-glass-magnifier-icon-pack-5ada52d6937326.175116051524257494604.png");
        ImageView view = new ImageView(img);
        search.setGraphic(view);*/
        search.setOnMouseClicked(event -> {
            if (filter.getText() == "") {
                search.setText("Cerca");
            } else {
                search.setText("Reset");


            }
        });
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    RifBiblioDAO rifBiblioDAO = new RifBiblioDAO(DbConnection.getInstance().getConnection());
                    Utente utente = new Utente();
                    if (filter.getText() == "") {
                        tableGestione.setItems(rifBiblioDAO.getRif(utente.getCodiceUnivoco()));
                    } else {
                        tableGestione.setItems(rifBiblioDAO.getAdvanceRif(utente.getCodiceUnivoco(), filter.getText()));

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        tipo.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("tipo"));
        titolo.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("titolo"));
        autori.setCellFactory(ActionButtonTableCell.<RifBibliografico>forTableColumn("Autori", (RifBibliografico rif) -> {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/gestione-autori.fxml"
                    )
            );
            Stage stage = new Stage();
            String contentAutori = "";
            try {
                RifAutoriDAO rifAut = new RifAutoriDAO(DbConnection.getInstance().getConnection());
                rifXAutori = rifAut.getRifXAutori(rif.getId());
                for (Integer i = 0; i < rifXAutori.size(); i++) {
                    contentAutori = contentAutori + "Autore " + (i + 1) + " " + rifXAutori.get(i).getNome() + " " + rifXAutori.get(i).getCognome() + "\n";
                }


                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException | SQLException e) {
                PopUpException popUp = new PopUpException(e.getMessage());

            }
            GestioneAutoriController controller = loader.getController();

            controller.setRif(contentAutori);
            stage.show();
            return rif;
        }));

        data.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("data"));
        indirizzoURL.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("URL"));
        doi.setCellValueFactory(new PropertyValueFactory<RifBibliografico, String>("DOI"));

        descrizione.setCellFactory(ActionButtonTableCell.<RifBibliografico>forTableColumn("Info", (RifBibliografico rif) -> {
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
                PopUpException popUp = new PopUpException(e.getMessage());
            }
            GestioneDescrizioneController controller = loader.getController();
            controller.setLabel(rif.getDescrizione());
            stage.show();
            return rif;
        }));

        createRif.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/fxml/gestione-creazione.fxml"
                        )
                );
                Stage stage = new Stage();
                try {
                    stage.setScene(
                            new Scene(loader.load())
                    );
                } catch (IOException e) {
                    PopUpException popUp = new PopUpException(e.getMessage());

                }
                GestioneCreazioneController controller = loader.getController();
                controller.setTipo(listaTipologia);
                controller.setAutori(listaAutore);
                controller.setTable(tableGestione);
                stage.show();

            }
        });

        rimando.setCellFactory(ActionButtonTableCell.<RifBibliografico>forTableColumn("rimandi", (RifBibliografico rif) -> {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/gestione-viewRimando.fxml"
                    )
            );
            Stage stage = new Stage();
            try {

                RifBiblioDAO rifBiblioDAO = new RifBiblioDAO(DbConnection.getInstance().getConnection());

                //ELIMINARE DUPLICATI IN TABELLA E FAR FUNZIONARE LA CHECKBOX
                listRifxRif = rifBiblioDAO.getRifxRif(rif.getId());
                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException | SQLException e) {
                PopUpException popUp = new PopUpException(e.getMessage());

            }
            GestioneViewRimandoController controller = loader.getController();
            controller.setRifs(listRifxRif);
            stage.show();
            return rif;
        }));


        select.setCellFactory(ActionButtonTableCell.<RifBibliografico>forTableColumn("seleziona rimando", (RifBibliografico rif) -> {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/gestione-rimando.fxml"
                    )
            );
            Stage stage = new Stage();
            try {
                RifBiblioDAO rifBiblioDAO = new RifBiblioDAO(DbConnection.getInstance().getConnection());
                listSetRimandi = rifBiblioDAO.setRimandi(rif.getId());
                listRifxRif = rifBiblioDAO.getRifxRif(rif.getId());
                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException | SQLException e) {
                PopUpException popUp = new PopUpException(e.getMessage());

            }
            GestioneRimandoController controller = loader.getController();
            controller.setRif(rif);
            controller.setRifs(listSetRimandi);
            controller.setRifxRif(listRifxRif);
            controller.setAutoriRif(rifXAutori);
            stage.show();
            return rif;
        }));

        delete.setCellFactory(ActionButtonTableCell.<RifBibliografico>forTableColumn("Elimina Riferimento", (RifBibliografico rif) -> {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/conferma-eliminazione.fxml"
                    )
            );
            Stage stage = new Stage();
            try {
                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException e) {
                PopUpException popUp = new PopUpException(e.getMessage());

            }
            EliminazioneController controller = loader.getController();
            controller.setId(rif.getId());
            controller.setTable(tableGestione);
            stage.show();
            return rif;
        }));

        tableGestione.setItems(listRif);


        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                Home home = new Home();

                Stage currentScene = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
                    PopUpException popUp = new PopUpException(e.getMessage());

                }
            }
        });
    }


}
