package lk.ijse.finalproject02.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class dbformController implements Initializable {

    @FXML
    private Label classcount;

    @FXML
    private Label seminarcount;

    @FXML
    private Label studentcount;

    @FXML
    private Label teachercount;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentcount.setText(String.valueOf(Studentmodel.getStudentCount()));
    }
}
