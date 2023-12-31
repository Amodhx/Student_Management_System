package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.MailSender.Mailsend;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class mailsendformController implements Initializable {

    @FXML
    private JFXComboBox classcombo;

    @FXML
    private JFXComboBox  gradecombo;

    @FXML
    private JFXButton sendButton;

    @FXML
    private TextArea subject;
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);
    @FXML
    void onsendclick(ActionEvent event) {
        String subjec = "Massage From Excel Education Center";
        String text = subject.getText();
        String grade = (String) gradecombo.getValue();
        String classID = (String) classcombo.getValue();
        ArrayList<StudentDTO> studentClassVise = null;
        try {
            studentClassVise = studentService.getStudentClassVise(classID);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        for (int i = 0; i < studentClassVise.size(); i++) {
            Mailsend mailsend = new Mailsend(studentClassVise.get(i).getEmail(),subjec,text);
            mailsend.start();
        }
        new Alert(Alert.AlertType.INFORMATION,"Mails Send Successfully!!").show();
        classcombo.setValue("");
        comboinizia();
        subject.setText("");



    }
    @SneakyThrows
    public void comboinizia(){
        ObservableList<String> grade = FXCollections.observableArrayList("23","24");
        gradecombo.setItems(grade);
        ArrayList<ClassDTO> classDTOS = classService.getAllClasses();
        ObservableList<String> clas = FXCollections.observableArrayList();
        for (int i = 0; i < classDTOS.size(); i++) {
            clas.add(classDTOS.get(i).getClassId());
        }
        classcombo.setItems(clas);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboinizia();

    }
}
