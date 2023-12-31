package lk.ijse.finalproject02.dao.custom.impl;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.StudentDAO;
import lk.ijse.finalproject02.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public  boolean save(Student student) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("insert into student values (0,?,?,?,?,?,?,?,?)",student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getNIC(),
                student.getContactnumber(),
                student.getEmail(),
                student.getParentId(),
                student.getBatch());
    }

    @Override
    public  int getStudentID(String nic) throws SQLException, ClassNotFoundException {
        int id = 0;
        ResultSet resultSet =SQLUtil.databaseConnect("select studentId from student where NIC=?",nic);
        while (resultSet.next()){
            id = resultSet.getInt(1);
        }
        return id;
    }
    @Override
    public  int getStudentId(String mail) throws SQLException, ClassNotFoundException {
        int id = 0;
        ResultSet resultSet = SQLUtil.databaseConnect("select studentId from student where email=?",mail);
        while (resultSet.next()){
            id = resultSet.getInt(1);
        }
        return id;
    }
    @Override
    public  int getCount() throws SQLException, ClassNotFoundException {
        int x  = 0;
        ResultSet rs = SQLUtil.databaseConnect("select count(studentId) from student");
        while (rs.next()){
            x = rs.getInt(1);
        }
        return x;
    }
    @Override
    public  String getStudentNIC(int studentID) throws SQLException, ClassNotFoundException {
        String NIC = null;
        ResultSet resultSet = SQLUtil.databaseConnect("select NIC from student where studentId  = ?",studentID);
        while (resultSet.next()){
            NIC = resultSet.getString(1);
        }
        return NIC;
    }
    @Override
    public  String getStudentName(int studentID) throws SQLException, ClassNotFoundException {
        String x = null;
        ResultSet resultSet = SQLUtil.databaseConnect("select firstName,lastName from student where studentId = ?",studentID);
        while (resultSet.next()) {
            x = resultSet.getString(1) + " " + resultSet.getString(2);
        }
        return x;
    }
    @Override
    public  ArrayList<Student> getStudentClassVise(String classID) throws SQLException, ClassNotFoundException {
        ArrayList<Student> arrayList = new ArrayList<>();
        ResultSet rs =SQLUtil.databaseConnect("SELECT s.* from student s join class_detail c on s.studentId = c.studentId  where classId = ?",classID);
        while (rs.next()){
            Student st = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
            arrayList.add(st);
        }
        return arrayList;
    }
    @Override
    public  boolean updateStudentValues(int studentID,String name,String email,String contacnt,String batch) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("update student set firstName = ?,contactNum = ?,email = ?,batch = ? where studentId = ?",
                name,contacnt,email,batch,studentID);
    }
    @Override
    public  boolean deleteStudent(int studentId) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("delete from student where studentId = ?", studentId);
    }
    @Override
    public  String getStudentBatch(int studentID) throws SQLException, ClassNotFoundException {
        String batch = null;
        ResultSet resultSet  = SQLUtil.databaseConnect("select batch from student where studentId = ?",studentID);
        while (resultSet.next()){
            batch = resultSet.getString(1);
        }
        return batch;
    }
    @Override
    public Student getStudentByStudentID(int studentID) throws SQLException, ClassNotFoundException {
        Student student = new Student();
        ResultSet resultSet =SQLUtil.databaseConnect("select * from student where studentId =?",studentID);
        while (resultSet.next()){
            student.setStudentid(resultSet.getInt(1));
            student.setFirstName(resultSet.getString(2));
            student.setLastName(resultSet.getString(3));
            student.setGender(resultSet.getString(4));
            student.setNIC(resultSet.getString(5));
            student.setContactnumber(resultSet.getString(6));
            student.setEmail(resultSet.getString(7));
            student.setParentId(resultSet.getInt(8));
            student.setBatch(resultSet.getString(9));
        }
        return student;
    }
    @Override
    public  ArrayList<Student> getStudentSearching(String batch,String text) throws SQLException, ClassNotFoundException {
        ArrayList<Student> arrayList = new ArrayList<>();
        ResultSet rs = SQLUtil.databaseConnect("select s.* from student s join class_detail c on s.studentId = c.studentId  where classId = ? && NIC = ?",
                batch,text);
        while (rs.next()){
            Student st = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
            arrayList.add(st);
        }
        return arrayList;
    }
    @Override
    public  ArrayList<Student> getStudentBatchVise(String batch) throws SQLException, ClassNotFoundException {
        ArrayList<Student> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select * from student where batch = ?",batch);
        while (resultSet.next()){
            Student st = new Student(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(9));
            arrayList.add(st);
        }
        return arrayList;
    }
    @Override
    public  ArrayList<Student> getStudentBatchAndStreamViseAndSubjectVise(String batch , String stream,String subject) throws SQLException, ClassNotFoundException {
        ArrayList<Student> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select " +
                "s.studentId,s.firstName,s.lastName,s.gender,s.NIC,s.contactNum,s.email,s.parentId,s.batch " +
                "from student s  join class_detail cd on s.studentId = cd.studentId" +
                " join class c on cd.classId = c.classId " +
                "where s.batch = ? && c.stream = ? && c.subject = ?" +
                "group by studentId;",batch,stream,subject);
        while (resultSet.next()){
            Student student = new Student(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(9));
            arrayList.add(student);
        }
        return arrayList;
    }
    @Override
    public  ArrayList<Student> getStudentBatchAndStreamVise(String batch , String stream) throws SQLException, ClassNotFoundException {
        ArrayList<Student> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select" +
                " s.studentId,s.firstName,s.lastName,s.gender,s.NIC,s.contactNum,s.email,s.parentId,s.batch from " +
                "student s  join class_detail cd on s.studentId = cd.studentId join class c on cd.classId = c.classId" +
                " where s.batch = ? && c.stream = ? group by studentId;",batch,stream);
        while (resultSet.next()){
            Student student = new Student(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(9));
            arrayList.add(student);
        }
        return arrayList;
    }
    @Override
    public  ArrayList<String> getBatches() throws SQLException, ClassNotFoundException {
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select distinct batch from student");
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }
    @Override
    public  ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Student> ar=new ArrayList<>();
        ResultSet rs = SQLUtil.databaseConnect("select * from student");
        while (rs.next()){
            Student st = new Student(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
            ar.add(st);
        }
        return ar;
    }
}
