package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class mailsendformController {

    @FXML
    private JFXComboBox<?> classcombo;

    @FXML
    private JFXComboBox<?> gradecombo;

    @FXML
    private JFXButton sendButton;

    @FXML
    private TextArea subject;

    @FXML
    void onsendclick(ActionEvent event) {

    }

}
