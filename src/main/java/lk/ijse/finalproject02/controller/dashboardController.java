package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {



    @FXML
    private JFXButton attendencebutton;

    @FXML
    private JFXButton classbutton;




    @FXML
    private JFXButton studentbutton1;
    @FXML
    private JFXButton exambutton;

    @FXML
    private JFXButton mailbutton;

    public static JFXButton button;



    @FXML
    private JFXButton settingbutton;

    @FXML
    private JFXButton studentbutton;



    @FXML
    private JFXButton teacherbutton;

    @FXML
    private TextField textfield;

    @FXML
    private AnchorPane pane;



    @FXML
    void onadminiconClikc(MouseEvent event) {
        Stage stage = new Stage();
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/view/addadmin-form.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setX(1060);
            stage.setY(50);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

    }
    @FXML
    void onattendenceclick(ActionEvent event) {
        colourRemove();
        attendencebutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        attendencebutton.setTextFill(Color.rgb(5, 75, 180, 1));
        changePage("/view/attendence-form.fxml");

    }

    @FXML
    void onclassesclick(ActionEvent event) {
        colourRemove();
        classbutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        classbutton.setTextFill(Color.rgb(5, 75, 180, 1));
        changePage("/view/class-form.fxml");

    }
    @FXML
    void ondashclick(ActionEvent event) {
        colourRemove();
        studentbutton1.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        studentbutton1.setTextFill(Color.rgb(5, 75, 180, 1));
        changePage("/view/db-form.fxml");
    }

    @FXML
    void onexamclikc(ActionEvent event) {
        colourRemove();
        exambutton.setStyle("-fx-background-color: rgba(255, 255, 255, 1)");
        exambutton.setTextFill(Color.rgb(5, 75, 180, 1));
        changePage("/view/exam-form.fxml");

    }

    @FXML
    void onmailclick(ActionEvent event) {
        colourRemove();
        mailbutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        mailbutton.setTextFill(Color.rgb(5, 75, 180, 1));
        changePage("/view/mailsend-form.fxml");

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
        changePage("/view/student-form.fxml");

    }

    @FXML
    void onteacherclick(ActionEvent event) {
        colourRemove();
        teacherbutton.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        teacherbutton.setTextFill(Color.rgb(5, 75, 180, 1));
        changePage("/view/teacher-form.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colourRemove();
        changePage("/view/db-form.fxml");
        studentbutton1.setStyle("-fx-background-color:rgba(203, 231, 235, 1) ");
        studentbutton1.setTextFill(Color.rgb(5, 75, 180, 1));
    }
    public void changePage(String uiName){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(uiName));
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
