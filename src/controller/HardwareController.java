
package controller;

import database.Dao;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import model.Drucker;
import model.Hardware;
import model.Raum;
import model.Rechner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HardwareController implements Initializable {
    @FXML
    public Button btnHinzufuegen;
    @FXML
    private ListView<Raum> listView;
    @FXML
    private TextField txtRaumId;
    @FXML
    private TextField txtHardwareId;
    @FXML
    private TextField txtTyp;
    @FXML
    private TextField txtSeriennummer;
    @FXML
    private TextField txtInventarnummer;
    @FXML
    private TextField txtHersteller;
    @FXML
    private TextField txtModell;
    @FXML
    private TextField txtStatus;
    @FXML
    private TextField txtImagepfad;
    @FXML
    private TextField txtBetriebsmittel;
    @FXML
    private RadioButton rbDrucker;
    @FXML
    private RadioButton rbRechner;

    private ObservableList<Raum> raumList = FXCollections.observableArrayList();
    private Dao reparaturDao = new Dao();
    private boolean isRechner = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Einträge werden bei der Initialisierung aus der Datenbank geholt und in die Liste geschrieben
        fillList();

        toggleRadiobuttonHardware();

        listView.setItems(raumList);
        listView.refresh();
    }

    @FXML
    private void writeToDb(){
        //   System.out.println(getDataFromView());
    }

    private void readFromDb(){

    }

    private void writeDataToList(int raumId, Hardware hardware){
        for(Raum raum : raumList){
            if(raum.getRaumid() == raumId){
                raum.getHardwareliste().add(hardware);
            }
        }

        listView.refresh();
    }

    private void fillList() {
        // Werte zum testen
        Rechner rechner = new Rechner();
        rechner.setHersteller("test");
        rechner.setImagepfad("/test/test/test");
        rechner.setModell("testModell");
        Raum raum = new Raum();
        raum.setRaumid(123);
        ArrayList<Hardware> hardwareList = new ArrayList<>();
        hardwareList.add(rechner);
        hardwareList.add(rechner);
        hardwareList.add(rechner);
        raum.setHardwareliste(hardwareList);
        raumList.add(raum);
        raumList.add(raum);
    }

    @FXML
    private void toggleRadiobuttonHardware(){
        if(rbRechner.isSelected()){
            txtImagepfad.setDisable(false);
            txtBetriebsmittel.setDisable(true);
            isRechner = true;
        }
        else {
            txtImagepfad.setDisable(true);
            txtBetriebsmittel.setDisable(false);
            isRechner = false;
        }
    }

    @FXML
    void handleBtnSaveAction(ActionEvent e) {
        String typ = txtTyp.getText();
        String seriennummer = txtSeriennummer.getText();
        String inventarnummer = txtInventarnummer.getText();
        String hersteller = txtHersteller.getText();
        String modell = txtModell.getText();
        int status = Integer.parseInt(txtStatus.getText());
        boolean rechnerInstance = rbRechner.isSelected();
        boolean druckerInstance = rbDrucker.isSelected();
        String raumid = txtRaumId.getText();

        if (rechnerInstance) {
            String imagepfad = txtImagepfad.getText();
            Rechner rechner = new Rechner(typ, seriennummer, inventarnummer,
                    hersteller, modell, status, imagepfad, raumid);
            reparaturDao.saveHardware(rechner);

            writeDataToList(Integer.parseInt(raumid), rechner);
        }
        if (druckerInstance) {
            String betriebsmittel = txtBetriebsmittel.getText();
            Drucker drucker = new Drucker(typ, seriennummer, inventarnummer, hersteller, modell, status,
                    betriebsmittel, raumid);
            reparaturDao.saveHardware(drucker);

            writeDataToList(Integer.parseInt(raumid), drucker);
        }
    }

    @FXML
    private void closeWindow(){
        System.exit(0);
    }
}