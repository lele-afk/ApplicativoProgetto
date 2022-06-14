package com.example.controller;

import com.example.connection.DbConnection;
import com.example.model.Autori;
import com.example.model.Tipologia;
import com.example.modelDAO.RifAutoriDAO;
import com.example.modelDAO.RifBiblioDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestioneCreazioneController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button creazione;

    @FXML
    private TextField titolo;

    @FXML
    private TextField url;

    @FXML
    private TextField doi;

    @FXML
    private ComboBox<Tipologia> tipo;

    @FXML
    private DatePicker dataCreazione;

    @FXML
    private TextArea descrizione;

    @FXML
    private ComboBox<WrapperCheck<Autori>> autori;

    private ObservableList<Autori> listaAutore;
    private ObservableList<Tipologia> listaTipologia;

    public void setAutori(ObservableList<WrapperCheck<Autori>> autori){

        this.autori.setItems(autori);

    }
    public void setTipo(ObservableList<Tipologia> tipologie){

        this.tipo.setItems(tipologie);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
// TO-DO ->>INSERIRE IN TIPO UNA SELECT CON I DATI DALLA GET(GET DA FARE)

        tipo.setCellFactory(param -> {
            ListCell<Tipologia> cell = new ListCell<>(){
                protected void updateItem(Tipologia item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item.getNome());
                    }
                }
            };
            return cell;
        });

        tipo.setButtonCell(new ListCell<Tipologia>() {
            @Override
            protected void updateItem(Tipologia t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) {
                    setText(t.getNome());
                } else {
                    setText(null);
                }

            }
        });

        autori.setCellFactory( c -> {
            ListCell<WrapperCheck<Autori>> cell = new ListCell<>(){
                @Override
                protected void updateItem(WrapperCheck<Autori> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        final CheckBox cb = new CheckBox(item.getItem().getNome()+" "+item.getItem().getCognome());
                        cb.selectedProperty().bind(item.checkProperty());
                        setGraphic(cb);

                    }
                }
            };

            cell.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
                cell.getItem().checkProperty().set(!cell.getItem().checkProperty().get());
                StringBuilder sb = new StringBuilder();
                autori.getItems().filtered( f-> f!=null).filtered( f-> f.getCheck()).forEach( p -> {
                    sb.append("; "+p.getItem().getNome()+" "+p.getItem().getCognome());
                });
                final String string = sb.toString();

                autori.setPromptText(string.substring(Integer.min(2, string.length())));
            });

            return cell;
        });;

        back.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();

                autori.getItems().forEach( item ->
                        item.setCheck(false)
                );

                Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                currentScene.close();
            }
        });

        creazione.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                try {
                    DbConnection db = DbConnection.getInstance();
                    RifBiblioDAO rif = new RifBiblioDAO(DbConnection.getInstance().getConnection());
                    RifAutoriDAO rifAutoriDAO = new RifAutoriDAO(DbConnection.getInstance().getConnection());
                    Integer id = rif.postRif(titolo.getText(), url.getText(), doi.getText() == ""?null: doi.getText(), descrizione.getText(), String.valueOf(dataCreazione.getValue()),tipo.getValue().getIdTipologia());
                    autori.getItems().filtered( f -> f.getCheck()).forEach( item ->
                    {
                        try {

                            rifAutoriDAO.postRifXAutori(item.getItem().getCodiceUnivoco(),id);
                            item.setCheck(false);
                        } catch (SQLException e) {
                            PopUpException popUp = new PopUpException(e.getMessage());

                        }
                    }
                    );

                    Stage stage = new Stage();
                    Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                    currentScene.close();
                }catch (Exception err){

                    PopUpException popUp = new PopUpException(err.getMessage());

                }
            }
        });
    }
}
