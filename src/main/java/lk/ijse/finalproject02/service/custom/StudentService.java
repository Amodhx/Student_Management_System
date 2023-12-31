package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentService extends AllService {
    public String getStudentNIC(int stuID) throws SQLException, ClassNotFoundException ;
    public String getStudentName(int id) throws SQLException, ClassNotFoundException ;
    public String getStudentBatch(int id) throws SQLException, ClassNotFoundException;
    public int getStudentId(String nic) throws SQLException, ClassNotFoundException ;
    public ArrayList<StudentDTO> getStudentClassVise(String classID) throws SQLException, ClassNotFoundException ;

    public ArrayList<StudentDTO> getAll() throws SQLException, ClassNotFoundException ;

    public int getCount() throws SQLException, ClassNotFoundException;

    public ArrayList<StudentDTO> getStudentSearching(String clsID, String searchfieldText) throws SQLException, ClassNotFoundException;

    public boolean save(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    public ArrayList<StudentDTO> getStudentBatchVise(String s) throws SQLException, ClassNotFoundException;

    public ArrayList<StudentDTO> getStudentBatchAndStreamVise(String s, String s1) throws SQLException, ClassNotFoundException;

    public ArrayList<StudentDTO> getStudentBatchAndStreamViseAndSubjectVise(String s, String s1, String s2) throws SQLException, ClassNotFoundException;

    public boolean deleteStudent(int studentid) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getBatches() throws SQLException, ClassNotFoundException;

    public StudentDTO getStudentByStudentID(int studentId) throws SQLException, ClassNotFoundException ;

    public boolean updateStudentValues(int studentId, String namefeildText, String emailfieldText, String contactfieldText, String batchFieldText) throws SQLException, ClassNotFoundException;
}
