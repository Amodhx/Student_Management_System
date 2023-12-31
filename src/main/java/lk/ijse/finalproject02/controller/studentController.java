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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.DTO.tm.Studenttm;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassDetailServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;
import lombok.SneakyThrows;

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
    private JFXComboBox<String> streamCombo;
    ArrayList<String> batches;
    @FXML
    private JFXComboBox<String> stream1Combo;

    @FXML
    private JFXComboBox<String> subjectCombo;
    @FXML
    private TableColumn<Studenttm, JFXButton> updatecolumn;
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);
    ClassDetailServiceImpl classDetailService = (ClassDetailServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASSDETAIL);
    @SneakyThrows
    @FXML
    void onStreamSelected(ActionEvent event) {
        String bach = streamCombo.getValue();
        String stream = stream1Combo.getValue();
        loadValues(bach,stream);
        ArrayList<String> getsubjects = classService.getSubjects(stream);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < getsubjects.size(); i++) {
            observableList.add(getsubjects.get(i));
        }
        subjectCombo.setItems(observableList);


    }

    @FXML
    void onSubjectSelected(ActionEvent event) {
        String bach = streamCombo.getValue();
        String stream = stream1Combo.getValue();
        String  subject = subjectCombo.getValue();

        loadValues(bach,stream,subject);

    }
    @FXML
    void onselected(ActionEvent event) {
        String x = streamCombo.getValue();
        loadValues(x);

    }

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
        studentaddController.studentAncPane = pane;
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
    @SneakyThrows
    public void loadValues(String... s){
        ArrayList<StudentDTO> allStudents = new ArrayList<>();
        try {
            if (s.length == 1) {
                allStudents = studentService.getStudentBatchVise(s[0]);
            } else if (s.length == 2) {
                allStudents = studentService.getStudentBatchAndStreamVise(s[0], s[1]);
            } else if (s.length == 3) {
                allStudents = studentService.getStudentBatchAndStreamViseAndSubjectVise(s[0], s[1], s[2]);
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }catch (ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        ObservableList<Studenttm> observableList = FXCollections.observableArrayList();


        for (int i = 0; i <allStudents.size() ; i++) {
            int iddd = allStudents.get(i).getStudentid();
            String classIDD = classDetailService.getclassID(iddd);
            Studenttm studenttm = new Studenttm(allStudents.get(i).getFirstName(),classIDD,allStudents.get(i).getEmail(),allStudents.get(i).getContactnumber(),allStudents.get(i).getGender(),new JFXButton("Update"),new JFXButton("Delete"));
            observableList.add(studenttm);
        }
        for (int i = 0; i < observableList.size(); i++) {
            observableList.get(i).getButton().setStyle("-fx-background-color: rgba(175, 108, 108, 1)");
            observableList.get(i).getButton().setTextFill(Color.WHITE);
            observableList.get(i).getJfxButton().setStyle("-fx-background-color: rgba(112, 143, 189, 1)");
            observableList.get(i).getJfxButton().setTextFill(Color.WHITE);
        }

        for (int i = 0; i < observableList.size(); i++) {
            int studentid = allStudents.get(i).getStudentid();
            observableList.get(i).getButton().setOnAction(event -> {
                boolean b = false;
                try {
                    b = studentService.deleteStudent(studentid);
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                } catch (ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
                ;
                    if (b) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "ERROR!!").show();
                    }


                loadValues(s);
            });
            int studentid1 = allStudents.get(i).getStudentid();
            String firstName = allStudents.get(i).getFirstName();
            String batch = allStudents.get(i).getBatch();
            String email = allStudents.get(i).getEmail();
            String contactnumber = allStudents.get(i).getContactnumber();
            observableList.get(i).getJfxButton().setOnAction(event ->{

                studentUpdateFromController.studentId =studentid1;
                studentUpdateFromController.studentName = firstName;
                studentUpdateFromController.studentEmail = email;
                studentUpdateFromController.studentBatch = batch;
                studentUpdateFromController.studentContact = contactnumber;
                studentUpdateFromController.ancpane = pane;
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/view/studentUpdate-form.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(parent);
                Stage stage =new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
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
        updatecolumn.setCellValueFactory(new PropertyValueFactory<Studenttm,JFXButton>("jfxButton"));
        actioncolumn.setCellValueFactory(new PropertyValueFactory<Studenttm,JFXButton>("button"));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
        try {
            batches = studentService.getBatches();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        if (batches.size()>0) {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(batches);
            streamCombo.setItems(observableList);
            streamCombo.setValue(batches.get(0));
            String s = batches.get(0);
            loadValues(s);
        }
        ObservableList<String> observableList = FXCollections.observableArrayList("Physical Science","Bio Science","Commerce","Arts");
        stream1Combo.setItems(observableList);

    }
}
