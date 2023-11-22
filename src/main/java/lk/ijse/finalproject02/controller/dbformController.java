package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.Model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;

public class dbformController implements Initializable {

    @FXML
    private Label classcount;

    @FXML
    private Label seminarcount;
    @FXML
    private Label timeadd;
    public static AnchorPane pane;

    @FXML
    private Label studentcount;

    @FXML
    private Label teachercount;
    @FXML
    private LineChart<?, ?> chart;

    @FXML
    void onMakeaPamentClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/makePayment-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setX(400);
        stage.setY(200);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    @FXML
    void onPaymentHistoryclick(ActionEvent event) {
        Stage stage = new Stage();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/paymentfiler-form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setY(620);
        stage.setX(880);
        paymentFilterformController.pane = pane;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    void onstudentrepotClick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/studentReportfilter-form.fxml"));
        } catch (IOException e) {


        }
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setX(880);
        stage.setY(520);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date time = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        LocalDate localDate = LocalDate.now();
        String month = String.valueOf(localDate.getMonth());
        String toMonth = null;
        if (month.equals("NOVEMBER")) {
            toMonth = "November";
        }
        String totale = Paymentmodel.totalAmountMonthVise(toMonth);
        seminarcount.setText(totale);
        String timenow = simpleDateFormat.format(time);
        timeadd.setText(timenow);
        studentcount.setText(String.valueOf(Studentmodel.getStudentCount()));
        classcount.setText(String.valueOf(Classmodel.getClassCount()));
        teachercount.setText(String.valueOf(Teachermodel.getteachersCount()));
        lineChart();
    }

    private void lineChart() {
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        series.setName("Physical Science");
        series1.setName("Bio Science");
        series2.setName("Commerce");
        series3.setName("Arts");

        ArrayList<ExamDetailDTO> marksSubjectAndBatchVise = ExamDetailmodel.getMarksSubjectAndBatchVise("Physical Science");
        ArrayList<ExamDetailDTO> marksSubjectAndBatchVise1 = ExamDetailmodel.getMarksSubjectAndBatchVise("Bio Science");
        ArrayList<ExamDetailDTO> marksSubjectAndBatchVise2 = ExamDetailmodel.getMarksSubjectAndBatchVise("Commerce");
        ArrayList<ExamDetailDTO> marksSubjectAndBatchVise3 = ExamDetailmodel.getMarksSubjectAndBatchVise("Arts");

        for (int i = 0; i < marksSubjectAndBatchVise.size(); i++) {


        }
        for (int i = 0; i < marksSubjectAndBatchVise1.size(); i++) {

        }
        for (int i = 0; i < marksSubjectAndBatchVise2.size(); i++) {

        }
        for (int i = 0; i < marksSubjectAndBatchVise3.size(); i++) {

        }

        series.getData().add(new XYChart.Data("1",45));
        series.getData().add(new XYChart.Data("2",75));
        series.getData().add(new XYChart.Data("3",25));
        series.getData().add(new XYChart.Data("4",95));
        series.getData().add(new XYChart.Data("5",55));

        series1.getData().add(new XYChart.Data("1",55));
        series1.getData().add(new XYChart.Data("2",95));
        series1.getData().add(new XYChart.Data("3",45));
        series1.getData().add(new XYChart.Data("4",25));
        series1.getData().add(new XYChart.Data("5",85));

        series2.getData().add(new XYChart.Data("1",85));
        series2.getData().add(new XYChart.Data("2",45));
        series2.getData().add(new XYChart.Data("3",95));
        series2.getData().add(new XYChart.Data("4",85));
        series2.getData().add(new XYChart.Data("5",25));

        series3.getData().add(new XYChart.Data("1",95));
        series3.getData().add(new XYChart.Data("2",85));
        series3.getData().add(new XYChart.Data("3",25));
        series3.getData().add(new XYChart.Data("4",55));
        series3.getData().add(new XYChart.Data("5",45));

        chart.getData().addAll(series,series1,series2,series3);
    }
}
