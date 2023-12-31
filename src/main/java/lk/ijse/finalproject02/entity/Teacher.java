package lk.ijse.finalproject02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Teacher {
    private int teacherId;
    private String firstName;
    private String lastName;
    private String gender;
    private String DOB;
    private String subject;
    private String contactNumber;
    private String email;
    private String NIC;
    private String city;
    private String address;

}
