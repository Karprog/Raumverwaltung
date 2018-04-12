package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button btnHardwareAnlegen;
    @FXML
    private Button btnRaumAnlegen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void startRaumAnlegen() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/raum.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 600));
        stage.show();
    }

    @FXML
    private void startHardwareAnlegen() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/hardware.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 600));
        stage.show();
    }
}
