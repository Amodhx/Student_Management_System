package lk.ijse.finalproject02.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Studentmodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.io.IOException;
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
    public static AnchorPane pane;

    @FXML
    private Label studentcount;

    @FXML
    private Label teachercount;
    @FXML
    void onMakeaPamentClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/makePayment-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setX(400);
        stage.setY(200);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    @FXML
    void onPaymentHistoryclick(ActionEvent event) {
        Stage stage = new Stage();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/paymentfiler-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setY(620);
        stage.setX(880);
        paymentFilterformController.pane = pane;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    void onstudentrepotClick(ActionEvent event) {

    }




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
