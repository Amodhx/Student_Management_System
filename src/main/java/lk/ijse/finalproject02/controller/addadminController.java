package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class addadminController implements Initializable {

    @FXML
    private JFXButton addaccountButton;
    @FXML
    private ImageView closeicon;

    @FXML
    private JFXButton logoutButton;

    @FXML
    void onLogOutClick(ActionEvent event) {

    }
    @FXML
    void oncloseClick(MouseEvent event) {
        Stage stage = (Stage) addaccountButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onaddAccountButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardController.button = logoutButton;
    }
}
