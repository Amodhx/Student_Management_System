package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.AttendenceDetailmodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendenceOBJformController implements Initializable {
    static  int x ;
    static String  classID;
    @FXML
    private Label NIC;

    private int studentID;
    public static int attendenceId;

    @FXML
    private JFXRadioButton absent;

    @FXML
    private Label name;

    @FXML
    private JFXRadioButton precent;
    @FXML
    void onselectedAbsent(ActionEvent event) {

    }

    @FXML
    void onprecentRadioclick(MouseEvent event) {
        AttendenceDetailDTO attendenceDetailDTO = new AttendenceDetailDTO(studentID,attendenceId,"Precent");
        boolean b = AttendenceDetailmodel.saveAttendeceDetail(attendenceDetailDTO);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<StudentDTO> studentClassVise = Studentmodel.getStudentClassVise(classID);
        name.setText(studentClassVise.get(x).getFirstName()+" "+studentClassVise.get(x).getLastName());
        NIC.setText(studentClassVise.get(x).getNIC());
        studentID = studentClassVise.get(x).getStudentid();

    }
}
