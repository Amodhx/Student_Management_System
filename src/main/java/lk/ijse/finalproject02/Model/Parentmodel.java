package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.ParentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Parentmodel {
    public static boolean saveParent(ParentDTO parentDTO){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into parent values (?,?,?,?,?)");
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,parentDTO.getName());
            preparedStatement.setString(3,parentDTO.getContactNumber());
            preparedStatement.setString(4,parentDTO.getJob());
            preparedStatement.setString(5,parentDTO.getEmail());

            int is = preparedStatement.executeUpdate();

            return is>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<ParentDTO> getallParent(){
        ArrayList<ParentDTO> parentDTOS = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  parent");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ParentDTO parentDTO = new ParentDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
                parentDTOS.add(parentDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return parentDTOS;
    }
}
