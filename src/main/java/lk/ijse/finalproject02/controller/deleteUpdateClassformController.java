package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.tm.classDeletetm;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.TeacherServiceImpl;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class deleteUpdateClassformController implements Initializable {

    @FXML
    private TableColumn<classDeletetm, String> batchcolumn;

    @FXML
    private TableColumn<classDeletetm, JFXButton> deletecolumn;

    @FXML
    private TableColumn<classDeletetm, String> feecolumn;

    @FXML
    private TableColumn<classDeletetm, String> idcolumn;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<classDeletetm, String> subjectcolumn;

    @FXML
    private TableView<classDeletetm> table;

    @FXML
    private TableColumn<classDeletetm, String> teachercolumn;

    @FXML
    private TableColumn<classDeletetm, JFXButton> updatecolumn;
    TeacherServiceImpl teacherService = (TeacherServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.TEACHER);
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        loadValues();
    }

    @SneakyThrows
    private void loadValues() {
        ArrayList<ClassDTO> classDTOS = classService.getAll();
        ObservableList<classDeletetm> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < classDTOS.size(); i++) {
            String teacherName = null;
            try {
                teacherName = teacherService.getTeacherName(classDTOS.get(i).getTeacherId());
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
            classDeletetm classDeletetm = new classDeletetm(classDTOS.get(i).getClassId(),
                    classDTOS.get(i).getSubject(),
                    teacherName,classDTOS.get(i).getGrade(),
                    classDTOS.get(i).getFee(),
                    new JFXButton("Updatet"),
                    new JFXButton("Delete"));

            observableList.add(classDeletetm);
        }
        table.setItems(observableList);

        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getUpdateButton().setStyle("-fx-background-color: rgba(112, 143, 189, 1)");
            observableList.get(i).getUpdateButton().setTextFill(Color.WHITE);
            observableList.get(i).getDeleteButton().setStyle("-fx-background-color: rgba(175, 108, 108, 1)");
            observableList.get(i).getDeleteButton().setTextFill(Color.WHITE);
        }

        for (int i = 0; i < classDTOS.size(); i++) {
            String clasID  = observableList.get(i).getClassid();
            String sublect = observableList.get(i).getSubject();
            String bathc = observableList.get(i).getBatch();
            String fee = observableList.get(i).getFee();
            observableList.get(i).getUpdateButton().setOnAction(event ->{
                classUpdateformController.clsID =clasID;
                classUpdateformController.batch = bathc;
                classUpdateformController .subject =sublect;
                classUpdateformController.fee =fee;
                classUpdateformController.pane = pane;
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/view/classUpdate-form.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            });
            observableList.get(i).getDeleteButton().setOnAction(event ->{
                boolean b = false;
                try {
                    b = classService.deleteClass(clasID);
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,"Cant delete class Detail!!!").show();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Class Detail Deleted").show();
                    loadValues();
                }
            });
        }
    }

    private void setValues() {
        idcolumn.setCellValueFactory(new PropertyValueFactory<classDeletetm,String>("classid"));
        subjectcolumn.setCellValueFactory(new PropertyValueFactory<classDeletetm,String >("subject"));
        teachercolumn.setCellValueFactory(new PropertyValueFactory<classDeletetm,String>("teacher"));
        batchcolumn.setCellValueFactory(new PropertyValueFactory<classDeletetm,String>("batch"));
        feecolumn.setCellValueFactory(new PropertyValueFactory<classDeletetm,String>("fee"));
        updatecolumn.setCellValueFactory(new PropertyValueFactory<classDeletetm,JFXButton>("updateButton"));
        deletecolumn.setCellValueFactory(new PropertyValueFactory<classDeletetm,JFXButton>("deleteButton"));


    }
}
