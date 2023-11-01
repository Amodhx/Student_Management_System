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
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.Model.Attendencemodel;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendenceformController implements Initializable {

    @FXML
    private TextField createteacherName;
    @FXML
    private TextField viewteachername;

    @FXML
    private JFXButton createbutton;

    @FXML
    private JFXComboBox createclassIdcombo;

    @FXML
    private TextField enterthedate;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXComboBox selecttheDateCOmbo;



    @FXML
    private JFXButton viewbutton;


    @FXML
    private JFXComboBox viewclassIdcombo;
    @FXML
    void oncreateclassIDcombo(ActionEvent event) {
        String classId = (String) createclassIdcombo.getValue();
        int teacherid = Classmodel.getTeacherid(classId);
        String teacherName = Teachermodel.getTeacherName(teacherid);
        createteacherName.setText(teacherName);

    }

    @FXML
    void onviewclassIdcombo(ActionEvent event) {
        String classID = (String) viewclassIdcombo.getValue();
        int teacherid = Classmodel.getTeacherid(classID);
        String teacherName = Teachermodel.getTeacherName(teacherid);
        viewteachername.setText(teacherName);

    }

    @FXML
    void oncreateclikc(ActionEvent event) throws IOException {
        attendenceAddformController.dat = enterthedate.getText();
        attendenceAddformController.clasID = (String) createclassIdcombo.getValue();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/attendenceAdd-form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(parent);



    }

    @FXML
    void onviewCLikc(ActionEvent event) throws IOException {
        attendenceViewformController.dat = (String) selecttheDateCOmbo.getValue();
        attendenceViewformController.classID = (String) viewclassIdcombo.getValue();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/attendenceView-form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(parent);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewteachername.setEditable(false);
        createteacherName.setEditable(false);
        ArrayList<ClassDTO> classDTOS = Classmodel.getallClasses();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < classDTOS.size(); i++) {
            observableList.add(classDTOS.get(i).getClassId());
        }
        createclassIdcombo.setItems(observableList);
        viewclassIdcombo.setItems(observableList);

        ArrayList<String> arrayList = Attendencemodel.getallDates();
        ObservableList<String> observableList1 = FXCollections.observableArrayList();
        observableList1.addAll(arrayList);

        selecttheDateCOmbo.setItems(observableList1);

    }
}
