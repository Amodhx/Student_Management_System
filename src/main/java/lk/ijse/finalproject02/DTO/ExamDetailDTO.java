package lk.ijse.finalproject02.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExamDetailDTO {
    private int studentID;
    private int examId;
    private String marks;
}
