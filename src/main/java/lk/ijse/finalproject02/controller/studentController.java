package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class studentController {

    @FXML
    private JFXButton addstudentButton;

    @FXML
    private AnchorPane pane;

    @FXML
    void onaddStudentClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/studentadd-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().clear();
        pane.getChildren().add(parent);


    }

}
