package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.Model.Usermodel;

import java.io.IOException;
import java.util.ArrayList;

public class loginController {

    @FXML
    private JFXButton loginbutton;

    @FXML
    private TextField password;

    @FXML
    private Label signup;

    @FXML
    private TextField username;
    private  void openUI(String uiName){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(uiName));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onloggingclick(ActionEvent event) {
        String usernameText = username.getText();
        String passwordText = password.getText();

        ArrayList<UserDTO> allUsers = Usermodel.getAllUsers();
        for (UserDTO user : allUsers) {
            if (user.getUserName().equals(usernameText) && user.getPassword().equals(passwordText)){
                openUI("/view/dashboard-form.fxml");
                Stage stage = (Stage) loginbutton.getScene().getWindow();
                stage.close();
            }else {
                System.out.println("not correct");
            }
        }

    }

    @FXML
    void onsignupclick(MouseEvent event) {

    }

}
