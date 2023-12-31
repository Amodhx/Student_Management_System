package lk.ijse.finalproject02.dao.custom.impl;

import com.mysql.cj.protocol.ResultsetRow;
import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.ExamDetailDAO;
import lk.ijse.finalproject02.entity.ExamDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamDetailDAOImpl implements ExamDetailDAO {
    @Override
    public  ArrayList<ExamDetail> getMarksStudentVIse(int studentID) throws SQLException, ClassNotFoundException {
        ArrayList<ExamDetail> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select * from exam_detail where studentId = ?",studentID);
        while (resultSet.next()){
            ExamDetail examDetail = new ExamDetail(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
            arrayList.add(examDetail);
        }
        return arrayList;
    }
    @Override
    public  ArrayList<ExamDetail> getMarksSubjectAndBatchVise(String stream) throws SQLException, ClassNotFoundException {
        ArrayList<ExamDetail> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select ed.* from exam_detail ed" +
                " join exam e on e.examId = ed.examId " +
                "join class c on c.classId = e.classId where c.stream = ?",stream);
        while (resultSet.next()){
            ExamDetail examDetail = new ExamDetail(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
            arrayList.add(examDetail);
        }
        return arrayList;
    }
    @Override
    public  boolean save(ExamDetail examDetail) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("insert into exam_detail values (?,?,?)",examDetail.getStudentID(),examDetail.getExamId(),examDetail.getMarks());
    }
    @Override
    public  ArrayList<ExamDetail> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ExamDetail> examDetailDTOS = new ArrayList<>();
        ResultSet resultSet  = SQLUtil.databaseConnect("select * from exam_detail");
        while (resultSet.next()){
            ExamDetail examDetail = new ExamDetail(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
            examDetailDTOS.add(examDetail);
        }
        return examDetailDTOS;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public  ArrayList<ExamDetail> getExamMarksexamVise(int examId) throws SQLException, ClassNotFoundException {
        ArrayList<ExamDetail> arrayList = new ArrayList<>();
        ResultSet resultSet  = SQLUtil.databaseConnect("select * from exam_detail where examId =  ?",examId);
        while (resultSet.next()){
            ExamDetail examDetail = new ExamDetail(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
            arrayList.add(examDetail);
        }
        return arrayList;
    }
}
