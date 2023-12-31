package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.Parent;

import java.sql.SQLException;

public interface ParentDAO extends CrudDAO<Parent> {
     Parent getParentbyID(int parentID) throws SQLException, ClassNotFoundException;
}
