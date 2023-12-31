package lk.ijse.finalproject02.dao.custom.impl;
import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.TeacherDAO;
import lk.ijse.finalproject02.entity.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public  ArrayList<String> getSubject() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.databaseConnect("select subject from teacher group by subject");
        ArrayList<String> arrayList = new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }
    @Override
    public  boolean deleteTeacher(int teacherID) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("delete from teacher where teacherID = ?",teacherID);
    }
    @Override
    public  boolean updateTeacher(int teacherID, String firstName, String lastName , String contact , String email,String nic) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("update  teacher set firstName = ?,lastName =?, contactNum = ?, email = ?, nic =? where teacherID = ?",firstName,lastName,contact,email,nic,teacherID);
    }
    @Override
    public  boolean save(Teacher teacher) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("INSERT  into  teacher values(?,?,?,?,?,?,?,?,?,?,?)", 0,
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getGender(),
                teacher.getDOB(),
                teacher.getSubject(),
                teacher.getContactNumber(),
                teacher.getEmail(),
                teacher.getNIC(),
                teacher.getCity(),
                teacher.getAddress());

    }
    @Override
    public  int getTeacherId(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.databaseConnect("select teacherID from teacher where firstName = ?",name);
        int id = 0 ;
        while (resultSet.next()){
            id =resultSet.getInt(1);
        }
        return id;
    }
    @Override
    public  String  getTeacherName(int id) throws SQLException, ClassNotFoundException {
        String  fname = null ;
        String lname = null;
        ResultSet resultSet = SQLUtil.databaseConnect("select firstName,lastName from teacher where teacherID = ?",id);
        while (resultSet.next()){
            fname = resultSet.getString(1);
            lname = resultSet.getString(2);
        }
        return fname + " " + lname ;
    }
    @Override
    public  ArrayList<Teacher> getteachersSubjectVise(String subject) throws SQLException, ClassNotFoundException {
        ArrayList<Teacher> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select * from teacher where subject = ?",subject);
        while (resultSet.next()){
            Teacher teacher = new Teacher(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11));
            arrayList.add(teacher);
        }
        return arrayList;
    }
    @Override
    public  int getCount() throws SQLException, ClassNotFoundException {
        int x = 0 ;
        ResultSet resultSet = SQLUtil.databaseConnect("select count(teacherID) from teacher");
        while (resultSet.next()){
            x = resultSet.getInt(1);
        }
        return x ;
    }
    @Override
    public  ArrayList<Teacher> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher> teachers = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select * from teacher");
        while (resultSet.next()){
            Teacher teacher = new Teacher(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11));
            teachers.add(teacher);
        }
        return teachers;
    }
}
