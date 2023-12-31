package lk.ijse.finalproject02.dao;

import lk.ijse.finalproject02.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory factory;
    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory(){
        return (factory == null) ? factory =new DAOFactory() : factory;
    }
    public enum DAOTypes{
        ATTENDENCE,ATTENDENCEDETAIL,CLASS,CLASSDETAIL,EXAM,EXAMDETAIL,PARENT,PAYMENT,STUDENT,TEACHER,USER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case EXAM: return new ExamDAOImpl();
            case USER: return new UserDAOImpl();
            case CLASS: return new ClassDAOImpl();
            case PARENT: return new ParentDAOImpl();
            case PAYMENT: return new PaymentDAOImpl();
            case STUDENT: return new StudentDAOImpl();
            case TEACHER: return new TeacherDAOImpl();
            case ATTENDENCE: return new AttendenceDAOImpl();
            case EXAMDETAIL: return  new ExamDetailDAOImpl();
            case CLASSDETAIL: return new ClassDetailDAOImpl();
            case ATTENDENCEDETAIL: return new AttendenceDetailDAOImpl();
            default: return null;
        }
    }


}
