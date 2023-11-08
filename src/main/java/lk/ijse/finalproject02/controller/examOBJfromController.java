package lk.ijse.finalproject02.controller;

import com.beust.ah.A;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.ExamDetailmodel;
import lk.ijse.finalproject02.Model.Exammodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

public class examOBJfromController implements Initializable {

    public static int x;
    public static int studentId;
    public static String clasid;

    @FXML
    private Label mail;

    @FXML
    private TextField mark;

    @FXML
    private Label name;

    @FXML
    private Label nic;
    ArrayList<StudentDTO> studentClassVise ;
    private int studen;


    @FXML
    void onmarkadded(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            ArrayList<ExamDTO> getallexam = Exammodel.getallexam();
            int z = getallexam.get(getallexam.size()-1).getExamId();
            String text = mark.getText();
            ExamDetailDTO examDetailDTO = new ExamDetailDTO(studen  ,z,text);
            boolean b = ExamDetailmodel.saveExamDetail(examDetailDTO);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       studentClassVise =  Studentmodel.getStudentClassVise(clasid);
        name.setText(studentClassVise.get(x).getFirstName()+" "+studentClassVise.get(x).getLastName());
        mail.setText(studentClassVise.get(x).getEmail());
        nic.setText(studentClassVise.get(x).getNIC());
        studen = studentClassVise.get(x).getStudentid();
    }
}
