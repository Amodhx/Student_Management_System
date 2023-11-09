package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.DTO.ParentDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Parentmodel;
import lk.ijse.finalproject02.Model.Studentmodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentadd3formController implements Initializable {
    public static Stage stage;

    @FXML
    private JFXButton backButton;
    public static String sfnamee;
    public static String batc;
    public static String slnamee;
    public static String nicc;
    public static String genderr;
    public static String contctnum;
    public static String maill;
    public static String parentName;
    public static String pjob;
    public static String pmail;
    public static String parenContct;


    @FXML
    private JFXButton finishButton;

    @FXML
    private JFXComboBox streamcombo;

    @FXML
    private JFXComboBox subjectComco;

    @FXML
    private JFXComboBox teacherCombo;
    ArrayList<TeacherDTO> teacherDTOS;

    @FXML
    void onselectStream(ActionEvent event) {
        String stream = (String) streamcombo.getValue();
        ArrayList<String> getsubjects = Classmodel.getsubjects(stream);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(getsubjects);
        subjectComco.setItems(observableList);
    }
    @FXML
    void onsubjectselected(ActionEvent event) {
        String sub = (String) subjectComco.getValue();
        teacherDTOS = Teachermodel.getteachersSubjectVise(sub);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < teacherDTOS.size(); i++) {
            observableList.add(teacherDTOS.get(i).getFirstName());
        }
        teacherCombo.setItems(observableList);
    }

    @FXML
    void onBackClick(ActionEvent event) {
        Stage stage1 = (Stage) finishButton.getScene().getWindow();
        stage1.close();
        stage.show();

    }

    @FXML
    void onFinishClick(ActionEvent event) {
        ParentDTO parentDTO = new ParentDTO(0,parentName,parenContct,pjob,pmail);
        Parentmodel.saveParent(parentDTO);
        ArrayList<ParentDTO> parentDTOS = Parentmodel.getallParent();
        int parentId  = parentDTOS.get(parentDTOS.size()-1).getParentId();
        StudentDTO studentDTO = new StudentDTO(0,sfnamee,slnamee,genderr,nicc,contctnum,maill,parentId,batc);
        Studentmodel.savStudent(studentDTO);

        ArrayList<StudentDTO> allStudents = Studentmodel.getAllStudents();
        int studentID = allStudents.get(allStudents.size()-1).getStudentid();

        String tname = (String) teacherCombo.getValue();
        int teacherId = Teachermodel.getTeacherId(tname);

        String s = Classmodel.getclassID(teacherId, batc);

        ClassDetailDTO classDetailDTO = new ClassDetailDTO(studentID,"s","das");


        Stage stage1 = (Stage) finishButton.getScene().getWindow();
        stage1.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("Physical Science","Bio Science","Commerce","Arts");
        streamcombo.setItems(observableList);
    }
}
