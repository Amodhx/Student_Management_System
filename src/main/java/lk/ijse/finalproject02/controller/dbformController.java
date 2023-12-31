package lk.ijse.finalproject02.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

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
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    TeacherServiceImpl teacherService = (TeacherServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.TEACHER);
    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);
    PaymentServiceImpl paymentService = (PaymentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PAYMENT);
    ExamDetailServiceImpl examDetailService = (ExamDetailServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.EXAMDETAIL);

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


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date time = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        LocalDate localDate = LocalDate.now();
        String month = String.valueOf(localDate.getMonth());
        String toMonth = null;
        if (month.equals("NOVEMBER")) {
            toMonth = "November";
        }else if(month.equals("DECEMBER")){
            toMonth = "December";
        }
        String totale = null;
        try {
            totale = paymentService.totalAmountMonthVise(toMonth);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        seminarcount.setText(totale);
        String timenow = simpleDateFormat.format(time);
        timeadd.setText(timenow);
        int studentCount = 0;
        try {
            studentCount = studentService.getCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        studentcount.setText(String.valueOf(studentCount));
        classcount.setText(String.valueOf(classService.getCount()));
        try {
            teachercount.setText(String.valueOf(teacherService.getCount()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        lineChart();
    }

    @SneakyThrows
    private void lineChart() {
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        series.setName("Physical Science");
        series1.setName("Bio Science");
        series2.setName("Commerce");
        series3.setName("Arts");

        ArrayList<ExamDetailDTO> marksSubjectAndBatchVise = examDetailService.getMarksSubjectAndBatchVise("Physical Science");
        ArrayList<ExamDetailDTO> marksSubjectAndBatchVise1 = examDetailService.getMarksSubjectAndBatchVise("Bio Science");
        ArrayList<ExamDetailDTO> marksSubjectAndBatchVise2 = examDetailService.getMarksSubjectAndBatchVise("Commerce");
        ArrayList<ExamDetailDTO> marksSubjectAndBatchVise3 = examDetailService.getMarksSubjectAndBatchVise("Arts");

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
