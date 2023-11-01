package lk.ijse.finalproject02.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Studentmodel;
import lk.ijse.finalproject02.Model.Teachermodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class classOBJController implements Initializable {

    @FXML
    private Label classname;

    @FXML
    private Label grade;

    @FXML
    private Label studentcount;

    @FXML
    private Label teacherID;

    @FXML
    private Label teachername;
    public static int x ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ClassDTO> classDTOS = Classmodel.getallClasses();
        classname.setText(classDTOS.get(x).getSubject());
        String classID = classDTOS.get(x).getClassId();
        ArrayList<StudentDTO> studentClassVise = Studentmodel.getStudentClassVise(classID);
        studentcount.setText(String.valueOf(studentClassVise.size()));
        teacherID.setText(String.valueOf(classDTOS.get(x).getTeacherId()));
        teachername.setText(Teachermodel.getTeacherName(classDTOS.get(x).getTeacherId()));
        grade.setText(classDTOS.get(x).getGrade());
    }
}
