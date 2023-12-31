package lk.ijse.finalproject02.dao.custom.impl;

import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.ClassDetailDAO;
import lk.ijse.finalproject02.entity.ClassDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDetailDAOImpl implements ClassDetailDAO {
    @Override
    public  boolean save(ClassDetail classDetail) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("insert into class_detail values (?,?,?)",classDetail.getStudentId(),classDetail.getClassID(),classDetail.getDescription());
    }
    @Override
    public  String getclassID(int studentID) throws SQLException, ClassNotFoundException {
        String classID = null;
        ResultSet resultSet = SQLUtil.databaseConnect("select classId from class_detail where studentId = ?",studentID);
        while (resultSet.next()){
            classID = resultSet.getString(1);
        }
        return classID;
    }
    @Override

    public  ArrayList<ClassDetail> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ClassDetail> classDetailDTOS = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select * from class_detail");
        while (resultSet.next()){
            ClassDetail classDetail= new ClassDetail(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            classDetailDTOS.add(classDetail);
        }
        return classDetailDTOS;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
