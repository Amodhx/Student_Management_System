package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.DTO.ParentDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentaddController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXComboBox classIdcombo;

    @FXML
    private JFXComboBox gendercombo;
    @FXML
    private TextField contactnumber;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;


    @FXML
    private TextField lastname;

    @FXML
    private TextField nic;

    @FXML
    private TextField parentcontact;

    @FXML
    private TextField parentemail;

    @FXML
    private TextField parentjob;

    @FXML
    private TextField parentname;


    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField teachername;

    @FXML
    void onselectedclassId(ActionEvent event) {
        String classID = (String) classIdcombo.getValue();

        int teacherID = Classmodel.getTeacherid(classID);
        String teacherN = Teachermodel.getTeacherName(teacherID);
        teachername.setText(teacherN);

    }

    @FXML
    void onsavaeclick(ActionEvent event) {
        String parenname = parentname.getText();
        String parencontact = parentcontact.getText();
        String parenjob = parentjob.getText();
        String parenmail = parentemail.getText();
        ParentDTO parentDTO = new ParentDTO(0,parenname,parencontact,parenjob,parenmail);
        boolean issave = Parentmodel.saveParent(parentDTO);
        ArrayList<ParentDTO> parentDTOS = Parentmodel.getallParent();


        String fname = firstname.getText();
        String lname = lastname.getText();
        String gender = (String) gendercombo.getValue();
        String Nic = nic.getText();
        String contact = contactnumber.getText();
        String mail = email.getText();
        int parentId = Integer.parseInt(String.valueOf(parentDTOS.get(parentDTOS.size()-1).getParentId()));
        StudentDTO studentDTO = new StudentDTO(0,fname,lname,gender,Nic,contact,mail,parentId);
        boolean is = Studentmodel.savStudent(studentDTO);

        ArrayList<StudentDTO> studentDTOS = Studentmodel.getAllStudents();
        int studentid = studentDTOS.get(studentDTOS.size() - 1).getStudentid();
        String classIDd = (String) classIdcombo.getValue();
        ClassDetailDTO classDetailDTO = new ClassDetailDTO(studentid,classIDd,"" );
        Boolean aBoolean = ClassDetailmodel.saveClassDetail(classDetailDTO);
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/student-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpane.getChildren().clear();
        anchorpane.getChildren().add(parent);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> ob = FXCollections.observableArrayList("Male","Female");
        gendercombo.setItems(ob);
        ArrayList<ClassDTO> classDTOS = Classmodel.getallClasses();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < classDTOS.size(); i++) {
            observableList.add(classDTOS.get(i).getClassId());
        }
        classIdcombo.setItems(observableList);

    }
}
