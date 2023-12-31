package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendenceDetailService extends AllService {
    public boolean saveAttendence(AttendenceDetailDTO attendenceDetailDTO) throws SQLException, ClassNotFoundException;
    public ArrayList<AttendenceDetailDTO> getAttendenceClassVise(String clasid, String date) throws SQLException, ClassNotFoundException;
}
