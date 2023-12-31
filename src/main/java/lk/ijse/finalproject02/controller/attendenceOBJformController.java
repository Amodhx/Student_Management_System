package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.AttendenceDetailServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;

import java.net.URL;
import java.sql.SQLException;
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

    AttendenceDetailServiceImpl attendenceDetailService = (AttendenceDetailServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ATTENDENCEDETAIL);
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    @FXML
    void onselectedAbsent(ActionEvent event) {


    }
    @FXML
    void onprecentRadioclick(ActionEvent event) {
        AttendenceDetailDTO attendenceDetailDTO = new AttendenceDetailDTO(studentID,attendenceId,"Precent");
        try {
            boolean b = attendenceDetailService.saveAttendence(attendenceDetailDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<StudentDTO> studentClassVise = null;
        try {
            studentClassVise = studentService.getStudentClassVise(classID);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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
            try {
                attendenceDetailService.saveAttendence(attendenceDetailDTO);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
