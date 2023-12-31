package lk.ijse.finalproject02.dao;

import lk.ijse.finalproject02.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T databaseConnect(String sql , Object... objects) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject((i+1),objects[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")){
           return (T) preparedStatement.executeQuery();
        }else {
           return (T) (Boolean)(preparedStatement.executeUpdate() > 0) ;
        }
    }
}
