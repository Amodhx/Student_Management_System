package lk.ijse.finalproject02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parent {
    private int parentId;
    private String name;
    private String contactNumber;
    private String job;
    private String email;
}
