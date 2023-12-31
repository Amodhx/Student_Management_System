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
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.DTO.ParentDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentadd3formController implements Initializable {
    public static Stage stage;
    public static AnchorPane ancpane;

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
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    TeacherServiceImpl teacherService = (TeacherServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.TEACHER);
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);

    ClassDetailServiceImpl classDetailService = (ClassDetailServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASSDETAIL);
    ParentServiceImpl parentService = (ParentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PARENT);
    @FXML
    void onselectedTeacher(ActionEvent event) {
        finishButton.requestFocus();
    }

    @SneakyThrows
    @FXML
    void onselectStream(ActionEvent event) {
        String stream = (String) streamcombo.getValue();
        ArrayList<String> getsubjects = classService.getSubjects(stream);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(getsubjects);
        subjectComco.setItems(observableList);
        subjectComco.requestFocus();
    }
    @FXML
    void onsubjectselected(ActionEvent event) {
        String sub = (String) subjectComco.getValue();
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

    @FXML
    void onBackClick(ActionEvent event) {
        Stage stage1 = (Stage) finishButton.getScene().getWindow();
        stage1.close();
        stage.show();

    }

    @SneakyThrows
    @FXML
    void onFinishClick(ActionEvent event) throws IOException {
        ParentDTO parentDTO = new ParentDTO(0,parentName,parenContct,pjob,pmail);
        ArrayList<ParentDTO> parentDTOS = null;
        try {
            parentService.save(parentDTO);
            parentDTOS = parentService.getAll();
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }catch (ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        int parentId  = parentDTOS.get(parentDTOS.size()-1).getParentId();
        StudentDTO studentDTO = new StudentDTO(0,sfnamee,slnamee,genderr,nicc,contctnum,maill,parentId,batc);
        try {
            studentService.save(studentDTO);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        ArrayList<StudentDTO> allStudents = null;
        try {
            allStudents = studentService.getAll();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        int studentID = allStudents.get(allStudents.size()-1).getStudentid();
        System.out.println(batc);
        String tname = (String) teacherCombo.getValue();
        int teacherId = 0;
        try {
            teacherId = teacherService.getTeacherID(tname);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        System.out.println(teacherId);
        String s = classService.getClassID(teacherId, batc);
        System.out.println(s);

        ClassDetailDTO classDetailDTO = new ClassDetailDTO(studentID,s,"das");
        classDetailService.saveClassDetail(classDetailDTO);

        Stage stage1 = (Stage) finishButton.getScene().getWindow();
        stage1.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/view/student-form.fxml"));
        ancpane.getChildren().clear();
        ancpane.getChildren().add(parent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("Physical Science","Bio Science","Commerce","Arts");
        streamcombo.setItems(observableList);
    }
}
