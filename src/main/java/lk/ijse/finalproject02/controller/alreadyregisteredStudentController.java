package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassDetailServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.TeacherServiceImpl;

import java.net.URL;
import java.sql.SQLException;
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
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    TeacherServiceImpl teacherService = (TeacherServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.TEACHER);
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);
    ClassDetailServiceImpl classDetailService = (ClassDetailServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASSDETAIL);

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
        boolean b = false;
        try {
            int studentId = studentService.getStudentId(studentNIC.getText());
            String classID = classService.getClassID(
                    teacherService.getTeacherID((String) teacherCombo.getValue()),
                    studentService.getStudentBatch(studentId));

             b = classDetailService.saveClassDetail(new ClassDetailDTO(studentId, classID, "asd"));
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (b) {
            Stage stage = (Stage) finishButton.getScene().getWindow();
            stage.close();
        }else {
            new Alert(Alert.AlertType.ERROR,"Cant add Student to class").show();
        }
    }

    @FXML
    void onNIcadd(KeyEvent event) {
        try {
            if (event.getCode().equals(KeyCode.ENTER)) {
                String text = studentNIC.getText();
                int studentID = studentService.getStudentId(text);
                String studentName1 = studentService.getStudentName(studentID);
                studentName.setText(studentName1);
                streamcombo.requestFocus();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }catch (ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void onselectStream(ActionEvent event) {
        String stream = (String) streamcombo.getValue();
        ArrayList<String> getsubjects = null;
        try {
            getsubjects = classService.getSubjects(stream);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"cant load Classes").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"cant load Classes").show();
        }
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(getsubjects);
        subjectComco.setItems(observableList);
        subjectComco.requestFocus();
    }

    @FXML
    void onsubjectselected(ActionEvent event) {
        String sub = (String) subjectComco.getValue();
        ArrayList<TeacherDTO> teacherDTOS = null;
        try {
            teacherDTOS = teacherService.getTeacherSubVise(sub);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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
