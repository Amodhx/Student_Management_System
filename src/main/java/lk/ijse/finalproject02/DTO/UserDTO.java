package lk.ijse.finalproject02.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private int userId;
    private String userName;
    private String password;
    private String type;
    private String mail;
    private String fullname;
}
