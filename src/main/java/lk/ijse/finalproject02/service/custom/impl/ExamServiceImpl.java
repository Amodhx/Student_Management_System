package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.ExamDAO;
import lk.ijse.finalproject02.dao.custom.impl.ExamDAOImpl;
import lk.ijse.finalproject02.entity.Exam;
import lk.ijse.finalproject02.service.custom.ExamService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamServiceImpl implements ExamService {
    ExamDAO examDAO = (ExamDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXAM);
    @Override
    public boolean save(ExamDTO examDTO) throws SQLException, ClassNotFoundException {
        return examDAO.save(new Exam(examDTO.getExamId(),examDTO.getClassId(),examDTO.getDate()));
    }
    @Override
    public ArrayList<ExamDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ExamDTO> arrayList = new ArrayList<>();
        for (Exam e :
                examDAO.getAll()) {
            arrayList.add(new ExamDTO(e.getExamId(),e.getClassId(),e.getDate()));
        }
        return arrayList;
    }
    @Override
    public String getclassID(int examId) throws SQLException, ClassNotFoundException {
        return examDAO.getclassID(examId);
    }
}
