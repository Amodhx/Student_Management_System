package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.AttendenceDTO;
import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.Attendence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendenceDAO extends CrudDAO<Attendence> {
     ArrayList<String> getallDates() throws SQLException, ClassNotFoundException;

}
