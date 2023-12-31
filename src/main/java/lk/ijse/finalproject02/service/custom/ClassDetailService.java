package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;

public interface ClassDetailService extends AllService {
    public boolean saveClassDetail(ClassDetailDTO classDetailDTO) throws SQLException, ClassNotFoundException ;

    public String getclassID(int iddd) throws SQLException, ClassNotFoundException ;
}
