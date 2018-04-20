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
import java.util.List;
import java.util.ResourceBundle;

public class HardwareController implements Initializable {
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
    private List<Hardware> hardwareList = new ArrayList<>();
    private Dao reparaturDao = new Dao();
    private boolean isRechner = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readRaumFromDb();
        readHardwareFromDB();
        mapHardwareToRaum();
        toggleRadiobuttonHardware();

        listView.setItems(raumList);
        listView.refresh();
    }

    private void readRaumFromDb(){
        raumList.addAll(reparaturDao.getRaumList());
    }

    private void readHardwareFromDB() {
        hardwareList.addAll(reparaturDao.getHardware());
    }

    private void mapHardwareToRaum() {
        for ( Hardware hardware : hardwareList) {
            for ( Raum raum : raumList) {
                // Raum.Raumid und Hardware.Rumid müssen beide entweder Zahl oder String sein.
                if( hardware.getRaumid().equals(raum.getRaumid() + "")) {
                    raum.getHardwareliste().add(hardware);
                }
            }
        }
    }

    private void refreshList(){
        raumList.clear();
        hardwareList.clear();

        readRaumFromDb();
        readHardwareFromDB();
        mapHardwareToRaum();

        listView.refresh();
    }

    private void writeDataToList(int raumId, Hardware hardware){
        for(Raum raum : raumList){
            if(raum.getRaumid() == raumId){
                raum.getHardwareliste().add(hardware);
            }
        }

        listView.refresh();
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

            refreshList();
        }
        if (druckerInstance) {
            String betriebsmittel = txtBetriebsmittel.getText();
            Drucker drucker = new Drucker(typ, seriennummer, inventarnummer, hersteller, modell, status,
                    betriebsmittel, raumid);
            reparaturDao.saveHardware(drucker);

            refreshList();
        }
    }

    /*void handleEditHardwareAction (ActionEvent e) {

        if (rbRechner.isSelected()) {
            Rechner rechner = null;
            //Wenn Eintrag noch nicht existiert, lege neues Hardwareelement an
            if ("".equals(txtHardwareId)) {
                rechner = new Rechner();
                handleBtnSaveAction(e);
            } else {
                //Wenn Eintrag schon vorhanden, ändere bestehenden Eintrag
                rechner = (Rechner) hardwareList.get(listView.getSelectionModel().getSelectedIndex());
                rechner.setId(Integer.parseInt(txtHardwareId.getText()));
                rechner.setTyp(txtTyp.getText());
                rechner.setHersteller(txtHersteller.getText());
                rechner.setInventarnummer(txtInventarnummer.getText());
                rechner.setSeriennummer(txtSeriennummer.getText());
                rechner.setModell(txtModell.getText());
                rechner.setImagepfad(txtImagepfad.getText());
                rechner.setStatus(Integer.parseInt(txtStatus.getText()));
            }
        }
    }*/

    @FXML
    private void closeWindow(){
        System.exit(0);
    }
}
