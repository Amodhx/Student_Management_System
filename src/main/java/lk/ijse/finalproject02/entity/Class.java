package lk.ijse.finalproject02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    private String classId;
    private String subject;
    private int teacherId;
    private String grade;
    private String stream;
    private String fee;

    public Class(String subject, String grade) {
        this.subject = subject;
        this.grade = grade;
    }
}
