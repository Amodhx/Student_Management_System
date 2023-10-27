package lk.ijse.finalproject02.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
    private String classId;
    private String subject;
    private int teacherId;
    private String grade;
}
