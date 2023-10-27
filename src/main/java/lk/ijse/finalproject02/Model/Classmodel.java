package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ClassDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Classmodel {
    public static boolean saveclass(ClassDTO classDTO){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into class values (?,?,?,?)");
            preparedStatement.setString(1,classDTO.getClassId());
            preparedStatement.setString(2,classDTO.getSubject());
            preparedStatement.setInt(3,classDTO.getTeacherId());
            preparedStatement.setString(4,classDTO.getGrade());

            int is = preparedStatement.executeUpdate();

            return is>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static int getClassCount(){
        int x = 0;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(classId) from class group by classId;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                x = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return x;
    }
    public static ArrayList<ClassDTO> getallClasses(){
        ArrayList<ClassDTO> classDTOS = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  class");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClassDTO classDTO = new ClassDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4));
                classDTOS.add(classDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return classDTOS;
    }
}
