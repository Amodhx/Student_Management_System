package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalproject02.DB.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class settingformController {


    @FXML
    private JFXButton addadminButton;

    @FXML
    private AnchorPane ancpane;

    @FXML
    private JFXButton classButton;

    @FXML
    private JFXButton teacherButon;
    @FXML
    private JFXButton incomeReportButton;
    @FXML
    void OnINcomeClick(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        InputStream inputStream = getClass().getResourceAsStream("/reports/IncomeReport.jrxml");
        JasperDesign load = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DBConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasperPrint,false);


    }

    @FXML
    void onAddAdinCLick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/adminVerify-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void onclassClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/deleteUpdateClass-form.fxml"));
        ancpane.getChildren().clear();
        ancpane.getChildren().add(parent);

    }

    @FXML
    void onteacherClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/deleteUpdateteacher-form.fxml"));
        ancpane.getChildren().clear();
        ancpane.getChildren().add(parent);

    }

}
