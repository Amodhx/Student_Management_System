package lk.ijse.finalproject02.service.custom;

import javafx.scene.control.Alert;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminService extends AllService {
    public boolean saveUser(UserDTO userDTO);
    public ArrayList<UserDTO> getAllUsers();

    public boolean changePassword(String usernameText, String passwordText) throws SQLException, ClassNotFoundException;
}
