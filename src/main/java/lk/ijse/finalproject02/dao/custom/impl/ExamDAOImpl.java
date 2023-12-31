package lk.ijse.finalproject02.dao.custom.impl;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.ExamDAO;
import lk.ijse.finalproject02.entity.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamDAOImpl implements ExamDAO {
    @Override
    public  boolean save(Exam exam) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("INSERT into exam values (?,?,?)",exam.getExamId(),exam.getClassId(),exam.getDate());
    }
    @Override
    public  String getclassID(int examid) throws SQLException, ClassNotFoundException {
        String classid = null;
        ResultSet resultSet = SQLUtil.databaseConnect("select classId from exam where examId = ?",examid);
        while (resultSet.next()){
            classid = resultSet.getString(1);
        }
        return classid;
    }
    @Override
    public  ArrayList<Exam> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Exam> examDTOS = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select  * from  exam");
        while (resultSet.next()){
            Exam exam = new Exam(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            examDTOS.add(exam);
        }
        return examDTOS;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
