package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.io.IOException;
import java.net.URL;
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

        boolean b = Studentmodel.updateStudentValues(studentId, namefeildText, emailfieldText, contactfieldText, batchFieldText);
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
