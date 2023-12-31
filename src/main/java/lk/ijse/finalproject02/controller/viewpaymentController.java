package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.finalproject02.DTO.PaymentDTO;
import lk.ijse.finalproject02.DTO.tm.paymentViewtm;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.PaymentServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    PaymentServiceImpl paymentService = (PaymentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PAYMENT);

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
        int studentID = 0;
        try {
            studentID = studentService.getStudentId(nic);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }

        ArrayList<PaymentDTO> paymentDTOS = null;
        try {
            paymentDTOS = paymentService.getAllPaymentStudentVise(studentID);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        niccolumn.setCellValueFactory(new PropertyValueFactory<>("nic"));
        monthcolumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        classidcolumn.setCellValueFactory(new PropertyValueFactory<>("clasid"));
        amountcolumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        ObservableList<paymentViewtm> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < paymentDTOS.size(); i++) {
            try {
                String studentName = studentService.getStudentName(paymentDTOS.get(i).getStudentId());
                String studentNIC = studentService.getStudentNIC(paymentDTOS.get(i).getStudentId());
                paymentViewtm paymentViewtm = new paymentViewtm(studentName, studentNIC, paymentDTOS.get(i).getMonth(), paymentDTOS.get(i).getClasid(), paymentDTOS.get(i).getAmount());
                observableList.add(paymentViewtm);
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }catch (ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }

        }
        table.setItems(observableList);

    }
}
