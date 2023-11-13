package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.Model.ClassDetailmodel;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Studentmodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class alreadyregisteredStudentController implements Initializable {

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton finishButton;

    @FXML
    private JFXComboBox streamcombo;

    @FXML
    private TextField studentNIC;

    @FXML
    private TextField studentName;

    @FXML
    private JFXComboBox subjectComco;

    @FXML
    private JFXComboBox teacherCombo;
    @FXML
    void onselectedTeacher(ActionEvent event) {
        finishButton.requestFocus();
    }

    @FXML
    void onBackClick(ActionEvent event) {
        Stage stage  = (Stage) finishButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onFinishClick(ActionEvent event) {
        String text = studentNIC.getText();
        int studentID = Studentmodel.getStudentID(text);
        String sub = (String) subjectComco.getValue();
        String tea = (String) teacherCombo.getValue();
        int teacherId = Teachermodel.getTeacherId(tea);
        String s = Classmodel.getclassID(teacherId, sub);
        ClassDetailDTO classDetailDTO = new ClassDetailDTO(studentID,s,"asdas");
        Boolean aBoolean = ClassDetailmodel.saveClassDetail(classDetailDTO);

        Stage stage = (Stage) finishButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onNIcadd(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            String text = studentNIC.getText();
            int studentID = Studentmodel.getStudentID(text);
            String studentName1 = Studentmodel.getStudentName(studentID);
            studentName.setText(studentName1);
            streamcombo.requestFocus();
        }

    }

    @FXML
    void onselectStream(ActionEvent event) {
        String stream = (String) streamcombo.getValue();
        ArrayList<String> getsubjects = Classmodel.getsubjects(stream);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(getsubjects);
        subjectComco.setItems(observableList);
        subjectComco.requestFocus();
    }

    @FXML
    void onsubjectselected(ActionEvent event) {
        String sub = (String) subjectComco.getValue();
        ArrayList<TeacherDTO> teacherDTOS = Teachermodel.getteachersSubjectVise(sub);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < teacherDTOS.size(); i++) {
            observableList.add(teacherDTOS.get(i).getFirstName());
        }
        teacherCombo.setItems(observableList);
        teacherCombo.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("Physical Science","Bio Science","Commerce","Arts");
        streamcombo.setItems(observableList);
    }
}
