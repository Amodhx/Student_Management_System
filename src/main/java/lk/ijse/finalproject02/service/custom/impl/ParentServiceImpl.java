package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.ParentDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.ParentDAO;
import lk.ijse.finalproject02.dao.custom.impl.ParentDAOImpl;
import lk.ijse.finalproject02.entity.Parent;
import lk.ijse.finalproject02.service.custom.ParentService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ParentServiceImpl implements ParentService {
    ParentDAO parentDAO = (ParentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PARENT);
    @Override
    public ArrayList<ParentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ParentDTO> arrayList = new ArrayList<>();
        for (Parent p :
                parentDAO.getAll()) {
            arrayList.add(new ParentDTO(p.getParentId(),p.getName(),p.getContactNumber(),p.getJob(),p.getEmail()));
        }
        return arrayList;
    }
    @Override
    public boolean save(ParentDTO parentDTO) throws SQLException, ClassNotFoundException {
        return parentDAO.save(new Parent(parentDTO.getParentId(),parentDTO.getName(),
                parentDTO.getContactNumber(),parentDTO.getJob(),parentDTO.getEmail()));
    }
    @Override
    public ParentDTO getParentbyID(int parentID) throws SQLException, ClassNotFoundException {
        Parent parentbyID = parentDAO.getParentbyID(parentID);
        return new ParentDTO(parentbyID.getParentId(),parentbyID.getName(),parentbyID.getContactNumber(),
                parentbyID.getJob(),parentbyID.getEmail());
    }
}
