package lk.ijse.finalproject02.dao.custom.impl;

import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.UserDAO;
import lk.ijse.finalproject02.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public  boolean save(User user) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("insert into User values (0,?,?,?,?,?)",user.getUserName()
                ,user.getPassword()
                ,user.getType()
                ,user.getMail()
                ,user.getFullname());
    }
    @Override
    public  boolean changePassword(String username,String password) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("update user set password = ? where userName = ?", password, username);
    }
    @Override
    public  ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =  SQLUtil.databaseConnect("select * from User");
        ArrayList<User> arrayList = new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(new User(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)));
        }
        return arrayList;
    }


    @Override
    public int getCount() {
        return 0;
    }
}
