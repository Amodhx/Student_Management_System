package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.AdminServiceImpl;

import java.io.IOException;
import java.util.ArrayList;

public class adminVrifyformController {

    @FXML
    private TextField adminname;

    @FXML
    private PasswordField adminpassword;

    @FXML
    private JFXButton canselButton;

    @FXML
    private JFXButton okButton;

    AdminServiceImpl adminService = (AdminServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    @FXML
    void onOkClick(ActionEvent event) throws IOException {
        String adminnameText = adminname.getText();
        String adminpasswordText = adminpassword.getText();
        ArrayList<UserDTO> allUsers =adminService.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUserName().equals(adminnameText) && allUsers.get(i).getPassword().equals(adminpasswordText) && allUsers.get(i).getType().equals("Admin")){
                Parent parent = FXMLLoader.load(getClass().getResource("/view/adminAddpop-form.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            }
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void onaddEnterPassword(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            okButton.requestFocus();
        }

    }

    @FXML
    void onaddEnterUserName(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            adminpassword.requestFocus();
        }


    }

    @FXML
    void oncanselClick(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();

    }

}
