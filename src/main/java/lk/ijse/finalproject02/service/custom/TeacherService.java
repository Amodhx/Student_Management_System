package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherService extends AllService {
    public ArrayList<TeacherDTO> getTeacherSubVise(String sub) throws SQLException, ClassNotFoundException;

    public int getTeacherID(String name) throws SQLException, ClassNotFoundException ;
    public String getTeacherName(int id) throws SQLException, ClassNotFoundException;

    public ArrayList<TeacherDTO> getAll() throws SQLException, ClassNotFoundException ;

    public int getCount() throws SQLException, ClassNotFoundException ;

    public boolean deleteTeacher(int teaID) throws SQLException, ClassNotFoundException ;

    public Boolean save(TeacherDTO teacherDTO) throws SQLException, ClassNotFoundException;

    public boolean updateTeacher(int teacherID, String fname, String lname, String contact, String mail, String nic) throws SQLException, ClassNotFoundException ;
}
