package lk.ijse.finalproject02.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentreportformController implements Initializable {
    @FXML
    private GridPane gridpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int studentID = Studentmodel.getStudentID("200227000940");
        ArrayList<ClassDTO> classDTOS = Classmodel.getclassObjStudentVIse(studentID);
        int colomn = 0;
        int row = 0;
        for (int i = 0; i < classDTOS.size(); i++) {
            studentReportClassIBJformController.x = i;
            studentReportClassIBJformController.arrayList = classDTOS;
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/studentReportClassObject-form.fxml"));
                gridpane.add(parent, colomn, row++);
                GridPane.setMargin(parent, new Insets(5, 5, 5, 5));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
