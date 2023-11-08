package lk.ijse.finalproject02.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.DTO.tm.exammarkstm;
import lk.ijse.finalproject02.Model.Classmodel;
import lk.ijse.finalproject02.Model.ExamDetailmodel;
import lk.ijse.finalproject02.Model.Exammodel;
import lk.ijse.finalproject02.Model.Studentmodel;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ExamDetailDTO> examDetailDTOS = ExamDetailmodel.getallExamDetail();
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        classIdcolumn.setCellValueFactory(new PropertyValueFactory<>("classID"));
        NICcolumn.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        markscolumn.setCellValueFactory(new PropertyValueFactory<>("marks"));

        ObservableList<exammarkstm> exammarkstms = FXCollections.observableArrayList();

        for (int i = 0; i <examDetailDTOS.size(); i++){
            String studentNIC = Studentmodel.getStudentNIC(examDetailDTOS.get(i).getStudentID());
            String studentName = Studentmodel.getStudentName(examDetailDTOS.get(i).getStudentID());
            int examId = examDetailDTOS.get(i).getExamId();
            String getclassID = Exammodel.getclassID(examId);
            exammarkstm exammarkstm = new exammarkstm(studentName,getclassID,studentNIC,examDetailDTOS.get(i).getMarks());
            exammarkstms.add(exammarkstm);
        }

        table.setItems(exammarkstms);
    }
}
