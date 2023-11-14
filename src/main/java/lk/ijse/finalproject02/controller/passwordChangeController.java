package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.MailSender.Mailsend;
import lk.ijse.finalproject02.Model.Usermodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class passwordChangeController implements Initializable {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label correct;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordretype;

    @FXML
    private JFXButton savebutton;

    @FXML
    private Label sendvirification;

    @FXML
    private TextField username;

    @FXML
    private Label vefificationOk;

    @FXML
    private TextField verificationcode;
    private int randomNumber;

    @FXML
    private Label usernamevalid;
    @FXML
    void usernameentering(KeyEvent event) {
        ArrayList<UserDTO> allUsers = Usermodel.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            if (username.getText().equals(allUsers.get(i).getUserName())){
                usernamevalid.setTextFill(Color.rgb(7,255,61,1));
                usernamevalid.setText("Valid Username");
                break;
            }else {
                usernamevalid.setTextFill(Color.rgb(244,32,32,1));
                usernamevalid.setText("Invalid Username");
                break;
            }
        }
    }

    @FXML
    void oncancelClikc(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onsaveClick(ActionEvent event) {
        String usernameText = username.getText();
        String passwordText = password.getText();
        boolean b = Usermodel.changePassword(usernameText, passwordText);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void onverificationType(KeyEvent event) {
       int t = Integer.parseInt(verificationcode.getText());
       if (t == randomNumber){
           vefificationOk.setText("OK");
       }
    }

    @FXML
    void sendvirificationClick(MouseEvent event) {
        String text = email.getText();
        Random random = new Random();
        randomNumber = random.nextInt(100000);
        Mailsend.sendMail(text, String.valueOf(randomNumber),"");
        sendvirification.setText("");

    }
    @FXML
    void onRetyperpassword(KeyEvent event) {
        if (password.getText().equals(passwordretype.getText())) {
            correct.setText("**Checked correct");
            savebutton.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        correct.setText("");
        vefificationOk.setText("");
        savebutton.setDisable(true);
        usernamevalid.setText("");
    }
}
