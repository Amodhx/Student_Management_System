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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.ExamServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.TeacherServiceImpl;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class examfilterformController implements Initializable {
    @FXML
    private DatePicker selecttheDate;

    @FXML
    private JFXButton createbutton;

    @FXML
    private JFXComboBox createclassIdcombo;

    @FXML
    private TextField createteacherName;

    @FXML
    private TextField enterthedate;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField viewTeacherName;

    @FXML
    private JFXButton viewbutton;

    @FXML
    private JFXComboBox viewclassIDClombo;

    @FXML
    private JFXComboBox examIdcombo;
    TeacherServiceImpl teacherService = (TeacherServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.TEACHER);
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);
    ExamServiceImpl examService = (ExamServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.EXAM);

    @SneakyThrows
    @FXML
    void oncreateclassIDcombo(ActionEvent event) {
       String classID = (String) createclassIdcombo.getValue();
        int teacherid = classService.getTeacherID(classID);
        String teacherNa = null;
        try {
            teacherNa = teacherService.getTeacherName(teacherid);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        createteacherName.setText(teacherNa);
    }

    @FXML
    void oncreateclikc(ActionEvent event) {
        examAddformController.clsID =  (String) createclassIdcombo.getValue();
        examAddformController.dat = String.valueOf(selecttheDate.getValue());
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/examAddform.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @SneakyThrows
    @FXML
    void onviewClassIdClombo(ActionEvent event) {
        String classID = (String) viewclassIDClombo.getValue();
        int teacherid = classService.getTeacherID(classID);
        String teacherNa = null;
        try {
            teacherNa = teacherService.getTeacherName(teacherid);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        viewTeacherName.setText(teacherNa);
    }

    @FXML
    void onviewclick(ActionEvent event) {
        String x = (String) examIdcombo.getValue();
        int exami = Integer.parseInt(x);
        examviewFormController.examId = exami;
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/examViewform.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(parent);

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ClassDTO> classDTOS = classService.getAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < classDTOS.size(); i++) {
            observableList.add(classDTOS.get(i).getClassId());
        }
        createclassIdcombo.setItems(observableList);
        viewclassIDClombo.setItems(observableList);
        ArrayList<ExamDTO> getallexam = examService.getAll();
        ObservableList<String>  observableList1 = FXCollections.observableArrayList();
        for (int i = 0; i < getallexam.size(); i++) {
            observableList1.add(String.valueOf(getallexam.get(i).getExamId()));
        }
        examIdcombo.setItems(observableList1);


    }
}
