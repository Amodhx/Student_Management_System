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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.DTO.tm.teacherDeletetm;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class deleteUpdateTeacherformController implements Initializable {
    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<teacherDeletetm, String> contactcolumn;

    @FXML
    private TableColumn<teacherDeletetm, JFXButton> deletecolumn;

    @FXML
    private TableColumn<teacherDeletetm, String > emailcolumn;

    @FXML
    private TableColumn<teacherDeletetm, String> gendercolumn;

    @FXML
    private TableColumn<teacherDeletetm, Integer> idcolumn;

    @FXML
    private TableColumn<teacherDeletetm, String> namecolumn;

    @FXML
    private TableColumn<teacherDeletetm, String> niccolumn;

    @FXML
    private TableColumn<teacherDeletetm, String> subjectcolumn;

    @FXML
    private TableView<teacherDeletetm> table;
    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<teacherDeletetm, JFXButton> updatecolumn;
    @FXML
    void onsearching(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumn();
        loadValues();

    }

    private void loadValues() {
        ArrayList<TeacherDTO> teacherDTOS = Teachermodel.getallTeachers();
        ObservableList<teacherDeletetm> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < teacherDTOS.size(); i++) {
            teacherDeletetm deletetm =
                    new teacherDeletetm(teacherDTOS.get(i).getTeacherId(),
                            teacherDTOS.get(i).getFirstName()+" "+teacherDTOS.get(i).getLastName(),
                            teacherDTOS.get(i).getGender(),
                            teacherDTOS.get(i).getSubject(),
                            teacherDTOS.get(i).getNIC(),
                            teacherDTOS.get(i).getContactNumber(),
                            teacherDTOS.get(i).getEmail(),
                            new JFXButton("Update"),
                            new JFXButton("Delete"));

            observableList.add(deletetm);

        }
        table.setItems(observableList);

        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getUpdateButton().setStyle("-fx-background-color: rgba(112, 143, 189, 1)");
            observableList.get(i).getUpdateButton().setTextFill(Color.WHITE);
            observableList.get(i).getDeleteButton().setStyle("-fx-background-color: rgba(175, 108, 108, 1)");
            observableList.get(i).getDeleteButton().setTextFill(Color.WHITE);
        }

        for (int i = 0; i < observableList.size(); i++) {
            int teaID = observableList.get(i).getId();
            String fname = teacherDTOS.get(i).getFirstName();
            String lname = teacherDTOS.get(i).getLastName();
            String con = observableList.get(i).getContactNum();
            String email = observableList.get(i).getEmail();
            String nic = observableList .get(i).getNic();
            observableList.get(i).getUpdateButton().setOnAction(event -> {
                teacherUpdateformController.anxpane =pane;
                teacherUpdateformController.teacherID = teaID;
                teacherUpdateformController.tacherFirstName = fname;
                teacherUpdateformController.teacherLastName = lname;
                teacherUpdateformController.teacherContact = con;
                teacherUpdateformController.tacherEmail = email;
                teacherUpdateformController.teacherNIC =nic;
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/view/teacherUpdate-form.fxml"));
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
                boolean b = Teachermodel.deleteTeacher(teaID);
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Teacher Deleted").show();
                    loadValues();
                }
            });
        }

    }

    private void setColumn() {
        idcolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,Integer>("id"));
        namecolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,String>("name"));
        gendercolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,String>("gender"));
        subjectcolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,String>("subject"));
        niccolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,String>("nic"));
        contactcolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,String>("contactNum"));
        emailcolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,String>("email"));
        updatecolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,JFXButton>("updateButton"));
        deletecolumn.setCellValueFactory(new PropertyValueFactory<teacherDeletetm,JFXButton>("deleteButton"));
    }
}
