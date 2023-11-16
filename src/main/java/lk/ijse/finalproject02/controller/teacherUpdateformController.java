package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class teacherUpdateformController implements Initializable {
    public static AnchorPane anxpane;
    public static int teacherID;
    public static String tacherFirstName;
    public static String teacherLastName;
    public static String teacherContact;
    public static String tacherEmail;
    public static String teacherNIC;


    @FXML
    private TextField EmailField;

    @FXML
    private TextField NICField;

    @FXML
    private JFXButton canselButton;

    @FXML
    private TextField contactfield;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private JFXButton saveButton;

    @FXML
    void onCanselButton(ActionEvent event) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void onSaveClick(ActionEvent event) {
        String fname = firstNameField.getText();
        String lname = lastNameField.getText();
        String contact = contactfield.getText();
        String mail = EmailField.getText();
        String nic = NICField.getText();

        boolean b = Teachermodel.updateTeacher(teacherID, fname, lname, contact, mail, nic);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Teacher Updated").show();
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/view/deleteUpdateteacher-form.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            anxpane.getChildren().clear();
            anxpane.getChildren().add(parent);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameField.setText(tacherFirstName);
        lastNameField.setText(teacherLastName);
        contactfield.setText(teacherContact);
        EmailField.setText(tacherEmail);
        NICField.setText(teacherNIC);
    }
}
