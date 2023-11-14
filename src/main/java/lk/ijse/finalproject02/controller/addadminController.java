package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addadminController implements Initializable {

    @FXML
    private JFXButton addaccountButton;
    @FXML
    private ImageView closeicon;

    @FXML
    private JFXButton logoutButton;

    public static JFXButton button;

    @FXML
    void onLogOutClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/login-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        Stage stage1 = (Stage) button.getScene().getWindow();
        stage1.close();
        Stage stage2 = (Stage) logoutButton.getScene().getWindow();
        stage2.close();
    }
    @FXML
    void oncloseClick(MouseEvent event) {
        Stage stage = (Stage) addaccountButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onaddAccountButton(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/adminVerify-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        Stage stage1 = (Stage) addaccountButton.getScene().getWindow();
        stage1.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardController.button = logoutButton;
    }
}
