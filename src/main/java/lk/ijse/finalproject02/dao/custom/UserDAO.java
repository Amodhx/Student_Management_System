package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User> {
      boolean changePassword(String username,String password) throws SQLException, ClassNotFoundException;
}
