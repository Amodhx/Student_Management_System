package lk.ijse.finalproject02.Model;

import javafx.fxml.FXMLLoader;
import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamDetailmodel {
    public static boolean saveExamDetail(ExamDetailDTO examDetailDTO){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into exam_detail values (?,?,?)");
            preparedStatement.setInt(1,examDetailDTO.getStudentID());
            preparedStatement.setInt(2,examDetailDTO.getExamId());
            preparedStatement.setString(3,examDetailDTO.getMarks());
            int executed = preparedStatement.executeUpdate();
            return executed>0;

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<ExamDetailDTO> getallExamDetail(){
        ArrayList<ExamDetailDTO> examDetailDTOS = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from exam_detail");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ExamDetailDTO examDetailDTO = new ExamDetailDTO(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
                examDetailDTOS.add(examDetailDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return examDetailDTOS;
    }
    public static ArrayList<ExamDetailDTO> getExamMarksexamVise(int examId){
        ArrayList<ExamDetailDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from exam_detail where examId =  ?");
            preparedStatement.setInt(1,examId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ExamDetailDTO examDetailDTO = new ExamDetailDTO(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
                arrayList.add(examDetailDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
}
