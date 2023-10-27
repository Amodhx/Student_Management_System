package lk.ijse.finalproject02.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Studentmodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;

public class dbformController implements Initializable {

    @FXML
    private Label classcount;

    @FXML
    private Label seminarcount;
    @FXML
    private Label timeadd;

    @FXML
    private Label studentcount;

    @FXML
    private Label teachercount;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date time = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String timenow = simpleDateFormat.format(time);
        timeadd.setText(timenow);
        studentcount.setText(String.valueOf(Studentmodel.getStudentCount()));
        classcount.setText(String.valueOf(Classmodel.getClassCount()));
        teachercount.setText(String.valueOf(Teachermodel.getteachersCount()));
    }
}
