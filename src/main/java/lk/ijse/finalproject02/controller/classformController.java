package lk.ijse.finalproject02.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.service.ServiceFactory;
import lk.ijse.finalproject02.service.custom.impl.ClassServiceImpl;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class classformController implements Initializable {
    @FXML
    private JFXButton addclassButton;
    @FXML
    private AnchorPane pane;

    @FXML
    private GridPane gridpane;

    ClassServiceImpl classService = (ClassServiceImpl) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CLASS);

    @FXML
    void onaddclassClick(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/classadd-form.fxml"));
            pane.getChildren().clear();
            pane.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ClassDTO> classDTOS = classService.getAll();
        int colomn = 0;
        int row = 0;
        for (int i = 0; i < classDTOS.size(); i++) {
                classOBJController.x = i;
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/classOBJ-form.fxml"));
                gridpane.add(parent, colomn, row++);
                GridPane.setMargin(parent, new Insets(5,5,5,5));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

