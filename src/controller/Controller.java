package controller;

import model.Hardware;
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

public class Controller implements Initializable {
    @FXML
    private ListView<Raum> listView;
    @FXML
    private TextField txtRaumId;
    @FXML
    private TextField txtHardwareId;
    @FXML
    private TextField txtType;
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

    private void writeDataToView(){
        /*txtHardwareId.setText();
        txtHersteller.setText();
        txtModell.setText();
        txtSeriennummer.setText();
        txtInventarnummer.setText();
        txtType.setText();
        txtStatus.setText();
        txtImagepfad.setText();
        txtBetriebsmittel.setText();*/
    }

    private Hardware getHardwareDataFromView(){
        if(isRechner){
            Rechner rechner = new Rechner();
            rechner.setHersteller(txtHersteller.getText());
            rechner.setId(Integer.parseInt(txtHardwareId.getText()));
            rechner.setInventarnummer(txtInventarnummer.getText());
            rechner.setSeriennummer(txtSeriennummer.getText());
            rechner.setModell(txtModell.getText());
            rechner.setTyp(txtType.getText());
            rechner.setStatus(Integer.parseInt(txtStatus.getText()));
            rechner.setImagepfad(txtImagepfad.getText());
            return  rechner;
        }
        else{
            Drucker drucker = new Drucker();
            drucker.setId(Integer.parseInt(txtHardwareId.getText()));
            drucker.setHersteller(txtHersteller.getText());
            drucker.setInventarnummer(txtInventarnummer.getText());
            drucker.setSeriennummer(txtSeriennummer.getText());
            drucker.setModell(txtModell.getText());
            drucker.setTyp(txtType.getText());
            drucker.setStatus(Integer.parseInt(txtStatus.getText()));
            drucker.setBetriebsmittel(txtBetriebsmittel.getText());
            return  drucker;
        }
    }


    private Raum getRaumDataFromView() {
        /*Raum raum = new Raum();
        raum.*/

        return null;
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
        raum.setHardwareArrayList(hardwareList);
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
    private void closeWindow(){
        System.exit(0);
    }
}
