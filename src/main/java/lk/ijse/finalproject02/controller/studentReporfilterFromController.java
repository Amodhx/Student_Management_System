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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class studentReporfilterFromController {
    public static AnchorPane pane;

    @FXML
    private TextField nictext;

    @FXML
    private JFXButton scanButton;

    @FXML
    private JFXButton viewButton;
    JFrame frame;

    @FXML
    void onCanselIconCLick(MouseEvent event) {
        Stage stage = (Stage) scanButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void onScanClick(ActionEvent event) {
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
                                    nictext.setText(nic);
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

    @FXML
    void onviweClick(ActionEvent event) {
        String nictextText = nictext.getText();
        studentreportformController.nic = nictextText;
        Parent parent  = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/studentreport-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Stage stage1 = (Stage) viewButton.getScene().getWindow();
        stage1.close();

    }

}
