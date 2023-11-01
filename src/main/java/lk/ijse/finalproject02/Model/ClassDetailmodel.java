package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;

import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDetailmodel {
    public static Boolean saveClassDetail(ClassDetailDTO classDetailDTO){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into class_detail values (?,?,?)");
            preparedStatement.setInt(1,classDetailDTO.getStudentId());
            preparedStatement.setString(2,classDetailDTO.getClassID());
            preparedStatement.setString(3,classDetailDTO.getDescription());
            int executed = preparedStatement.executeUpdate();

            return executed>0;

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static String getclassID(int studentID){
        String classID = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select classId from class_detail where studentId = ?");
            preparedStatement.setInt(1,studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                classID = resultSet.getString(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return classID;
    }

    public static ArrayList<ClassDetailDTO> getAllClassDetails(){
        ArrayList<ClassDetailDTO> classDetailDTOS = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from class_detail");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClassDetailDTO classDetailDTO = new ClassDetailDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                classDetailDTOS.add(classDetailDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return classDetailDTOS;
    }
}
