package lk.ijse.finalproject02.dao.custom.impl;

import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.ParentDAO;
import lk.ijse.finalproject02.entity.Parent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParentDAOImpl implements ParentDAO {
    @Override
    public Parent getParentbyID(int parentID) throws SQLException, ClassNotFoundException {
        Parent parent = new Parent();
        ResultSet resultSet  = SQLUtil.databaseConnect("select * from parent where parentId = ?",parentID);
        while (resultSet.next()){
            parent.setParentId(resultSet.getInt(1));
            parent.setName(resultSet.getString(2));
            parent.setContactNumber(resultSet.getString(3));
            parent.setJob(resultSet.getString(4));
            parent.setEmail(resultSet.getString(5));
        }
        return parent;
    }
    @Override
    public  boolean save(Parent parent) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("INSERT into parent values (?,?,?,?,?)",
                0,
                parent.getName(),
                parent.getContactNumber(),
                parent.getJob(),
                parent.getEmail());
    }
    @Override
    public  ArrayList<Parent> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Parent> parents = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select  * from  parent");
        while (resultSet.next()){
            Parent parent = new Parent(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            parents.add(parent);
        }
        return parents;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
