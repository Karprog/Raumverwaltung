package database.implementation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

abstract class Controller {

    @FXML
    private Button btnEnde;

    @FXML
    public void handleButtonEnde(ActionEvent actionEvent) {

        Stage stage = (Stage) this.btnEnde.getScene().getWindow();
        stage.close();
    }

    abstract void handleBtnSaveAction(ActionEvent e);
}
