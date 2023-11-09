package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usermodel {
    public static boolean savUser(UserDTO user){
        Connection con;
        try {
            con= DBConnection.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement("insert into User values (0,?,?,?,?,?)");
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getType());
            ps.setString(4,user.getMail());
            ps.setString(5,user.getFullname());

            int aff=ps.executeUpdate();
            return aff>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean changePassword(String username,String password){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update user set password = ? where userName = ?");
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,username);
            int executed = preparedStatement.executeUpdate();
            return executed>0;

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<UserDTO> getAllUsers(){
        ArrayList<UserDTO> ar=new ArrayList<>();

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement("select * from User");
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                UserDTO ut=new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6) );
                ar.add(ut);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return ar;

    }
}
