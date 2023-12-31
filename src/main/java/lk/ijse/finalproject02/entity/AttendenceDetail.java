package lk.ijse.finalproject02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttendenceDetail {
    private int studentID;
    private int attendenceId;
    private String mark;
}
