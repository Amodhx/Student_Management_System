package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.DTO.ParentDTO;
import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.ExamDetailmodel;
import lk.ijse.finalproject02.Model.Parentmodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentreportformController implements Initializable {
    public static String nic;
    @FXML
    private GridPane gridpane;
    @FXML
    private LineChart<?, ?> examDetail;


    @FXML
    private Label parentEmail;

    @FXML
    private Label parentJOb;

    @FXML
    private Label parentName;

    @FXML
    private Label parnetContactnum;

    @FXML
    private Label studentContact;

    @FXML
    private Label studentEmail;

    @FXML
    private Label studentbatch;

    @FXML
    private Label studentgender;

    @FXML
    private Label studentnic;
    private int ParentID;
    @FXML
    private BarChart<?, ?> attendenceBarGraph;




    public void loadClass(){
        int studentID = Studentmodel.getStudentID(nic);
        ArrayList<ClassDTO> classDTOS = Classmodel.getclassObjStudentVIse(studentID);
        int colomn = 0;
        int row = 0;
        for (int i = 0; i < classDTOS.size(); i++) {
            studentReportClassIBJformController.x = i;
            studentReportClassIBJformController.arrayList = classDTOS;
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/studentReportClassObject-form.fxml"));
                gridpane.add(parent, colomn, row++);
                GridPane.setMargin(parent, new Insets(5, 5, 5, 5));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            loadClass();
            studentpersonalInfo();
            parentInfo();
            examMarkaddChart();
            atteendenceadd();

    }

    private void atteendenceadd() {
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series1.setName("Month Attendence");
        series1.getData().add(new XYChart.Data<>("1",50));
        series1.getData().add(new XYChart.Data<>("2",75));
        series1.getData().add(new XYChart.Data<>("3",25));
        series1.getData().add(new XYChart.Data<>("4",100));
        series1.getData().add(new XYChart.Data<>("5",50));
        attendenceBarGraph.getData().addAll(series,series1);

    }

    private void examMarkaddChart() {
        int studentID = Studentmodel.getStudentID(nic);
        XYChart.Series series = new XYChart.Series();
        series.setName("Last exam marks");
        ArrayList<ExamDetailDTO> marksStudentVIse = ExamDetailmodel.getMarksStudentVIse(studentID);
        for (int i = 0; i < marksStudentVIse.size(); i++) {
            if (i == 6 ){
                break;
            }
            int marks = Integer.parseInt(marksStudentVIse.get(i).getMarks());
            series.getData().add(new XYChart.Data<>("Mark" ,marks));
        }


        series.getData().add(new XYChart.Data<>("1",75));
        series.getData().add(new XYChart.Data<>("2",25));
        series.getData().add(new XYChart.Data<>("3",45));
        series.getData().add(new XYChart.Data<>("4",15));
        series.getData().add(new XYChart.Data<>("5",55));
        examDetail.getData().addAll(series);
    }

    private void parentInfo() {


        ParentDTO parentbyID = Parentmodel.getParentbyID(ParentID);

        parentName.setText(parentbyID.getName());
        parentEmail.setText(parentbyID.getEmail());
        parentJOb.setText(parentbyID.getJob());
        parnetContactnum.setText(parentbyID.getContactNumber());
    }

    private void studentpersonalInfo() {
        StudentDTO studentByStudentID = Studentmodel.getStudentByStudentID(Studentmodel.getStudentID(nic));
        ParentID = studentByStudentID.getParentId();
        studentgender.setText(studentByStudentID.getGender());
        studentnic.setText(studentByStudentID.getNIC());
        studentEmail.setText(studentByStudentID.getEmail());
        studentContact.setText(studentByStudentID.getContactnumber());
        studentbatch.setText(studentByStudentID.getBatch());
    }
}
