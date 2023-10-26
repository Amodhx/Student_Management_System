package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.Model.Studentmodel;
import lk.ijse.finalproject02.Model.Usermodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private JFXButton loginbutton;
    @FXML
    private Label passwordwronglable;

    @FXML
    private TextField password;

    @FXML
    private Label signup;

    @FXML
    private TextField username;

    @FXML
    void onbackusername(KeyEvent event) {
        passwordwronglable.setText("");

    }
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
                passwordwronglable.setText("Incorrect login credential");
                username.setText("");
                password.setText("");
            }
        }

    }

    @FXML
    void onsignupclick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
