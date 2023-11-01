package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.TeacherDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Teachermodel {
    public static Boolean saveTeacher(TeacherDTO teacherDTO){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT  into  teacher values(?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,teacherDTO.getFirstName());
            preparedStatement.setString(3,teacherDTO.getLastName());
            preparedStatement.setString(4,teacherDTO.getGender());
            preparedStatement.setString(5,teacherDTO.getDOB());
            preparedStatement.setString(6,teacherDTO.getSubject());
            preparedStatement.setString(7,teacherDTO.getContactNumber());
            preparedStatement.setString(8,teacherDTO.getEmail());
            preparedStatement.setString(9,teacherDTO.getNIC());
            preparedStatement.setString(10,teacherDTO.getCity());
            preparedStatement.setString(11,teacherDTO.getAddress());

            int executed = preparedStatement.executeUpdate();

            return executed > 0;
        }catch (SQLException e ){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String  getTeacherName(int id){
        String  fname = null ;
        String lname = null;
        try {
            Connection connection = DBConnection .getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select firstName,lastName from teacher where teacherID = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                fname = resultSet.getString(1);
                lname = resultSet.getString(2);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return fname + " " + lname ;
    }
    public static int getteachersCount(){
        int x = 0 ;
        try {
            Connection connection =DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(teacherID) from teacher;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                x = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return x ;
    }
    public static ArrayList<TeacherDTO> getallTeachers(){
        ArrayList<TeacherDTO> teacherDTOS = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                TeacherDTO teacherDTO = new TeacherDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11));
                teacherDTOS.add(teacherDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return teacherDTOS;
    }
}
