package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.ClassDTO;
import lk.ijse.finalproject02.DTO.ClassDetailDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.ClassDAO;
import lk.ijse.finalproject02.dao.custom.impl.ClassDAOImpl;
import lk.ijse.finalproject02.entity.Class;
import lk.ijse.finalproject02.service.custom.ClassService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClassServiceImpl implements ClassService {
    ClassDAO classDAO = (ClassDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CLASS);
    @Override
    public boolean saveClass(ClassDTO classDTO ) throws SQLException, ClassNotFoundException {
        return classDAO.save(new Class(classDTO.getClassId(),classDTO.getSubject(),classDTO.getTeacherId(),classDTO.getGrade(),classDTO.getStream(),classDTO.getFee()));
    }
    @Override
    public String getClassID(int teacherID , String  batch) throws SQLException, ClassNotFoundException {
        return classDAO.getclassID(teacherID, batch);
    }
    @
    Override
    public ArrayList<String> getSubjects(String stream) throws SQLException, ClassNotFoundException {
        return classDAO.getsubjects(stream);
    }
    @Override
    public ArrayList<ClassDTO> getAllClasses() throws SQLException, ClassNotFoundException {
        ArrayList<ClassDTO> arrayList = new ArrayList<>();
        for (Class c : classDAO.getAll()) {
            arrayList.add(new ClassDTO(c.getClassId(),c.getSubject(),c.getTeacherId(),c.getGrade(),c.getStream(),c.getFee()));
        }
        return arrayList;
    }
    @Override
    public int getTeacherID(String classID) throws SQLException, ClassNotFoundException {
        return classDAO.getTeacherid(classID);
    }
    @Override

    public ArrayList<ClassDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ClassDTO> arrayList = new ArrayList<>();
        for (Class c : classDAO.getAll()) {
            arrayList.add(new ClassDTO(c.getClassId(),c.getSubject(),c.getTeacherId(),c.getGrade(),c.getStream(),c.getFee()));
        }
        return arrayList;
    }
    @Override

    public boolean updateClass(String clsID, String sub, String btch, String feee) throws SQLException, ClassNotFoundException {
        return classDAO.updateClass(clsID,sub,btch,feee);
    }
    @Override

    public int getCount() throws SQLException, ClassNotFoundException {
        return classDAO.getCount();
    }
    @Override

    public boolean deleteClass(String clasID) throws SQLException, ClassNotFoundException {
        return classDAO.deleteClass(clasID);
    }
    @Override

    public String getClassFee(String clsID) throws SQLException, ClassNotFoundException {
        return classDAO.getClassFee(clsID);
    }
    @Override

    public ArrayList<ClassDTO> getclassObjStudentVIse(int studentID) throws SQLException, ClassNotFoundException {
        ArrayList<ClassDTO> arrayList = new ArrayList<>();
        for (Class c : classDAO.getclassObjStudentVIse(studentID)) {
            arrayList.add(new ClassDTO(c.getClassId(),c.getSubject(),c.getTeacherId(),c.getGrade(),c.getStream(),c.getFee()));
        }
        return arrayList;
    }

}
