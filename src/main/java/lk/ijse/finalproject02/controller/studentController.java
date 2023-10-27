package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.Observable;
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
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentController implements Initializable {
    @FXML
    private TableColumn<StudentDTO,String> tableclass;

    @FXML
    private TableColumn<StudentDTO,String> tablecontact;

    @FXML
    private TableColumn<StudentDTO,String> tablegender;

    @FXML
    private TableColumn<StudentDTO,String> tablemail;

    @FXML
    private TableColumn<StudentDTO,String> tablename;

    @FXML
    private JFXButton addstudentButton;

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView table;

    @FXML
    void onaddStudentClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/studentadd-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().clear();
        pane.getChildren().add(parent);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<StudentDTO> allStudents = Studentmodel.getAllStudents();
        ObservableList<StudentDTO> observableList = FXCollections.observableArrayList();
        observableList.addAll(allStudents);



        tablename.setCellValueFactory(new PropertyValueFactory<StudentDTO,String>("firstName"));
        tableclass.setCellValueFactory(new PropertyValueFactory<StudentDTO,String>("classId"));
        tablemail.setCellValueFactory(new PropertyValueFactory<StudentDTO,String>("email"));
        tablecontact.setCellValueFactory(new PropertyValueFactory<StudentDTO,String>("contactnumber"));
        tablegender.setCellValueFactory(new PropertyValueFactory<StudentDTO,String>("gender"));
        table.setItems(observableList);


    }
}
