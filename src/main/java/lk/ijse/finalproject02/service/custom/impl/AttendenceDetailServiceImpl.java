package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.AttendenceDetailDAO;
import lk.ijse.finalproject02.dao.custom.impl.AttendenceDetailDAOImpl;
import lk.ijse.finalproject02.entity.AttendenceDetail;
import lk.ijse.finalproject02.service.custom.AttendenceDetailService;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceDetailServiceImpl implements AttendenceDetailService {
    AttendenceDetailDAO attendenceDetailDAO = (AttendenceDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDENCEDETAIL);
    @Override
    public boolean saveAttendence(AttendenceDetailDTO attendenceDetailDTO) throws SQLException, ClassNotFoundException {
        return attendenceDetailDAO.save(new AttendenceDetail(attendenceDetailDTO.getStudentID(),attendenceDetailDTO.getAttendenceId(),attendenceDetailDTO.getMark()));
    }
    @Override
    public ArrayList<AttendenceDetailDTO> getAttendenceClassVise(String clasid, String date) throws SQLException, ClassNotFoundException {
        ArrayList<AttendenceDetailDTO> arrayList = new ArrayList<>();
        for (AttendenceDetail a : attendenceDetailDAO.getAttendenceDetailClassVise(clasid,date)) {
            arrayList.add(new AttendenceDetailDTO(a.getStudentID(),a.getAttendenceId(),a.getMark()));
        }
        return arrayList;
    }
}
