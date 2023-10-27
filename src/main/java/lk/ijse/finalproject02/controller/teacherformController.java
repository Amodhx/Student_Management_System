package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class teacherformController implements Initializable {

    @FXML
    private JFXButton addteacher;

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView table;

    @FXML
    private TableColumn<TeacherDTO, String> tablecontact;

    @FXML
    private TableColumn<TeacherDTO, String> tablegender;

    @FXML
    private TableColumn<TeacherDTO, String> tablemail;

    @FXML
    private TableColumn<TeacherDTO, String> tablename;

    @FXML
    private TableColumn<TeacherDTO, String> tablesubject;

    @FXML
    void onaddteacherClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/teacheradd-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().clear();
        pane.getChildren().add(parent);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<TeacherDTO> teacherDTOS = Teachermodel.getallTeachers();
        ObservableList<TeacherDTO> observableList = FXCollections.observableArrayList();
        observableList.addAll(teacherDTOS);

        tablename.setCellValueFactory(new PropertyValueFactory<TeacherDTO,String>("firstName"));
        tablesubject.setCellValueFactory(new PropertyValueFactory<TeacherDTO,String>("subject"));
        tablemail.setCellValueFactory(new PropertyValueFactory<TeacherDTO,String>("email"));
        tablecontact.setCellValueFactory(new PropertyValueFactory<TeacherDTO,String>("contactNumber"));
        tablegender.setCellValueFactory(new PropertyValueFactory<TeacherDTO,String>("gender"));

        table.setItems(observableList);
    }
}
