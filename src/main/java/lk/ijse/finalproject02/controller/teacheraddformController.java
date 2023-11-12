package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.Model.Parentmodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class teacheraddformController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXComboBox gendercombo;

    @FXML
    private JFXButton saveButton;
    @FXML
    private TextField DOB;

    @FXML
    private TextField address;


    @FXML
    private TextField city;

    @FXML
    private TextField contactnumber;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;


    @FXML
    private TextField lastnmae;

    @FXML
    private TextField nic;

    @FXML
    void fonFirstname(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            lastnmae.requestFocus();
        }

    }

    @FXML
    void onAddress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            saveButton.requestFocus();
        }

    }

    @FXML
    void onCIty(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            address.requestFocus();
        }

    }


    @FXML
    void onContactNum(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            email.requestFocus();
        }

    }

    @FXML
    void onDOB(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            subject.requestFocus();
        }
    }

    @FXML
    void onEmail(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            nic.requestFocus();
        }

    }

    @FXML
    void onGender(ActionEvent event) {
        DOB.requestFocus();
    }

    @FXML
    void onLastName(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            gendercombo.requestFocus();
        }
    }

    @FXML
    void onNIc(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            city.requestFocus();
        }

    }

    @FXML
    void onSubject(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            contactnumber.requestFocus();
        }

    }


    @FXML
    private TextField subject;
    @FXML
    void onCanselClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/teacher-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);
    }

    @FXML
    void onsavaeclick(ActionEvent event) {
        String fname = firstname.getText();
        String lname = lastnmae.getText();
        String gender = (String) gendercombo.getValue();
        String datebirth = DOB.getText();
        String sub = subject.getText();
        String connub = contactnumber.getText();
        String mail = email.getText();
        String Nic = nic.getText();
        String cityText = city.getText();
        String addressText = address.getText();

        TeacherDTO teacherDTO = new TeacherDTO(0,fname,lname,gender,datebirth,sub,connub,mail,Nic,cityText,addressText);
        Boolean aBoolean = Teachermodel.saveTeacher(teacherDTO);
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/teacher-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("Male","Female");
        gendercombo.setItems(observableList);
        firstname.requestFocus();

    }
}
