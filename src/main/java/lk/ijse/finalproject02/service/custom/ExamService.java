package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.ExamDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamService  extends AllService {
    public boolean save(ExamDTO examDTO) throws SQLException, ClassNotFoundException ;

    public ArrayList<ExamDTO> getAll() throws SQLException, ClassNotFoundException;

    public String getclassID(int examId) throws SQLException, ClassNotFoundException ;
}
