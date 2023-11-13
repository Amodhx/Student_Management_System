package lk.ijse.finalproject02.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.finalproject02.DTO.ClassDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentReportClassIBJformController implements Initializable {
    public static int x;
    public static ArrayList<ClassDTO> arrayList;

    @FXML
    private Label batch;

    @FXML
    private Label className;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        className.setText(arrayList.get(x).getSubject());
        batch.setText(arrayList.get(x).getGrade());
    }
}
