package com.example.controller;

import com.example.connection.DbConnection;
import com.example.model.Autori;
import com.example.model.RifBibliografico;
import com.example.modelDAO.RifAutoriDAO;
import com.example.modelDAO.RifBiblioDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class GestioneRimandoController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button submit;

    @FXML
    private TableView<RifBibliografico> tableRimando;

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
    private TableColumn select;

    @FXML
    private TableColumn<RifBibliografico, String> doi;

    private RifBibliografico rif;
    private ObservableList<RifBibliografico> rifs = FXCollections.observableArrayList();
    private ObservableList<Autori> rifXAutori = FXCollections.observableArrayList();
    private ObservableList<RifBibliografico> rifXrif = FXCollections.observableArrayList();


    //prendo il rif per fare un filtro sulla tabella in modo da escludere se stesso

    public void setRif(RifBibliografico rif) {
        this.rif = rif;
    }

    public void setRifs(ObservableList<RifBibliografico> rifs) {
        this.rifs = rifs;
        tableRimando.setItems(rifs);
    }
    public void setRifxRif(ObservableList<RifBibliografico> rifXrif){
        this.rifXrif = rifXrif;
    }

    public void setAutoriRif(ObservableList<Autori> rifXAutori){
        this.rifXAutori = rifXAutori;
    }

    private ArrayList<Integer> idRif = new ArrayList<>();
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
                PopUpException popUp = new PopUpException(e.getMessage());
            }
            GestioneDescrizioneController controller = loader.getController();
            controller.setLabel(rif.getDescrizione());
            stage.show();
            return rif;
        }));


        select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RifBibliografico, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<RifBibliografico, CheckBox> arg0) {
                RifBibliografico rif = arg0.getValue();

                CheckBox checkBox = new CheckBox();
                AtomicBoolean checked= new AtomicBoolean(false);


                rifXrif.forEach(rifBibliografico -> {
                    if(rifBibliografico.getIdRimando().equals(rif.getId())){
                        checked.set(true);
                        checkBox.selectedProperty().setValue(checked.get());

                    }
                });
                if(checkBox.selectedProperty().getValue().booleanValue()){
                    if(!idRif.contains(rif.getId())){
                        setIdRif(rif.getId());
                    }
                }
                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                                        Boolean old_val, Boolean new_val) {

                        rif.setSelected(new_val);
                        if(new_val){
                            idRif.add(rif.getId());
                        }else{
                            idRif.remove(rif.getId());
                        }
                    }
                });

                return new SimpleObjectProperty<CheckBox>(checkBox);

            }

        });





        back.setOnAction(event ->{
            Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
            currentScene.close();
        });

        submit.setOnAction(event -> {
            try{
                DbConnection db = DbConnection.getInstance();
                RifBiblioDAO rif = new RifBiblioDAO(DbConnection.getInstance().getConnection());
                rifXrif.forEach(rifBibliografico   ->{
                    try {
                        rif.deleteRimando(rifBibliografico.getIdRimando());
                    } catch (SQLException e) {
                        PopUpException popUp = new PopUpException(e.getMessage());
                    }
                });

                idRif.forEach(rimando -> {
                    try {

                        rif.postRifxRif(this.rif.getId(),rimando);

                    } catch (SQLException e) {
                        Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                        currentScene.close();
                        PopUpException popUp = new PopUpException(e.getMessage());
                    }
                });

                Stage currentScene = (Stage)((Node)event.getSource()).getScene().getWindow();
                currentScene.close();
            }catch (Exception err){

                PopUpException popUp = new PopUpException(err.getMessage());
            }


        });
    }

    public void setIdRif(Integer id) {
        this.idRif.add(id) ;
    }
}
