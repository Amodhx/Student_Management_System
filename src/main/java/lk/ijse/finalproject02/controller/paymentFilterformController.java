package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class paymentFilterformController {

    @FXML
    private TextField nictext;

    public static AnchorPane pane;

    @FXML
    private JFXButton viewButton;

    @FXML
    void onCanselIconCLick(MouseEvent event) {
        Stage stage = (Stage) viewButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onviweClick(ActionEvent event) {
        String nictextText = nictext.getText();
        Parent parent = null;
        viewpaymentController.nic = nictextText;
        viewpaymentController.ancpane = pane;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/viewPayment-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().clear();
        pane.getChildren().add(parent);
        Stage stage = (Stage) viewButton.getScene().getWindow();
        stage.close();

    }

}
