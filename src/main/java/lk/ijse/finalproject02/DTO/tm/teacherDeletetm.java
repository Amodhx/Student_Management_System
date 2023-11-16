package lk.ijse.finalproject02.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class teacherDeletetm {
    private int id;
    private String name;
    private String gender;
    private String subject;
    private String nic;
    private String contactNum;
    private String email;
    private JFXButton updateButton;
    private JFXButton deleteButton;
}
