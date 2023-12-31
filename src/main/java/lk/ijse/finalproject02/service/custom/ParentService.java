package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.ParentDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ParentService extends AllService {
    public ArrayList<ParentDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(ParentDTO parentDTO) throws SQLException, ClassNotFoundException ;

    public ParentDTO getParentbyID(int parentID) throws SQLException, ClassNotFoundException ;
}
