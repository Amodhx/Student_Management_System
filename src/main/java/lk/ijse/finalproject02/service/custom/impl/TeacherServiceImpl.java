package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.TeacherDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.TeacherDAO;
import lk.ijse.finalproject02.dao.custom.impl.TeacherDAOImpl;
import lk.ijse.finalproject02.entity.Teacher;
import lk.ijse.finalproject02.service.custom.TeacherService;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherServiceImpl implements TeacherService {
    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TEACHER);
    @Override
    public ArrayList<TeacherDTO> getTeacherSubVise(String sub) throws SQLException, ClassNotFoundException {
        ArrayList<TeacherDTO> arrayList = new ArrayList<>();
        for (Teacher t :teacherDAO.getteachersSubjectVise(sub)) {
            arrayList.add(new TeacherDTO(t.getTeacherId(),t.getFirstName(),t.getLastName(),t.getGender(),
                    t.getDOB(),t.getSubject(),t.getContactNumber(),t.getEmail(),t.getNIC(),t.getCity(),
                    t.getAddress()));
        }
        return arrayList;
    }
    @Override
    public int getTeacherID(String name) throws SQLException, ClassNotFoundException {
        return teacherDAO.getTeacherId(name);
    }
    @Override
    public String getTeacherName(int id) throws SQLException, ClassNotFoundException {
        return teacherDAO.getTeacherName(id);
    }
    @Override
    public ArrayList<TeacherDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<TeacherDTO> arrayList = new ArrayList<>();
        for (Teacher t :teacherDAO.getAll()) {
            arrayList.add(new TeacherDTO(t.getTeacherId(),t.getFirstName(),t.getLastName(),t.getGender(),
                    t.getDOB(),t.getSubject(),t.getContactNumber(),t.getEmail(),t.getNIC(),t.getCity(),
                    t.getAddress()));
        }
        return arrayList;
    }
    @Override

    public int getCount() throws SQLException, ClassNotFoundException {
        return teacherDAO.getCount();
    }
    @Override

    public boolean deleteTeacher(int teaID) throws SQLException, ClassNotFoundException {
        return teacherDAO.deleteTeacher(teaID);
    }
    @Override
    public Boolean save(TeacherDTO teacherDTO) throws SQLException, ClassNotFoundException {
        return teacherDAO.save(new Teacher(teacherDTO.getTeacherId(),teacherDTO.getFirstName(),
                teacherDTO.getLastName(),teacherDTO.getGender(),teacherDTO.getDOB(),teacherDTO.getSubject(),
                teacherDTO.getContactNumber(),teacherDTO.getEmail(),teacherDTO.getNIC(),teacherDTO.getCity(),teacherDTO.getAddress()));
    }
    @Override

    public boolean updateTeacher(int teacherID, String fname, String lname, String contact, String mail, String nic) throws SQLException, ClassNotFoundException {
        return teacherDAO.updateTeacher(teacherID,fname,lname,contact,mail,nic);
    }
}
