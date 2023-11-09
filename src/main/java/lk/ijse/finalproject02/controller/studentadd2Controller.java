package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.ParentDTO;

import java.io.IOException;

public class studentadd2Controller {

    @FXML
    private JFXButton backButton;

    public static Stage stage;
    public static String batch;

    public static String sfnamee;
    public static String slnamee;
    public static String nicc;
    public static String genderr;
    public static String contctnum;
    public static String maill;


    @FXML
    private JFXButton nextButton;

    @FXML
    private TextField parentcontact;

    @FXML
    private TextField parentemail;

    @FXML
    private TextField parentjob;

    @FXML
    private TextField parentname;

    @FXML
    void onBackClick(ActionEvent event) {
        Stage stage1 = (Stage) nextButton.getScene().getWindow();
        stage1.close();
        stage.show();
    }

    @FXML
    void onnextClick(ActionEvent event) {
        studentadd3formController.sfnamee = sfnamee;
        studentadd3formController.slnamee =slnamee;
        studentadd3formController.maill = maill;
        studentadd3formController.nicc = nicc;
        studentadd3formController.contctnum = contctnum;
        studentadd3formController.genderr = genderr;
        studentadd3formController.batc =batch;
        studentadd3formController.parentName = parentname.getText();
        studentadd3formController.pmail = parentemail.getText();
        studentadd3formController.pjob = parentjob.getText();
        studentadd3formController.parenContct = parentcontact.getText();


        Stage stage1 = (Stage) nextButton.getScene().getWindow();
        stage1.hide();
        Parent parent ;
        studentadd3formController.stage = stage1;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/studentadd3-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage2 = new Stage();
        Scene scene = new Scene(parent);
        stage2.setScene(scene);
        stage2.setResizable(false);
        stage2.show();
    }

    }


