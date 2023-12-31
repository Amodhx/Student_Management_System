package lk.ijse.finalproject02.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ExamServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class examAddformController implements Initializable {
    public static String dat;
    public static String clsID;

    @FXML
    private Label classIDD;
    @FXML
    private TextField searchfield;
    @FXML
    private Label date;


    @FXML
    private GridPane gridpane;
    int colomn = 0;
    int row = 0;
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    ExamServiceImpl examService = (ExamServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.EXAM);

    @FXML
    void onseachPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            String searchfieldText = searchfield.getText();
            if (searchfieldText == null) {
                loadValues();
            } else {
                gridpane.getChildren().clear();
                ArrayList<StudentDTO> studentSearching = null;
                try {
                    studentSearching = studentService.getStudentSearching(clsID, searchfieldText);
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                } catch (ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
                examOBJfromController.clasid = clsID;
                for (int i = 0; i < studentSearching.size(); i++) {
                    try {
                        examOBJfromController.studentId = studentSearching.get(i).getStudentid();
                        examOBJfromController.searchtext = searchfieldText;
                        examOBJfromController.x = i;
                        Parent parent = FXMLLoader.load(getClass().getResource("/view/examOBJ-form.fxml"));
                        gridpane.add(parent, colomn, row++);
                        GridPane.setMargin(parent, new Insets(10, 10, 10, 10));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classIDD.setText(clsID);
        date.setText(dat);
        ExamDTO examDTO = new ExamDTO(0,clsID,dat);
        boolean saveexam = examService.save(examDTO);
        ArrayList<StudentDTO> studentClassVise = studentService.getStudentClassVise(clsID);
        examOBJfromController.clasid = clsID;

        for (int i = 0; i < studentClassVise.size(); i++) {
            try {
                examOBJfromController.studentId = studentClassVise.get(i).getStudentid();
                examOBJfromController.x = i ;
                Parent parent = FXMLLoader.load(getClass().getResource("/view/examOBJ-form.fxml"));
                gridpane.add(parent, colomn, row++);
                GridPane.setMargin(parent, new Insets(10, 10, 10, 10));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @SneakyThrows
    private void loadValues() {
        classIDD.setText(clsID);
        date.setText(dat);
        ExamDTO examDTO = new ExamDTO(0,clsID,dat);
        boolean saveexam = examService.save(examDTO);
        ArrayList<StudentDTO> studentClassVise = null;
        try {
            studentClassVise = studentService.getStudentClassVise(clsID);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        examOBJfromController.clasid = clsID;

        for (int i = 0; i < studentClassVise.size(); i++) {
            try {
                examOBJfromController.studentId = studentClassVise.get(i).getStudentid();
                examOBJfromController.x = i ;
                Parent parent = FXMLLoader.load(getClass().getResource("/view/examOBJ-form.fxml"));
                gridpane.add(parent, colomn, row++);
                GridPane.setMargin(parent, new Insets(10, 10, 10, 10));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
