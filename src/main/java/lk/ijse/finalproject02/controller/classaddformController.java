package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class classaddformController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TextField classID;

    @FXML
    private TextField day;

    @FXML
    private JFXComboBox gendercombo;

    @FXML
    private TextField grade;

    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField subject;
    ArrayList<TeacherDTO> teacherDTOS;
    @FXML
    private JFXComboBox Streamcombo;

    @FXML
    private TextField monthlyfee;

    @FXML
    void onsavaeclick(ActionEvent event) {
        String classIDText = classID.getText();
        String subjectText = subject.getText();
        String teacherName = (String) gendercombo.getValue();
        String gradeText = grade.getText();
        int teacherId = Teachermodel.getTeacherId(teacherName);
        String strem = (String) Streamcombo.getValue();
        String fee= monthlyfee.getText();

        ClassDTO classDTO = new ClassDTO(classIDText,subjectText,teacherId,gradeText,strem,fee);
        Classmodel.saveclass(classDTO);

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/class-form.fxml"));
            anchorpane.getChildren().clear();
            anchorpane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherDTOS = Teachermodel.getallTeachers();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < teacherDTOS.size(); i++) {
            observableList.add(teacherDTOS.get(i).getFirstName());
        }
        gendercombo.setItems(observableList);

        ObservableList<String> observableList1 = FXCollections.observableArrayList("Physical Science","Bio Science","Commerce","Arts");
        Streamcombo.setItems(observableList1);
    }
}
