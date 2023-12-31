package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.ClassDetailDAO;
import lk.ijse.finalproject02.dao.custom.impl.ClassDetailDAOImpl;
import lk.ijse.finalproject02.entity.ClassDetail;
import lk.ijse.finalproject02.service.custom.ClassDetailService;

import java.sql.SQLException;

public class ClassDetailServiceImpl implements ClassDetailService {
    ClassDetailDAO classDetailDAO = (ClassDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CLASSDETAIL);
    @
    Override
    public boolean saveClassDetail(ClassDetailDTO classDetailDTO) throws SQLException, ClassNotFoundException {
        return classDetailDAO.save(new ClassDetail(classDetailDTO.getStudentId(),classDetailDTO.getClassID(),classDetailDTO.getDescription()));
    }
    @Override
    public String getclassID(int iddd) throws SQLException, ClassNotFoundException {
        return classDetailDAO.getclassID(iddd);
    }
}
