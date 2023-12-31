package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.MailSender.Mailsend;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.AdminServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class passwordChangeController  extends Thread implements Initializable {

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
    AdminServiceImpl adminService = (AdminServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    @FXML
    void usernameentering(KeyEvent event) {
        ArrayList<UserDTO> allUsers = null;
        allUsers = adminService.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            if (username.getText().equals(allUsers.get(i).getUserName())){
                usernamevalid.setTextFill(Color.rgb(7,255,61,1));
                usernamevalid.setText("Valid Username");
                return;
            }else {
                usernamevalid.setTextFill(Color.rgb(244,32,32,1));
                usernamevalid.setText("Invalid Username");
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
        try {
            boolean b = adminService.changePassword(usernameText, passwordText);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Invalid Values!!"+ e.getMessage()).show();
        } catch (ClassNotFoundException e) {

        }
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
        Random random = new Random();
        randomNumber = random.nextInt(100000);
        Mailsend thredd = new Mailsend(email.getText(),String.valueOf(randomNumber),"");
        String text = email.getText();
        boolean matches = Pattern.matches("^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", text);
        if (matches) {
           thredd.start();
            sendvirification.setText("");
            new Alert(Alert.AlertType.CONFIRMATION,"You have to check your mailbox to get Verification code").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Invalid Email").show();
        }
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
