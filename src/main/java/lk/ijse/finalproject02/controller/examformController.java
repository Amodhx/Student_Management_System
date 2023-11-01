package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class examformController {

    @FXML
    private JFXButton addevebutton;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> tableavarage;

    @FXML
    private TableColumn<?, ?> tablegrade;

    @FXML
    private TableColumn<?, ?> tablemarks;

    @FXML
    private TableColumn<?, ?> tablename;

    @FXML
    private TableColumn<?, ?> tablesubject;

    @FXML
    void onaddevaluationclikc(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/examfilter-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setY(200);
        stage.setX(900);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

}
