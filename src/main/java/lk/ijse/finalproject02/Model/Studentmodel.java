package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.StudentDTO;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Studentmodel {
    public static boolean savStudent(StudentDTO student){
        Connection con;
        try {
            con= DBConnection.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement("insert into student values (0,?,?,?,?,?,?,?,?)");
            ps.setString(1,student.getFirstName());
            ps.setString(2,student.getLastName());
            ps.setString(3,student.getGender());
            ps.setString(4,student.getNIC());
            ps.setString(5,student.getContactnumber());
            ps.setString(6,student.getEmail());
            ps.setInt(7,student.getParentId());
            ps.setString(8,student.getBatch());

            int aff=ps.executeUpdate();
            return aff>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static int getStudentID(String nic){
        int id = 0;

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select studentId from student where NIC=?");
            preparedStatement.setString(1, nic);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return id;
    }

    public static int getStudentId(String mail){
        int id = 0;

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select studentId from student where email=?");
            preparedStatement.setString(1,mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return id;
    }
    public static int getStudentCount(){
        int x  = 0;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(studentId) from student");
            ResultSet rs =preparedStatement.executeQuery();
            while (rs.next()){
                 x = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return x;
    }
    public static String getStudentNIC(int studentID){
        String NIC = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" select NIC from student where studentId  = ?");
            preparedStatement.setInt(1,studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                NIC = resultSet.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return NIC;
    }
    public static String getStudentName(int studentID){
        String x = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" select firstName,lastName from student where studentId = ?");
            preparedStatement.setInt(1,studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                x = resultSet.getString(1)+" "+resultSet.getString(2);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return x;
    }
    public static  ArrayList<StudentDTO> getStudentClassVise(String classID){
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" select s.* from student s join class_detail c on s.studentId = c.studentId  where classId = ?");
            preparedStatement.setString(1,classID);
            ResultSet rs =preparedStatement.executeQuery();
            while (rs.next()){
                StudentDTO st = new StudentDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
                arrayList.add(st);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static boolean updateStudentValues(int studentID,String name,String email,String contacnt,String batch){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update student set firstName = ?,contactNum = ?,email = ?,batch = ? where studentId = ?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,contacnt);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,batch);
            preparedStatement.setInt(5,studentID);
            int executed = preparedStatement.executeUpdate();
            return executed>0;

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;

    }
    public static boolean deleteStudent(int studentId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from student where studentId = ?");
            preparedStatement.setInt(1,studentId);

            int executed = preparedStatement.executeUpdate();
            return executed > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static String getStudentBatch(int studentID){
        String batch = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select batch from student where studentId = ?");
            preparedStatement.setInt(1,studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                batch = resultSet.getString(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return batch;
    }
    public static StudentDTO getStudentByStudentID(int studentID){
        StudentDTO studentDTO = new StudentDTO();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student where studentId =?");
            preparedStatement.setInt(1,studentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                studentDTO.setStudentid(resultSet.getInt(1));
                studentDTO.setFirstName(resultSet.getString(2));
                studentDTO.setLastName(resultSet.getString(3));
                studentDTO.setGender(resultSet.getString(4));
                studentDTO.setNIC(resultSet.getString(5));
                studentDTO.setContactnumber(resultSet.getString(6));
                studentDTO.setEmail(resultSet.getString(7));
                studentDTO.setParentId(resultSet.getInt(8));
                studentDTO.setBatch(resultSet.getString(9));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return studentDTO;
    }
    public static ArrayList<StudentDTO> getStudentSearching(String batch,String text){
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" select s.* from student s join class_detail c on s.studentId = c.studentId  where classId = ? && NIC = ?");
            preparedStatement.setString(1,batch);
            preparedStatement.setString(2,text);
            ResultSet rs =preparedStatement.executeQuery();
            while (rs.next()){
                StudentDTO st = new StudentDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
                arrayList.add(st);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static ArrayList<StudentDTO> getStudentBatchVise(String batch){
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student where batch = ?");
            preparedStatement.setString(1,batch);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StudentDTO st = new StudentDTO(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(9));
                arrayList.add(st);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static ArrayList<StudentDTO> getStudentBatchAndStreamViseAndSubjectVise(String batch , String stream,String subject){
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select " +
                    "s.studentId,s.firstName,s.lastName,s.gender,s.NIC,s.contactNum,s.email,s.parentId,s.batch " +
                    "from student s  join class_detail cd on s.studentId = cd.studentId" +
                    " join class c on cd.classId = c.classId " +
                    "where s.batch = ? && c.stream = ? && c.subject = ?" +
                    "group by studentId;");
            preparedStatement.setString(1,batch);
            preparedStatement.setString(2,stream);
            preparedStatement.setString(3,subject);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StudentDTO studentDTO = new StudentDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(9));
                arrayList.add(studentDTO);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static ArrayList<StudentDTO> getStudentBatchAndStreamVise(String batch , String stream){
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select" +
                    " s.studentId,s.firstName,s.lastName,s.gender,s.NIC,s.contactNum,s.email,s.parentId,s.batch from " +
                    "student s  join class_detail cd on s.studentId = cd.studentId join class c on cd.classId = c.classId" +
                    " where s.batch = ? && c.stream = ? group by studentId;");
            preparedStatement.setString(1,batch);
            preparedStatement.setString(2,stream);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StudentDTO studentDTO = new StudentDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(9));
                arrayList.add(studentDTO);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static ArrayList<String> getBatches(){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select distinct batch from student");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                arrayList.add(resultSet.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static ArrayList<StudentDTO> getAllStudents(){
        ArrayList<StudentDTO> ar=new ArrayList<>();

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement("select * from student");
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                StudentDTO st = new StudentDTO(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
                ar.add(st);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return ar;

    }
}
