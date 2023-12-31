package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.ExamDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamDetailDAO extends CrudDAO<ExamDetail> {
     ArrayList<ExamDetail> getMarksStudentVIse(int studentID) throws SQLException, ClassNotFoundException;
      ArrayList<ExamDetail> getMarksSubjectAndBatchVise(String stream) throws SQLException, ClassNotFoundException;
      ArrayList<ExamDetail> getExamMarksexamVise(int examId) throws SQLException, ClassNotFoundException;
}
