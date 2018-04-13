package controller;

import database.Dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Raum;


public class RaumController {

    @FXML
    private TextField txtBezeichnung;
    @FXML
    private TextField txtTyp;
    @FXML
    private TextField txtAnzahlArbeitsplaetze;
    @FXML
    private Button btnSave;

    private Dao dao = new Dao();

    void handleBtnSaveAction(ActionEvent e) {
        String bezeichnung = txtBezeichnung.getText();
        String typ = txtTyp.getText();
        int anzahlArbeitsplaetze = Integer.parseInt(txtAnzahlArbeitsplaetze.getText());

        Raum raum = new Raum(bezeichnung, typ, anzahlArbeitsplaetze);
        dao.saveRaum(raum);
    }
}
