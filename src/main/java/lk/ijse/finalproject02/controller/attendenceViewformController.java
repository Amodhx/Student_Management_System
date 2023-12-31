package lk.ijse.finalproject02.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;
import lk.ijse.finalproject02.DTO.tm.ViewAttendenceTm;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.AttendenceDetailServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.AttendenceServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
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

    AttendenceDetailServiceImpl attendenceDetailService = (AttendenceDetailServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ATTENDENCEDETAIL);
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ViewAttendenceTm> observableList = FXCollections.observableArrayList();
        ArrayList<AttendenceDetailDTO> attendenceDetailClassVise = attendenceDetailService.getAttendenceClassVise(classID, dat);
        for (int i = 0; i < attendenceDetailClassVise.size(); i++) {
            try {
                String studentName = studentService.getStudentName(attendenceDetailClassVise.get(i).getStudentID());
                String studentNIC = studentService.getStudentNIC(attendenceDetailClassVise.get(i).getStudentID());
                ViewAttendenceTm viewAttendenceTm = new ViewAttendenceTm(studentName, studentNIC, dat, attendenceDetailClassVise.get(i).getMark());
                observableList.add(viewAttendenceTm);
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }catch (ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }

        namecolumn.setCellValueFactory(new PropertyValueFactory<ViewAttendenceTm,String>("name"));
        NICcolumn.setCellValueFactory(new PropertyValueFactory<ViewAttendenceTm,String>("NIC"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ViewAttendenceTm,String>("date"));
        attendColumn.setCellValueFactory(new PropertyValueFactory<ViewAttendenceTm,String>("attend"));

        table.setItems(observableList);

    }
}
