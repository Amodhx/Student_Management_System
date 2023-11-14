package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private JFXButton canselButton;
    @FXML
    void oncanselClick(ActionEvent event) {
        Stage stage = (Stage)canselButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void onOkClick(ActionEvent event) throws IOException {
        String adminnameText = adminname.getText();
        String adminpasswordText = adminpassword.getText();
        ArrayList<UserDTO> allUsers = Usermodel.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUserName().equals(adminnameText) && allUsers.get(i).getPassword().equals(adminpasswordText) && allUsers.get(i).getType().equals("Admin")){
                Parent parent = FXMLLoader.load(getClass().getResource("/view/adminAddpop-form.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();

            }
        }
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
