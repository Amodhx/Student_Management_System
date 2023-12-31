package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherDAO extends CrudDAO<Teacher>{
     boolean deleteTeacher(int id) throws SQLException, ClassNotFoundException;
     ArrayList<String> getSubject() throws SQLException, ClassNotFoundException;
      boolean updateTeacher(int teacherID, String firstName, String lastName , String contact , String email,String nic) throws SQLException, ClassNotFoundException;
      int getTeacherId(String name) throws SQLException, ClassNotFoundException;
      String  getTeacherName(int id) throws SQLException, ClassNotFoundException;
      ArrayList<Teacher> getteachersSubjectVise(String subject) throws SQLException, ClassNotFoundException;
}
