package lk.ijse.finalproject02.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class classDeletetm {
    private String classid;
    private String subject;
    private String teacher;
    private String  batch;
    private String fee;
    private JFXButton updateButton;
    private JFXButton deleteButton;
}
