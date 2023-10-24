package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private ImageView dashimage;

    @FXML
    private JFXButton attendencebutton;

    @FXML
    private JFXButton classbutton;

    @FXML
    private Label classcount;


    @FXML
    private JFXButton studentbutton1;
    @FXML
    private JFXButton exambutton;

    @FXML
    private JFXButton mailbutton;

    @FXML
    private Label seminarcount;

    @FXML
    private JFXButton settingbutton;

    @FXML
    private JFXButton studentbutton;

    @FXML
    private Label studentcount;

    @FXML
    private JFXButton teacherbutton;

    @FXML
    private Label teachercount;

    @FXML
    private TextField textfield;

    @FXML
    private Label time;

    @FXML
    void onattendenceclick(ActionEvent event) {
        colourRemove();
        attendencebutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        attendencebutton.setTextFill(Color.rgb(5, 75, 180, 1));

    }

    @FXML
    void onclassesclick(ActionEvent event) {
        colourRemove();
        classbutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        classbutton.setTextFill(Color.rgb(5, 75, 180, 1));

    }
    @FXML
    void ondashclick(ActionEvent event) {
        colourRemove();
        studentbutton1.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        studentbutton1.setTextFill(Color.rgb(5, 75, 180, 1));
    }

    @FXML
    void onexamclikc(ActionEvent event) {
        colourRemove();
        exambutton.setStyle("-fx-background-color: rgba(255, 255, 255, 1)");
        exambutton.setTextFill(Color.rgb(5, 75, 180, 1));

    }

    @FXML
    void onmailclick(ActionEvent event) {
        colourRemove();
        mailbutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        mailbutton.setTextFill(Color.rgb(5, 75, 180, 1));

    }

    @FXML
    void onnotificlick(MouseEvent event) {

    }

    @FXML
    void onsearching(KeyEvent event) {

    }

    @FXML
    void onsettingclick(ActionEvent event) {
        colourRemove();
        settingbutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        settingbutton.setTextFill(Color.rgb(5, 75, 180, 1));

    }

    @FXML
    void onstudentclick(ActionEvent event) {
        colourRemove();
        studentbutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        studentbutton.setTextFill(Color.rgb(5, 75, 180, 1));

    }

    @FXML
    void onteacherclick(ActionEvent event) {
        colourRemove();
        teacherbutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        teacherbutton.setTextFill(Color.rgb(5, 75, 180, 1));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colourRemove();
        studentbutton1.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        studentbutton1.setTextFill(Color.rgb(5, 75, 180, 1));
    }
    public void colourRemove(){
        studentbutton1.setStyle("-fx-background-color:rgba(5, 75, 180, 1) ");
        studentbutton.setStyle("-fx-background-color:rgba(5, 75, 180, 1) ");
        teacherbutton.setStyle("-fx-background-color:rgba(5, 75, 180, 1) ");
        classbutton.setStyle("-fx-background-color:rgba(5, 75, 180, 1) ");
        exambutton.setStyle("-fx-background-color:rgba(5, 75, 180, 1) ");
        attendencebutton.setStyle("-fx-background-color:rgba(5, 75, 180, 1) ");
        mailbutton.setStyle("-fx-background-color:rgba(5, 75, 180, 1) ");
        settingbutton.setStyle("-fx-background-color:rgba(5, 75, 180, 1) ");
        studentbutton1.setTextFill(Color.rgb(255, 255, 255, 1));
        studentbutton.setTextFill(Color.rgb(255, 255, 255, 1));
        teacherbutton.setTextFill(Color.rgb(255, 255, 255, 1));
        classbutton.setTextFill(Color.rgb(255, 255, 255, 1));
        exambutton.setTextFill(Color.rgb(255, 255, 255, 1));
        attendencebutton.setTextFill(Color.rgb(255, 255, 255, 1));
        mailbutton.setTextFill(Color.rgb(255, 255, 255, 1));
        settingbutton.setTextFill(Color.rgb(255, 255, 255, 1));
    }


}
