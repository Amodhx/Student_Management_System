package lk.ijse.finalproject02.service.custom;

import com.beust.ah.A;
import lk.ijse.finalproject02.DTO.AttendenceDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendenceService extends AllService {
    public ArrayList<AttendenceDTO> getAllAttendence() throws SQLException, ClassNotFoundException ;
    public boolean saveAttendence(AttendenceDTO attendenceDTO ) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllDates() throws SQLException, ClassNotFoundException ;
}
