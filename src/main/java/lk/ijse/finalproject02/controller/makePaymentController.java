package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.PaymentDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.Paymentmodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class makePaymentController implements Initializable {

    @FXML
    private TextField amountCOmbo;

    @FXML
    private JFXButton canselButton;

    @FXML
    private JFXComboBox classIDCombo;

    @FXML
    private JFXComboBox monthCombo;

    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField studentNICCombo;
    @FXML
    void onclassSelected(ActionEvent event) {
        String clsID = (String) classIDCombo.getValue();
        String classFee = Classmodel.getClassFee(clsID);

        amountCOmbo.setText(classFee);

    }

    @FXML
    void onSaveClick(ActionEvent event) {
        String clasid = (String) classIDCombo.getValue();
        String nicComboText = studentNICCombo.getText();
        String month = (String) monthCombo.getValue();
        String amountCOmboText = amountCOmbo.getText();
        int studentID = Studentmodel.getStudentID(nicComboText);
        PaymentDTO paymentDTO = new PaymentDTO(0,studentID,amountCOmboText,month,clasid);
        boolean savepayment = Paymentmodel.savepayment(paymentDTO);
        if (savepayment){
            Stage stage = (Stage) canselButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void oncanselClick(ActionEvent event) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("January","February","March","Aprial","May","June","July","August","September","Octhomber","November","December");
        monthCombo.setItems(observableList);

        ArrayList<ClassDTO> classDTOS = Classmodel.getallClasses();
        ObservableList<String> observableList1 = FXCollections.observableArrayList();
        for (int i = 0; i < classDTOS.size(); i++) {
            observableList1.add(classDTOS.get(i).getClassId());
        }
        classIDCombo.setItems(observableList1);
        amountCOmbo.setEditable(false);
    }
}
