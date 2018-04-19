package controller;

import database.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Raum;

import java.net.URL;
import java.util.ResourceBundle;


public class RaumController implements Initializable {

    @FXML
    private TextField txtBezeichnung;
    @FXML
    private TextField txtTyp;
    @FXML
    private TextField txtAnzahlArbeitsplaetze;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnEnde;
    @FXML
    private ListView<Raum> listView;

    private ObservableList<Raum> raumList = FXCollections.observableArrayList();
    private Dao dao = new Dao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setItems(raumList);
        listView.refresh();
    }

    private Raum getRaumDataFromView(){
        Raum raum = new Raum();
        raum.setBezeichnung(txtBezeichnung.getText());
        raum.setTyp(txtTyp.getText());
        raum.setAnzahlArbeitsplaetze(Integer.parseInt(txtAnzahlArbeitsplaetze.getText()));
        return raum;
    }

    private void readRaumFromDb(){
        raumList.addAll(dao.getRaumList());
    }

    @FXML
    private void writeToDb(){
        //   System.out.println(getDataFromView());
    }

    private void readFromDb(){

    }

    private void writeDataToList(Raum raum){
        raumList.add(raum);
        listView.refresh();
    }

    @FXML
    void handleBtnSaveAction(ActionEvent e) {
        String bezeichnung = txtBezeichnung.getText();
        String typ = txtTyp.getText();
        int anzahlArbeitsplaetze = Integer.parseInt(txtAnzahlArbeitsplaetze.getText());

        Raum raum = new Raum(bezeichnung, typ, anzahlArbeitsplaetze);
        dao.saveRaum(raum);

        writeDataToList(raum);
    }

    @FXML
    private void closeWindow(){
        System.exit(0);
    }
}
