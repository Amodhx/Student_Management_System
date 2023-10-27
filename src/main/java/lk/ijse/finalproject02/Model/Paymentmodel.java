package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Paymentmodel {
    public static boolean savepayment(PaymentDTO paymentDTO){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into class values (?,?,?,?)");
            preparedStatement.setInt(1,0);
            preparedStatement.setInt(2,paymentDTO.getStudentId());
            preparedStatement.setDouble(3,paymentDTO.getAmount());
            preparedStatement.setString(4,paymentDTO.getDate());

            int is = preparedStatement.executeUpdate();

            return is>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<PaymentDTO> getallPayment(){
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  payment");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                PaymentDTO paymentDTO = new PaymentDTO(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDouble(3),resultSet.getString(4));
                paymentDTOS.add(paymentDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return paymentDTOS;
    }
}
