package lk.ijse.finalproject02.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendenceDTO {
    private int attendenceId;
    private int studentId;
    private String markattendence;
    private String date;
}
