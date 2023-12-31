package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamDAO extends CrudDAO<Exam> {
      String getclassID(int examid) throws SQLException, ClassNotFoundException;
}
