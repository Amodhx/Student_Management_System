package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student> {
      boolean deleteStudent(int studentId) throws SQLException, ClassNotFoundException;

      int getStudentID(String nic) throws SQLException, ClassNotFoundException;

      int getStudentId(String mail) throws SQLException, ClassNotFoundException;

      String getStudentNIC(int studentID) throws SQLException, ClassNotFoundException;
      String getStudentName(int studentID) throws SQLException, ClassNotFoundException;
     ArrayList<Student> getStudentClassVise(String classID) throws SQLException, ClassNotFoundException;
      boolean updateStudentValues(int studentID,String name,String email,String contacnt,String batch) throws SQLException, ClassNotFoundException;
      String getStudentBatch(int studentID) throws SQLException, ClassNotFoundException;
      Student getStudentByStudentID(int studentID) throws SQLException, ClassNotFoundException;
      ArrayList<Student> getStudentSearching(String batch,String text) throws SQLException, ClassNotFoundException;
      ArrayList<Student> getStudentBatchVise(String batch) throws SQLException, ClassNotFoundException;
      ArrayList<Student> getStudentBatchAndStreamViseAndSubjectVise(String batch , String stream,String subject) throws SQLException, ClassNotFoundException;
      ArrayList<Student> getStudentBatchAndStreamVise(String batch , String stream) throws SQLException, ClassNotFoundException;
      ArrayList<String> getBatches() throws SQLException, ClassNotFoundException;

}
