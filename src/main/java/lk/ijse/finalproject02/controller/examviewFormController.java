package lk.ijse.finalproject02.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.DTO.tm.exammarkstm;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ExamDetailServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.ExamServiceImpl;
import lk.ijse.finalproject02.service.custom.impl.StudentServiceImpl;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class examviewFormController implements Initializable {

    @FXML
    private TableColumn<exammarkstm, String> NICcolumn;

    @FXML
    private TableColumn<exammarkstm, String> classIdcolumn;

    @FXML
    private TableColumn<exammarkstm, String> markscolumn;

    @FXML
    private TableColumn<exammarkstm, String> namecolumn;

    @FXML
    private TableView table;
    public static int examId = 1 ;
    StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.STUDENT);
    ExamDetailServiceImpl examDetailService = (ExamDetailServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.EXAMDETAIL);
    ExamServiceImpl examService = (ExamServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.EXAM);

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ExamDetailDTO> examDetailDTOS = examDetailService.getExamMarksexamVise(examId);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        classIdcolumn.setCellValueFactory(new PropertyValueFactory<>("classID"));
        NICcolumn.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        markscolumn.setCellValueFactory(new PropertyValueFactory<>("marks"));

        ObservableList<exammarkstm> exammarkstms = FXCollections.observableArrayList();

        for (int i = 0; i <examDetailDTOS.size(); i++){
            try {
                String studentNIC1 = studentService.getStudentNIC(examDetailDTOS.get(i).getStudentID());
                String studentName = studentService.getStudentName(examDetailDTOS.get(i).getStudentID());
                int examId = examDetailDTOS.get(i).getExamId();
                String getclassID = examService.getclassID(examId);
                exammarkstm exammarkstm = new exammarkstm(studentName, getclassID, studentNIC1, examDetailDTOS.get(i).getMarks());
                exammarkstms.add(exammarkstm);
            }catch (ClassNotFoundException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }

        table.setItems(exammarkstms);
    }
}
