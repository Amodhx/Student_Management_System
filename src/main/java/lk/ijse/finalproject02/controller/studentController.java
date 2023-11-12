package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.DTO.tm.Studenttm;
import lk.ijse.finalproject02.Model.ClassDetailmodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentController implements Initializable {
    @FXML
    private TableColumn<Studenttm,String> tableclass;

    @FXML
    private TableColumn<Studenttm,String> tablecontact;

    @FXML
    private TableColumn<Studenttm,String> tablegender;

    @FXML
    private TableColumn<Studenttm,String> tablemail;

    @FXML
    private TableColumn<Studenttm,String> tablename;

    @FXML
    private JFXButton addstudentButton;

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView table;
    @FXML
    private JFXButton alreadyhaveaccButton;

    @FXML
    private TableColumn<Studenttm, JFXButton> actioncolumn;

    @FXML
    void alreadyhaveaccOnClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/studenclassAdd-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void onaddStudentClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/studentadd-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();



    }
    public void loadValues(){
        ArrayList<StudentDTO> allStudents = Studentmodel.getAllStudents();
        ArrayList<ClassDetailDTO> classDetailDTOS = ClassDetailmodel.getAllClassDetails();
        ObservableList<Studenttm> observableList = FXCollections.observableArrayList();

        for (int i = 0; i <allStudents.size() ; i++) {
            int iddd = allStudents.get(i).getStudentid();
            String classIDD = ClassDetailmodel.getclassID(iddd);
            Studenttm studenttm = new Studenttm(allStudents.get(i).getFirstName(),classIDD,allStudents.get(i).getEmail(),allStudents.get(i).getContactnumber(),allStudents.get(i).getGender(),new JFXButton("Delete"));
            observableList.add(studenttm);
        }
        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getButton().setStyle("-fx-background-color: rgba(175, 108, 108, 1)");
            observableList.get(i).getButton().setTextFill(Color.WHITE);
        }

        for (int i = 0; i < observableList.size(); i++) {
            int studentid = allStudents.get(i).getStudentid();
            observableList.get(i).getButton().setOnAction(event -> {
                    boolean b = Studentmodel.deleteStudent(studentid);
                    if (b) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "ERROR!!").show();
                    }


                loadValues();
            });
        }

        table.setItems(observableList);
    }
    public void setValues(){
        tablename.setCellValueFactory(new PropertyValueFactory<Studenttm,String>("name"));
        tableclass.setCellValueFactory(new PropertyValueFactory<Studenttm,String>("classi"));
        tablemail.setCellValueFactory(new PropertyValueFactory<Studenttm,String>("mail"));
        tablecontact.setCellValueFactory(new PropertyValueFactory<Studenttm,String>("contact"));
        tablegender.setCellValueFactory(new PropertyValueFactory<Studenttm,String>("gender"));
        actioncolumn.setCellValueFactory(new PropertyValueFactory<Studenttm,JFXButton>("button"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      setValues();
      loadValues();

    }
}
