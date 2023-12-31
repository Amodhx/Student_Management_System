package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.ExamDetailDAO;
import lk.ijse.finalproject02.dao.custom.impl.ExamDetailDAOImpl;
import lk.ijse.finalproject02.entity.ExamDetail;
import lk.ijse.finalproject02.service.custom.ExamDetailService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamDetailServiceImpl implements ExamDetailService {
    ExamDetailDAO examDetailDAO = (ExamDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EXAMDETAIL);
    @Override
    public ArrayList<ExamDetailDTO> getMarksSubjectAndBatchVise(String sub) throws SQLException, ClassNotFoundException {
        ArrayList<ExamDetailDTO> examDetailDTOS = new ArrayList<>();
        for (ExamDetail e : examDetailDAO.getMarksSubjectAndBatchVise(sub)) {
            examDetailDTOS.add(new ExamDetailDTO(e.getStudentID(),e.getExamId(),e.getMarks()));
        }
        return examDetailDTOS;
    }

    @Override
    public boolean save(ExamDetailDTO examDetailDTO) throws SQLException, ClassNotFoundException {
        return examDetailDAO.save(new ExamDetail(examDetailDTO.getStudentID(),examDetailDTO.getExamId(),examDetailDTO.getMarks()));
    }
    @Override

    public ArrayList<ExamDetailDTO> getExamMarksexamVise(int examId) throws SQLException, ClassNotFoundException {
        ArrayList<ExamDetailDTO> arrayList = new ArrayList<>();
        for (ExamDetail e :
                examDetailDAO.getExamMarksexamVise(examId)) {
            arrayList.add(new ExamDetailDTO(e.getStudentID(),e.getExamId(),e.getMarks()));

        }
        return arrayList;
    }
    @Override

    public ArrayList<ExamDetailDTO> getMarksStudentVIse(int studentID) throws SQLException, ClassNotFoundException {
        ArrayList<ExamDetailDTO> arrayList = new ArrayList<>();
        for (ExamDetail e :
                examDetailDAO.getMarksStudentVIse(studentID)) {
            arrayList.add(new ExamDetailDTO(e.getStudentID(),e.getExamId(),e.getMarks()));
        }
        return arrayList;
    }

}
