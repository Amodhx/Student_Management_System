package lk.ijse.finalproject02.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.TeacherServiceImpl;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
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
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    TeacherServiceImpl teacherService = (TeacherServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.TEACHER);
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);
    public static int x ;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ClassDTO> classDTOS = classService.getAll();
        classname.setText(classDTOS.get(x).getSubject());
        String classID = classDTOS.get(x).getClassId();
        ArrayList<StudentDTO> studentClassVise = new ArrayList<>();
        try {
            studentClassVise = studentService.getStudentClassVise(classID);
            studentcount.setText(String.valueOf(studentClassVise.size()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"AA").show();
        } catch (ClassNotFoundException e) {
            //new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        teacherID.setText(String.valueOf(classDTOS.get(x).getTeacherId()));
        try {
            teachername.setText(teacherService.getTeacherName(classDTOS.get(x).getTeacherId()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"B").show();
        } catch (ClassNotFoundException e) {
          // new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        grade.setText(classDTOS.get(x).getGrade());
    }
}
