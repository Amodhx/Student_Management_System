package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class classUpdateformController implements Initializable {
    public static AnchorPane pane;
    public static String clsID;
    public static String subject;
    public static String batch;
    public static String fee;



    @FXML
    private TextField batchfield;

    @FXML
    private JFXButton canselButton;

    @FXML
    private TextField feefeild;

    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField subjectfield;
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);

    @FXML
    void onCanselButton(ActionEvent event) {
        Stage stage =  (Stage) saveButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void onSaveClick(ActionEvent event) throws IOException {
        String sub = subjectfield.getText();
        String btch = batchfield.getText();
        String  feee = feefeild.getText();

        boolean b = false;
        try {
            b = classService.updateClass(clsID, sub, btch, feee);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"cant update class names!!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Class Detail Deleted").show();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/deleteUpdateClass-form.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(parent);

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjectfield.setText(subject);
        feefeild.setText(fee);
        batchfield.setText(batch);
    }
}
