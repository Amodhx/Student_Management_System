package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassDAO extends CrudDAO<Class> {
      boolean deleteClass(String classID) throws SQLException, ClassNotFoundException;
      int getTeacherid(String id) throws SQLException, ClassNotFoundException;
      boolean updateClass(String classID,String subject ,String batch , String fee ) throws SQLException, ClassNotFoundException;
     ArrayList<String> getsubjects(String stream) throws SQLException, ClassNotFoundException;
      String getClassFee(String clasID) throws SQLException, ClassNotFoundException;
      String getclassID(int teacherID , String batch) throws SQLException, ClassNotFoundException;
      ArrayList<Class> getclassObjStudentVIse(int studentID) throws SQLException, ClassNotFoundException;
}
