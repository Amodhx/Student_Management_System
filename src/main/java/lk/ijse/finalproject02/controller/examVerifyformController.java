package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.Model.Usermodel;

import java.io.IOException;
import java.util.ArrayList;

public class examVerifyformController {

    @FXML
    private TextField adminname;

    @FXML
    private PasswordField adminpassword;

    @FXML
    private JFXButton okButton;

    @FXML
    private AnchorPane pane;

    @FXML
    void onOkClick(ActionEvent event) {
        String adminnameText = adminname.getText();
        String adminpasswordText = adminpassword.getText();
        ArrayList<UserDTO> allUsers = Usermodel.getAllUsers();
        if (allUsers.get(0).getUserName().equals(adminnameText) && allUsers.get(0).getPassword().equals(adminpasswordText)){
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/view/setting-form.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        }
    }

}
