package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject02.DTO.PaymentDTO;
import lk.ijse.finalproject02.DTO.tm.paymentViewtm;
import lk.ijse.finalproject02.Model.Paymentmodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewpaymentController implements Initializable {
    public static String nic;
    public static AnchorPane ancpane;

    @FXML
    private TableColumn<paymentViewtm, String> amountcolumn;

    @FXML
    private TableColumn<paymentViewtm, String> classidcolumn;

    @FXML
    private JFXButton closeButton;

    @FXML
    private TableColumn<paymentViewtm, String> monthcolumn;

    @FXML
    private TableColumn<paymentViewtm, String> namecolumn;

    @FXML
    private TableColumn<paymentViewtm, String> niccolumn;

    @FXML
    private TableView table;

    @FXML
    void oncloseClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/db-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ancpane.getChildren().clear();
        ancpane.getChildren().add(parent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int studentID = Studentmodel.getStudentID(nic);

        ArrayList<PaymentDTO> paymentDTOS = Paymentmodel.getAllPaymentStudentVise(studentID);

        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        niccolumn.setCellValueFactory(new PropertyValueFactory<>("nic"));
        monthcolumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        classidcolumn.setCellValueFactory(new PropertyValueFactory<>("clasid"));
        amountcolumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        ObservableList<paymentViewtm> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < paymentDTOS.size(); i++) {
            String studentName = Studentmodel.getStudentName(paymentDTOS.get(i).getStudentId());
            String studentNIC = Studentmodel.getStudentNIC(paymentDTOS.get(i).getStudentId());
            paymentViewtm paymentViewtm = new paymentViewtm(studentName,studentNIC,paymentDTOS.get(i).getMonth(),paymentDTOS.get(i).getClasid(),paymentDTOS.get(i).getAmount());
            observableList.add(paymentViewtm);
        }
        table.setItems(observableList);

    }
}
