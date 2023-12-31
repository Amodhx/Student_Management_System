package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.PaymentDTO;
import lk.ijse.finalproject02.dao.custom.impl.StudentDAOImpl;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.PaymentServiceImpl;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
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
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);
    PaymentServiceImpl paymentService = (PaymentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PAYMENT);
    @SneakyThrows
    @FXML
    void onclassSelected(ActionEvent event) {
        String clsID = (String) classIDCombo.getValue();
        String classFee = classService.getClassFee(clsID);

        amountCOmbo.setText(classFee);

    }

    @FXML
    void onSaveClick(ActionEvent event) {
        String clasid = (String) classIDCombo.getValue();
        String nicComboText = studentNICCombo.getText();
        String month = (String) monthCombo.getValue();
        String amountCOmboText = amountCOmbo.getText();
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        int studentID = 0;
        try {
            studentID = studentDAO.getStudentID(nicComboText);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        PaymentDTO paymentDTO = new PaymentDTO(0,studentID,amountCOmboText,month,clasid);
        boolean savepayment = false;
        try {
            savepayment = paymentService.save(paymentDTO);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("January","February","March","Aprial","May","June","July","August","September","Octhomber","November","December");
        monthCombo.setItems(observableList);

        ArrayList<ClassDTO> classDTOS = classService.getAllClasses();
        ObservableList<String> observableList1 = FXCollections.observableArrayList();
        for (int i = 0; i < classDTOS.size(); i++) {
            observableList1.add(classDTOS.get(i).getClassId());
        }
        classIDCombo.setItems(observableList1);
        amountCOmbo.setEditable(false);
    }
}
