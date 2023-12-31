package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.StudentDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.ClassDetailDAO;
import lk.ijse.finalproject02.dao.custom.impl.ClassDAOImpl;
import lk.ijse.finalproject02.dao.custom.impl.ClassDetailDAOImpl;
import lk.ijse.finalproject02.dao.custom.impl.StudentDAOImpl;
import lk.ijse.finalproject02.dao.custom.impl.TeacherDAOImpl;
import lk.ijse.finalproject02.entity.Student;
import lk.ijse.finalproject02.service.custom.StudentService;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public String getStudentNIC(int stuID) throws SQLException, ClassNotFoundException {
        return studentDAO.getStudentNIC(stuID);
    }
    @Override
    public String getStudentName(int id) throws SQLException, ClassNotFoundException {
        return studentDAO.getStudentName(id);
    }
    @Override
    public String getStudentBatch(int id) throws SQLException, ClassNotFoundException {
        return studentDAO.getStudentName(id);
    }
    @Override
    public int getStudentId(String nic) throws SQLException, ClassNotFoundException {
        return studentDAO.getStudentID(nic);

    }
    @Override
    public ArrayList<StudentDTO> getStudentClassVise(String classID) throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        for (Student s : studentDAO.getStudentClassVise(classID)) {
            arrayList.add(new StudentDTO(s.getStudentid(),s.getFirstName(),s.getLastName(),
                    s.getGender(),s.getNIC(),s.getContactnumber(),s.getEmail(),s.getParentId(),s.getBatch()));
        }
        return arrayList;
    }
    @Override

    public ArrayList<StudentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        for (Student s : studentDAO.getAll()) {
            arrayList.add(new StudentDTO(s.getStudentid(),s.getFirstName(),s.getLastName(),
                    s.getGender(),s.getNIC(),s.getContactnumber(),s.getEmail(),s.getParentId(),s.getBatch()));
        }
        return arrayList;
    }
@Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return studentDAO.getCount();
    }
    @Override

    public ArrayList<StudentDTO> getStudentSearching(String clsID, String searchfieldText) throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        for (Student s : studentDAO.getStudentSearching(clsID,searchfieldText)) {
            arrayList.add(new StudentDTO(s.getStudentid(),s.getFirstName(),s.getLastName(),
                    s.getGender(),s.getNIC(),s.getContactnumber(),s.getEmail(),s.getParentId(),s.getBatch()));
        }
        return arrayList;
    }
    @Override

    public boolean save(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.save(new Student(studentDTO.getStudentid(),studentDTO.getFirstName(),studentDTO.getLastName(),
                studentDTO.getGender(), studentDTO.getNIC(),studentDTO.getContactnumber(),studentDTO.getEmail(),
                studentDTO.getParentId(), studentDTO.getBatch()));
    }
    @Override

    public ArrayList<StudentDTO> getStudentBatchVise(String st) throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        for (Student s : studentDAO.getStudentBatchVise(st)) {
            arrayList.add(new StudentDTO(s.getStudentid(),s.getFirstName(),s.getLastName(),
                    s.getGender(),s.getNIC(),s.getContactnumber(),s.getEmail(),s.getParentId(),s.getBatch()));
        }
        return arrayList;
    }
    @Override

    public ArrayList<StudentDTO> getStudentBatchAndStreamVise(String s, String s1) throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        for (Student student : studentDAO.getStudentBatchAndStreamVise(s,s1)) {
            arrayList.add(new StudentDTO(student.getStudentid(),student.getFirstName(),student.getLastName(),
                    student.getGender(),student.getNIC(),student.getContactnumber(),student.getEmail(),
                    student.getParentId(),student.getBatch()));
        }
        return arrayList;
    }
    @Override

    public ArrayList<StudentDTO> getStudentBatchAndStreamViseAndSubjectVise(String s, String s1, String s2) throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> arrayList = new ArrayList<>();
        for (Student student : studentDAO.getStudentBatchAndStreamViseAndSubjectVise(s,s1,s2)) {
            arrayList.add(new StudentDTO(student.getStudentid(),student.getFirstName(),student.getLastName(),
                    student.getGender(),student.getNIC(),student.getContactnumber(),student.getEmail(),
                    student.getParentId(),student.getBatch()));
        }
        return arrayList;
    }
    @Override

    public boolean deleteStudent(int studentid) throws SQLException, ClassNotFoundException {
        return studentDAO.deleteStudent(studentid);
    }
    @Override
    public ArrayList<String> getBatches() throws SQLException, ClassNotFoundException {
        return studentDAO.getBatches();
    }
    @Override
    public StudentDTO getStudentByStudentID(int studentId) throws SQLException, ClassNotFoundException {
        Student studentByStudentID = studentDAO.getStudentByStudentID(studentId);
        return new StudentDTO(studentByStudentID.getStudentid(),studentByStudentID.getFirstName(),
                studentByStudentID.getLastName(),studentByStudentID.getGender(),studentByStudentID.getNIC(),
                studentByStudentID.getContactnumber(),studentByStudentID.getEmail(),studentByStudentID.getParentId(),
                studentByStudentID.getBatch());
    }
    @Override

    public boolean updateStudentValues(int studentId, String namefeildText, String emailfieldText, String contactfieldText, String batchFieldText) throws SQLException, ClassNotFoundException {
        return studentDAO.updateStudentValues(studentId,namefeildText,emailfieldText,contactfieldText,batchFieldText);
    }

}
