package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class classformController {

    @FXML
    private JFXButton addclassButton;
    @FXML
    private AnchorPane pane;

    @FXML
    private GridPane gridpane;

    @FXML
    void onaddclassClick(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/classadd-form.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
