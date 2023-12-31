package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.AttendenceDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.AttendenceDAO;
import lk.ijse.finalproject02.dao.custom.impl.AttendenceDAOImpl;
import lk.ijse.finalproject02.entity.Attendence;
import lk.ijse.finalproject02.service.custom.AttendenceService;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceServiceImpl implements AttendenceService {
    AttendenceDAO attendenceDAO = (AttendenceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDENCE);

    @Override
    public ArrayList<AttendenceDTO> getAllAttendence() throws SQLException, ClassNotFoundException {
        ArrayList<AttendenceDTO> arrayList = new ArrayList<>();
        for (Attendence a :attendenceDAO.getAll()) {
            arrayList.add(new AttendenceDTO(a.getAttendenceId(),a.getClassID(),a.getDate()));
        }
        return arrayList;
    }
    @Override
    public boolean saveAttendence(AttendenceDTO attendenceDTO ) throws SQLException, ClassNotFoundException {
        return attendenceDAO.save(new Attendence(attendenceDTO.getAttendenceId(),attendenceDTO.getClassID(),attendenceDTO.getDate()));
    }
    @Override
    public ArrayList<String> getAllDates() throws SQLException, ClassNotFoundException {
        return attendenceDAO.getallDates();
    }
}
