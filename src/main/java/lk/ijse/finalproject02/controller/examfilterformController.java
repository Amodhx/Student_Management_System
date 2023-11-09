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
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Exammodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class examfilterformController implements Initializable {

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

    @FXML
    void oncreateclassIDcombo(ActionEvent event) {
       String classID = (String) createclassIdcombo.getValue();
        int teacherid = Classmodel.getTeacherid(classID);
        String teacherNa = Teachermodel.getTeacherName(teacherid);
        createteacherName.setText(teacherNa);
    }

    @FXML
    void oncreateclikc(ActionEvent event) {
        examAddformController.clsID =  (String) createclassIdcombo.getValue();
        examAddformController.dat = enterthedate.getText();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/examAddform.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void onviewClassIdClombo(ActionEvent event) {
        String classID = (String) viewclassIDClombo.getValue();
        int teacherid = Classmodel.getTeacherid(classID);
        String teacherNa = Teachermodel.getTeacherName(teacherid);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ClassDTO> classDTOS = Classmodel.getallClasses();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < classDTOS.size(); i++) {
            observableList.add(classDTOS.get(i).getClassId());
        }
        createclassIdcombo.setItems(observableList);
        viewclassIDClombo.setItems(observableList);
        ArrayList<ExamDTO> getallexam = Exammodel.getallexam();
        ObservableList<String>  observableList1 = FXCollections.observableArrayList();
        for (int i = 0; i < getallexam.size(); i++) {
            observableList1.add(String.valueOf(getallexam.get(i).getExamId()));
        }
        examIdcombo.setItems(observableList1);


    }
}
