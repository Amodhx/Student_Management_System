package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceDetailmodel {
    public static boolean saveAttendeceDetail(AttendenceDetailDTO attendenceDetailDTO){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into attendencedetail values (?,?,?)");
            preparedStatement.setInt(1,attendenceDetailDTO.getStudentID());
            preparedStatement.setInt(2,attendenceDetailDTO.getAttendenceId());
            preparedStatement.setString(3,attendenceDetailDTO.getMark());

            int executed = preparedStatement.executeUpdate();
            return executed>0;

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<AttendenceDetailDTO> getAttendenceDetailClassVise(String classID,String date){
        ArrayList<AttendenceDetailDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select ad.* from attendencedetail ad join attendence a where ad.attendenceId = a.attendenceId and date = ? and classId = ?;");
            preparedStatement.setString(1,date);
            preparedStatement.setString(2,classID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                AttendenceDetailDTO attendenceDetailDTO = new AttendenceDetailDTO(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
                arrayList.add(attendenceDetailDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;

    }

    public static ArrayList<AttendenceDetailDTO> getAllAttendenceDetail(){
        ArrayList<AttendenceDetailDTO> arrayList = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from attendencedetail");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                AttendenceDetailDTO attendenceDetailDTO = new AttendenceDetailDTO(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
                arrayList.add(attendenceDetailDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
}
