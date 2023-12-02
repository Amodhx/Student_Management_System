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
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.net.URL;
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

    @FXML
    void onsendclick(ActionEvent event) {
        String subjec = "Massage From Excel Education Center";
        String text = subject.getText();
        String grade = (String) gradecombo.getValue();
        String classID = (String) classcombo.getValue();
        ArrayList<StudentDTO> studentClassVise = Studentmodel.getStudentClassVise(classID);
        for (int i = 0; i < studentClassVise.size(); i++) {
            Mailsend mailsend = new Mailsend(studentClassVise.get(i).getEmail(),subjec,text);
            mailsend.start();
        }
        new Alert(Alert.AlertType.INFORMATION,"Mails Send Successfully!!").show();
        classcombo.setValue("");
        comboinizia();
        subject.setText("");



    }
    public void comboinizia(){
        ObservableList<String> grade = FXCollections.observableArrayList("23","24");
        gradecombo.setItems(grade);
        ArrayList<ClassDTO> classDTOS = Classmodel.getallClasses();
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
