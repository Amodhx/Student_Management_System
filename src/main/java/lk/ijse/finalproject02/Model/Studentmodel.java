package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.StudentDTO;

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
            PreparedStatement ps=con.prepareStatement("insert into student values (0,?,?,?,?,?,?,?)");
            ps.setString(1,student.getFirstName());
            ps.setString(2,student.getLastName());
            ps.setString(3,student.getGender());
            ps.setString(4,student.getNIC());
            ps.setString(5,student.getContactnumber());
            ps.setString(6,student.getEmail());
            ps.setInt(7,student.getParentId());

            int aff=ps.executeUpdate();
            return aff>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static int getStudentCount(){
        int x  = 0;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(NIC) from student group by NIC;");
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
                StudentDTO st = new StudentDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
                arrayList.add(st);
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
                StudentDTO st = new StudentDTO(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8));
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
