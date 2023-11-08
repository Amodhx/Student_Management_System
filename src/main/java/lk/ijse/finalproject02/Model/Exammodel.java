package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.ExamDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exammodel {
    public static boolean saveexam(ExamDTO examDTO){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into exam values (?,?,?)");
            preparedStatement.setInt(1,examDTO.getExamId());
            preparedStatement.setString(2,examDTO.getClassId());
            preparedStatement.setString(3,examDTO.getDate());

            int is = preparedStatement.executeUpdate();

            return is > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static String getclassID(int examid){
      String classid = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select classId from exam where examId = ?");
            preparedStatement.setInt(1,examid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                classid = resultSet.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return classid;
    }
    public static ArrayList<ExamDTO> getallexam(){
        ArrayList<ExamDTO> examDTOS = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  exam");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ExamDTO examDTO = new ExamDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                examDTOS.add(examDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return examDTOS;
    }

}
