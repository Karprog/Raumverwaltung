package controller;

import database.Dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Drucker;
import model.Rechner;

public class HardwareController_2 extends Controller {

    @FXML
    private TextField txtRaumId;
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

    private Dao reparaturDao = new Dao();

    @FXML
    void handleBtnSaveAction(ActionEvent e) {
        String typ = txtTyp.getText();
        String seriennummer = txtSeriennummer.getText();
        String inventarnummer = txtInventarnummer.getText();
        String hersteller = txtHersteller.getText();
        String modell = txtModell.getText();
        int status = Integer.parseInt(txtStatus.getText());
        String rechnerInstance = rbRechner.getText();
        String druckerInstance = rbDrucker.getText();
        String raumid = txtRaumId.getText();

        if (!"".equals(rechnerInstance)) {
            String imagepfad = txtImagepfad.getText();
            Rechner rechner = new Rechner(typ, seriennummer, inventarnummer,
                    hersteller, modell, status, imagepfad, raumid);
            reparaturDao.saveHardware(rechner);
        }
        if (!"".equals(druckerInstance)) {
            String betriebsmittel = txtBetriebsmittel.getText();
            Drucker drucker = new Drucker(typ, seriennummer, inventarnummer, hersteller, modell, status,
                    betriebsmittel, raumid);
            reparaturDao.saveHardware(drucker);
        }
    }
}
