package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassService extends AllService {
    public boolean saveClass(ClassDTO classDTO ) throws SQLException, ClassNotFoundException ;
    public String getClassID(int teacherID , String  batch) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getSubjects(String stream) throws SQLException, ClassNotFoundException ;
    public ArrayList<ClassDTO> getAllClasses() throws SQLException, ClassNotFoundException;
    public int getTeacherID(String classID) throws SQLException, ClassNotFoundException ;

    public ArrayList<ClassDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean updateClass(String clsID, String sub, String btch, String feee) throws SQLException, ClassNotFoundException;

    public int getCount() throws SQLException, ClassNotFoundException ;

    public boolean deleteClass(String clasID) throws SQLException, ClassNotFoundException;

    public String getClassFee(String clsID) throws SQLException, ClassNotFoundException;

    public ArrayList<ClassDTO> getclassObjStudentVIse(int studentID) throws SQLException, ClassNotFoundException;
}
