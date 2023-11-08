package lk.ijse.finalproject02.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DTO.AttendenceDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.Attendencemodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendenceAddformController implements Initializable {

    @FXML
    private JFXButton scanbutton;
    JFrame frame;
    public static ArrayList<String> stringArrayList = new ArrayList<>();

    @FXML
    void onScanButtonCllick(ActionEvent event) {

        String nic = null;
        Webcam webcam = Webcam.getDefault();
        if (webcam.isOpen()){
            //frame.setVisible(true);
        }else {
            if (webcam != null) {
                webcam.setViewSize(WebcamResolution.VGA.getSize());

                frame = new JFrame("QR Code Scanner");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new WebcamPanel(webcam));
                frame.pack();
                frame.setVisible(true);

                while (nic == null) {
                    if (webcam.isOpen()) {
                        BufferedImage image = webcam.getImage();
                        if (image != null) {
                            try {
                                BinaryBitmap binaryBitmap = new BinaryBitmap(
                                        new HybridBinarizer(
                                                new BufferedImageLuminanceSource(image)
                                        )
                                );

                                Result result = new MultiFormatReader().decode(binaryBitmap);
                                if (result != null) {
                                    Toolkit.getDefaultToolkit().beep();
                                    nic = result.getText();
                                    stringArrayList.add(nic);
                                    attendenceOBJformController.markNIC = nic;
                                    attendenceOBJformController.arrayList.addAll(stringArrayList);
                                    loadstudentsOBJ();
                                    webcam.close();
                                    frame.setVisible(false);
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            } else {
                System.err.println("No webcam detected.");
            }
        }
        }

    static String dat;
    static String clasID;
    @FXML
    private Label date;
    @FXML
    private Label classIDD;

    @FXML
    private GridPane gridpane;
    public void loadstudentsOBJ(){
        gridpane.getChildren().clear();
        ArrayList<StudentDTO> studentClassVise = Studentmodel.getStudentClassVise(clasID);
        attendenceOBJformController.classID = clasID;
        AttendenceDTO attendenceDTO = new AttendenceDTO(0,clasID,dat);
        boolean b = Attendencemodel.saveAttendence(attendenceDTO);
        ArrayList<AttendenceDTO> getallattendence = Attendencemodel.getallattendence();
        attendenceOBJformController.attendenceId = getallattendence.get(getallattendence.size()-1).getAttendenceId();
        date.setText(dat);
        classIDD.setText(clasID);
        int colomn = 0;
        int row = 0;
        for (int i = 0; i < studentClassVise.size(); i++) {
            try {
                attendenceOBJformController.x = i;
                Parent parent = FXMLLoader.load(getClass().getResource("/view/attendenceOBJ-form.fxml"));
                gridpane.add(parent, colomn, row++);
                GridPane.setMargin(parent, new Insets(10, 10, 10, 10));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     loadstudentsOBJ();
    }
}
