package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;
import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.AttendenceDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendenceDetailDAO extends CrudDAO<AttendenceDetail> {
     ArrayList<AttendenceDetail> getAttendenceDetailClassVise(String classID, String date) throws SQLException, ClassNotFoundException;
}
