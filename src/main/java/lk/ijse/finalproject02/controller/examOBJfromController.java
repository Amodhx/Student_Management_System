package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ExamDetailServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.ExamServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class examOBJfromController implements Initializable {

    public static int x;
    public static int studentId;
    public static String clasid;
    @FXML
    private JFXRadioButton oksavedradioButton;

    @FXML
    private Label mail;

    @FXML
    private TextField mark;

    @FXML
    private Label name;
    public static String searchtext;


    @FXML
    private Label nic;
    ArrayList<StudentDTO> studentClassVise ;
    private int studen;
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    ExamDetailServiceImpl examDetailService = (ExamDetailServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.EXAMDETAIL);
    ExamServiceImpl examService = (ExamServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.EXAM);


    @SneakyThrows
    @FXML
    void onmarkadded(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            oksavedradioButton.setSelected(true);
            ArrayList<ExamDTO> getallexam = examService.getAll();
            int z = getallexam.get(getallexam.size()-1).getExamId();
            String text = mark.getText();
            ExamDetailDTO examDetailDTO = new ExamDetailDTO(studen  ,z,text);
            boolean b = examDetailService.save(examDetailDTO);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (searchtext == null) {
                studentClassVise = studentService.getStudentClassVise(clasid);
            } else {
                studentClassVise = studentService.getStudentSearching(clasid, searchtext);
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }catch (ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        name.setText(studentClassVise.get(x).getFirstName()+" "+studentClassVise.get(x).getLastName());
        mail.setText(studentClassVise.get(x).getEmail());
        nic.setText(studentClassVise.get(x).getNIC());
        studen = studentClassVise.get(x).getStudentid();
    }
}
