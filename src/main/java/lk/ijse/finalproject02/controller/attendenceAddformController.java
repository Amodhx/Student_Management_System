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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lk.ijse.finalproject02.DTO.AttendenceDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.AttendenceServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendenceAddformController implements Initializable {

    @FXML
    private JFXButton scanbutton;
    JFrame frame;
    public static ArrayList<String> stringArrayList = new ArrayList<>();
    static String dat;
    static String clasID;
    @FXML
    private Label date;
    @FXML
    private Label classIDD;

    @FXML
    private GridPane gridpane;
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    AttendenceServiceImpl attendenceService = (AttendenceServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ATTENDENCE);

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


    public void loadstudentsOBJ(){
        gridpane.getChildren().clear();
        try {
            ArrayList<StudentDTO> studentClassVise = studentService.getStudentClassVise(clasID);
            attendenceOBJformController.classID = clasID;
            AttendenceDTO attendenceDTO = new AttendenceDTO(0, clasID, dat);
            boolean b = attendenceService.saveAttendence(attendenceDTO);
            ArrayList<AttendenceDTO> getallattendence = attendenceService.getAllAttendence();
            attendenceOBJformController.attendenceId = getallattendence.get(getallattendence.size() - 1).getAttendenceId();
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
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Cant add Attendence Data").show();
        }catch (ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,"Cant add Attendence Data").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     loadstudentsOBJ();
    }
}
