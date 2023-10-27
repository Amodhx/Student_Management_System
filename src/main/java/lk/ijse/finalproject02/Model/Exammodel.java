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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into exam values (?,?,?,?)");
            preparedStatement.setInt(1,examDTO.getExamId());
            preparedStatement.setInt(2,examDTO.getStudentId());
            preparedStatement.setString(3,examDTO.getDate());
            preparedStatement.setInt(4,examDTO.getMarks());

            int is = preparedStatement.executeUpdate();

            return is > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<ExamDTO> getallexam(){
        ArrayList<ExamDTO> examDTOS = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  exam");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ExamDTO examDTO = new ExamDTO(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4));
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
