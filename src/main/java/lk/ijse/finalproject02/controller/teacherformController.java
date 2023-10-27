package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class teacherformController {

    @FXML
    private JFXButton addteacher;

    @FXML
    private AnchorPane pane;

    @FXML
    void onaddteacherClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/teacheradd-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().clear();
        pane.getChildren().add(parent);

    }

}
