package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.AttendenceDTO;
import lk.ijse.finalproject02.DTO.ClassDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Attendencemodel {
    public static boolean saveAttendence(AttendenceDTO attendenceDTO){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into attendence values (?,?,?)");
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,attendenceDTO.getClassID());
            preparedStatement.setString(3,attendenceDTO.getDate());

            int is = preparedStatement.executeUpdate();

            return is>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<String> getallDates(){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select date from attendence");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String x = resultSet.getString(1);
                arrayList.add(x);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<AttendenceDTO> getallattendence(){
        ArrayList<AttendenceDTO> attendenceDTOS = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  attendence");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                AttendenceDTO attendenceDTO = new AttendenceDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                attendenceDTOS.add(attendenceDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return attendenceDTOS;
    }
}
