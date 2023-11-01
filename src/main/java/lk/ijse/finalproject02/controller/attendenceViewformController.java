package lk.ijse.finalproject02.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.DTO.tm.ViewAttendenceTm;
import lk.ijse.finalproject02.Model.AttendenceDetailmodel;
import lk.ijse.finalproject02.Model.ClassDetailmodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendenceViewformController implements Initializable {

    public static String dat;
    public static String classID;

    @FXML
    private TableColumn<ViewAttendenceTm, String> NICcolumn;

    @FXML
    private TableColumn<ViewAttendenceTm, String> attendColumn;

    @FXML
    private TableColumn<ViewAttendenceTm, String> dateColumn;

    @FXML
    private TableColumn<ViewAttendenceTm, String> namecolumn;

    @FXML
    private TableView table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ViewAttendenceTm> observableList = FXCollections.observableArrayList();
        ArrayList<AttendenceDetailDTO> attendenceDetailClassVise = AttendenceDetailmodel.getAttendenceDetailClassVise(classID, dat);
        for (int i = 0; i < attendenceDetailClassVise.size(); i++) {
            String n = Studentmodel.getStudentName(attendenceDetailClassVise.get(i).getStudentID());
            String nic = Studentmodel.getStudentNIC(attendenceDetailClassVise.get(i).getStudentID());
            ViewAttendenceTm viewAttendenceTm = new ViewAttendenceTm(n,nic,dat,attendenceDetailClassVise.get(i).getMark());
            observableList.add(viewAttendenceTm);
        }

        namecolumn.setCellValueFactory(new PropertyValueFactory<ViewAttendenceTm,String>("name"));
        NICcolumn.setCellValueFactory(new PropertyValueFactory<ViewAttendenceTm,String>("NIC"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ViewAttendenceTm,String>("date"));
        attendColumn.setCellValueFactory(new PropertyValueFactory<ViewAttendenceTm,String>("attend"));

        table.setItems(observableList);

    }
}
