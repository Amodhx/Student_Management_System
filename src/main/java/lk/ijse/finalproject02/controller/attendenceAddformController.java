package lk.ijse.finalproject02.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lk.ijse.finalproject02.DTO.AttendenceDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.Attendencemodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendenceAddformController implements Initializable {
    static String dat;
    static String clasID;
    @FXML
    private Label date;
    @FXML
    private Label classIDD;

    @FXML
    private GridPane gridpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<StudentDTO> studentClassVise = Studentmodel.getStudentClassVise(clasID);
        attendenceOBJformController.classID = clasID;
        AttendenceDTO attendenceDTO = new AttendenceDTO(0,clasID,dat);
        boolean b = Attendencemodel.saveAttendence(attendenceDTO);
        ArrayList<AttendenceDTO> getallattendence = Attendencemodel.getallattendence();
        attendenceOBJformController.attendenceId = getallattendence.get(getallattendence.size()-1).getAttendenceId();
        date.setText(dat);
        classIDD.setText(clasID);
        int colomn = 0;
        int row = 0;
        for (int i = 0; i < studentClassVise.size(); i++) {
            try {
                attendenceOBJformController.x = i;
                Parent parent = FXMLLoader.load(getClass().getResource("/view/attendenceOBJ-form.fxml"));
                gridpane.add(parent, colomn, row++);
                GridPane.setMargin(parent, new Insets(10, 10, 10, 10));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
