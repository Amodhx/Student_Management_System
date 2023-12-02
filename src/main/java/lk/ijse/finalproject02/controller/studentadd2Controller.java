package lk.ijse.finalproject02.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.ParentDTO;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class studentadd2Controller extends Thread implements Initializable {




    @Override
    public void run(){
        String nic = nicc;
        String to = maill;
        String from = "amxdhnanditha@gmail.com";
        String host = "smtp.gmail.com";
        String username = "amxdhnanditha@gmail.com";
        String password = "gnhv lcmt oogq nppk";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Welcome to Excel-Education Center");
            String data = nic;
            String path = "C:\\Users\\User\\IdeaProjects\\final-project-02\\QR.jpg";
            try {
                BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE,500,500);
                MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Multipart multipart = new MimeMultipart();
            String imagePath = "C:\\Users\\User\\IdeaProjects\\final-project-02\\QR.jpg";
            BodyPart imagePart = new MimeBodyPart();
            imagePart.setDataHandler(new DataHandler(new FileDataSource(imagePath)));
            imagePart.setFileName("image.jpg");
            multipart.addBodyPart(imagePart);
            message.setContent(multipart);


            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }

    public static AnchorPane stuAncPane;

    @FXML
    private JFXButton backButton;

    public static Stage stage;
    public static String batch;

    public static String sfnamee;
    public static String slnamee;
    public static String nicc;
    public static String genderr;
    public static String contctnum;
    public static String maill;


    @FXML
    private JFXButton nextButton;

    @FXML
    private TextField parentcontact;

    @FXML
    private TextField parentemail;

    @FXML
    private TextField parentjob;

    @FXML
    private TextField parentname;
    @FXML
    void onContactNub(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            nextButton.requestFocus();
        }

    }

    @FXML
    void onEmail(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            parentcontact.requestFocus();
        }

    }

    @FXML
    void onJob(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            parentemail.requestFocus();
        }

    }

    @FXML
    void onName(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            parentjob.requestFocus();
        }

    }

    @FXML
    void onBackClick(ActionEvent event) {
        Stage stage1 = (Stage) nextButton.getScene().getWindow();
        stage1.close();
        stage.show();
    }

    @FXML
    void onnextClick(ActionEvent event) {
        boolean matches = Pattern.matches("^0\\d{2}\\d{7}$", parentcontact.getText());
        if (matches){
            boolean matches1 = Pattern.matches("^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", parentemail.getText());
            if (matches1) {
                studentadd3formController.sfnamee = sfnamee;
                studentadd3formController.slnamee = slnamee;
                studentadd3formController.maill = maill;
                studentadd3formController.nicc = nicc;
                studentadd3formController.contctnum = contctnum;
                studentadd3formController.genderr = genderr;
                studentadd3formController.batc = batch;
                studentadd3formController.parentName = parentname.getText();
                studentadd3formController.pmail = parentemail.getText();
                studentadd3formController.pjob = parentjob.getText();
                studentadd3formController.parenContct = parentcontact.getText();

                studentadd2Controller thred = new studentadd2Controller();


                Stage stage1 = (Stage) nextButton.getScene().getWindow();
                stage1.hide();
                Parent parent;
                studentadd3formController.stage = stage1;
                studentadd3formController.ancpane = stuAncPane;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/view/studentadd3-form.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage2 = new Stage();
                Scene scene = new Scene(parent);
                stage2.setScene(scene);
                stage2.setResizable(false);
                stage2.show();
                thred.start();
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Email Address").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Contact Number").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentname.requestFocus();
    }
}


