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
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.AttendenceServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.TeacherServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendenceformController implements Initializable {

    public DatePicker tenderhearted;
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
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);
    TeacherServiceImpl teacherService = (TeacherServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.TEACHER);
    AttendenceServiceImpl attendenceService = (AttendenceServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ATTENDENCE);

    @FXML
    void oncreateclassIDcombo(ActionEvent event) {
        String classId = (String) createclassIdcombo.getValue();
        int teacherid = 0;
        String teacherName = null;
        try {
            teacherid = classService.getTeacherID(classId);
            teacherName = teacherService.getTeacherName(teacherid);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        createteacherName.setText(teacherName);

    }

    @FXML
    void onviewclassIdcombo(ActionEvent event) {
        String classID = (String) viewclassIdcombo.getValue();
        int teacherid = 0;
        String teacherName = null;
        try {
            teacherid = classService.getTeacherID(classID);
            teacherName = teacherService.getTeacherName(teacherid);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        viewteachername.setText(teacherName);

    }

    @FXML
    void oncreateclikc(ActionEvent event) throws IOException {
        attendenceAddformController.dat = String.valueOf(tenderhearted.getValue());
        System.out.println(tenderhearted.getValue());
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
        try {
            viewteachername.setEditable(false);
            createteacherName.setEditable(false);
            ArrayList<ClassDTO> classDTOS = classService.getAllClasses();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (int i = 0; i < classDTOS.size(); i++) {
                observableList.add(classDTOS.get(i).getClassId());
            }
            createclassIdcombo.setItems(observableList);
            viewclassIdcombo.setItems(observableList);

            ArrayList<String> arrayList = attendenceService.getAllDates();
            ObservableList<String> observableList1 = FXCollections.observableArrayList();
            observableList1.addAll(arrayList);

            selecttheDateCOmbo.setItems(observableList1);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
