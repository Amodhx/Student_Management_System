package lk.ijse.finalproject02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    private int studentid;
    private String firstName;
    private String lastName;
    private String gender;
    private String NIC;
    private String contactnumber;
    private String email;
    private int parentId;
    private String batch;
}
