package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.ExamDetailDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamDetailService  extends AllService {
    public ArrayList<ExamDetailDTO> getMarksSubjectAndBatchVise(String sub) throws SQLException, ClassNotFoundException ;

    public boolean save(ExamDetailDTO examDetailDTO) throws SQLException, ClassNotFoundException ;

    public ArrayList<ExamDetailDTO> getExamMarksexamVise(int examId) throws SQLException, ClassNotFoundException ;

    public ArrayList<ExamDetailDTO> getMarksStudentVIse(int studentID) throws SQLException, ClassNotFoundException ;
}
