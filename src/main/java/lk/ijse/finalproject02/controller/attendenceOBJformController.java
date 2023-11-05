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
    static String markNIC =  null;
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
    private JFXRadioButton precentradio;

    public static ArrayList<String> arrayList = new ArrayList<>();
    @FXML
    void onselectedAbsent(ActionEvent event) {


    }
    @FXML
    void onprecentRadioclick(ActionEvent event) {
        AttendenceDetailDTO attendenceDetailDTO = new AttendenceDetailDTO(studentID,attendenceId,"Precent");
        boolean b = AttendenceDetailmodel.saveAttendeceDetail(attendenceDetailDTO);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<StudentDTO> studentClassVise = Studentmodel.getStudentClassVise(classID);
        for (int i = 0; i < arrayList.size(); i++) {
            if (studentClassVise.get(x).getNIC().equals(arrayList.get(i))){
                precentradio.setSelected(true);
            }
        }

        name.setText(studentClassVise.get(x).getFirstName()+" "+studentClassVise.get(x).getLastName());
        NIC.setText(studentClassVise.get(x).getNIC());
        studentID = studentClassVise.get(x).getStudentid();

        if (studentClassVise.get(x).getNIC().equals(markNIC)){
            AttendenceDetailDTO attendenceDetailDTO = new AttendenceDetailDTO(studentID,attendenceId,"precent");
            AttendenceDetailmodel.saveAttendeceDetail(attendenceDetailDTO);
        }
    }
}
