package lk.ijse.finalproject02.dao.custom.impl;

import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.ClassDAO;
import lk.ijse.finalproject02.entity.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDAOImpl implements ClassDAO {
    @Override
    public  boolean save(Class classes) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("INSERT into class values (?,?,?,?,?,?)",
                classes.getClassId(),
                classes.getSubject(),
                classes.getTeacherId(),
                classes.getGrade(),
                classes.getStream(),
                classes.getFee());
    }
    @Override
    public  int getTeacherid(String id) throws SQLException, ClassNotFoundException {
        int teacherid = 0;
        ResultSet resultSet = SQLUtil.databaseConnect("select teacherID from class where classId = ?",id);
        while (resultSet.next()){
            teacherid = resultSet.getInt(1);
        }
        return teacherid;
    }
    @Override
    public  boolean updateClass(String classID,String subject ,String batch , String fee ) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("update class set subject = ? , batch = ? , fee= ? where classId= ?;",subject,batch,fee,classID);
    }
    @Override
    public  boolean deleteClass(String classID) throws SQLException, ClassNotFoundException {
        return  SQLUtil.databaseConnect("delete from class where classId = ?",classID);
    }
    @Override
    public  ArrayList<String> getsubjects(String stream) throws SQLException, ClassNotFoundException {
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select distinct(subject) from class where stream = ? ",stream);
        while (resultSet.next()){
            String id = resultSet.getString(1);
            arrayList.add(id);
        }
        return arrayList;
    }
    @Override
    public  String getClassFee(String clasID) throws SQLException, ClassNotFoundException {
        String fee = null;
        ResultSet resultSet = SQLUtil.databaseConnect("select fee from class where classId = ?",clasID);
        while (resultSet.next()){
            fee = resultSet.getString(1);
        }
        return fee;
    }
    @Override
    public  int getCount() throws SQLException, ClassNotFoundException {
        int x = 0;
        ResultSet resultSet = SQLUtil.databaseConnect("select count(classId) from class");
        while (resultSet.next()){
            x = resultSet.getInt(1);
        }
        return x;
    }
    @Override
    public  String getclassID(int teacherID , String batch) throws SQLException, ClassNotFoundException {
        String clsid = null;
        ResultSet resultSet  = SQLUtil.databaseConnect("select classId from class where teacherID = ? && batch = ?",teacherID,batch);
        while (resultSet.next()){
            clsid = resultSet.getString(1);
        }
        return clsid;
    }
    @Override
    public  ArrayList<Class> getclassObjStudentVIse(int studentID) throws SQLException, ClassNotFoundException {
        ArrayList<Class> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select subject,batch from class c join class_detail cd on " +
                "c.classId = cd.classId where studentId = ?",
                studentID);
        while (resultSet.next()){
            Class classDTO = new Class(resultSet.getString(1),resultSet.getString(2));
            arrayList.add(classDTO);
        }
        return arrayList;
    }
    @Override
    public  ArrayList<Class> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Class> classDTOS = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select  * from  class");
        while (resultSet.next()){
            Class classDTO = new Class(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
            classDTOS.add(classDTO);
        }
        return classDTOS;
    }
}
