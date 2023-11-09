package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class studentaddController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton canselButton;

    @FXML
    private TextField contactNum;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private JFXComboBox gendercombo;

    @FXML
    private TextField lastname;

    @FXML
    private JFXButton nextButton;

    @FXML
    private TextField nic;
    @FXML
    private TextField batch;

    @FXML
    void oncanselCLick(ActionEvent event) {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void onnextClick(ActionEvent event) {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.hide();
        Parent parent = null;
        studentadd2Controller.stage = stage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/studentadd2-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage1 = new Stage();
        Scene scene = new Scene(parent);
        stage1.setScene(scene);
        stage1.setResizable(false);
        stage1.show();
        studentadd2Controller.sfnamee = firstName.getText();
        studentadd2Controller.batch = batch.getText();
        studentadd2Controller.slnamee = lastname.getText();
        studentadd2Controller.genderr = (String) gendercombo.getValue();
        studentadd2Controller.nicc = nic.getText();
        studentadd2Controller.contctnum = contactNum.getText();
        studentadd2Controller.maill = email.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableList = FXCollections.observableArrayList("Male","Female");
        gendercombo.setItems(observableList);
    }
}
