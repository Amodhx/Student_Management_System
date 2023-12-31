package lk.ijse.finalproject02.dao;

import lk.ijse.finalproject02.DTO.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T t) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    int getCount() throws SQLException, ClassNotFoundException;

}
