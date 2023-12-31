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
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class studentUpdateFromController implements Initializable {
    public static int studentId;
    public static String studentName;
    public static String studentEmail;
    public static String studentContact;
    public static String studentBatch;
    public static AnchorPane ancpane;


    @FXML
    private TextField batchField;

    @FXML
    private JFXButton canselButton;

    @FXML
    private TextField contactfield;

    @FXML
    private TextField emailfield;

    @FXML
    private TextField namefeild;

    @FXML
    private JFXButton saveButton;
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);

    @FXML
    void onCanselButton(ActionEvent event) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    void onSaveClick(ActionEvent event) throws IOException {
        String namefeildText = namefeild.getText();
        String emailfieldText = emailfield.getText();
        String contactfieldText = contactfield.getText();
        String batchFieldText = batchField.getText();

        boolean b = false;
        try {
            b = studentService.updateStudentValues(studentId, namefeildText, emailfieldText, contactfieldText, batchFieldText);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        if (b){
            Parent parent = FXMLLoader.load(getClass().getResource("/view/student-form.fxml"));
            ancpane.getChildren().clear();
            ancpane.getChildren().add(parent);
            Stage stage =(Stage) saveButton.getScene().getWindow();
            stage.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namefeild.setText(studentName);
        emailfield.setText(studentEmail);
        contactfield.setText(studentContact);
        batchField.setText(studentBatch);
    }
}
