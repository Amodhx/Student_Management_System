package lk.ijse.finalproject02.service;

import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.SuperDAO;
import lk.ijse.finalproject02.dao.custom.impl.*;
import lk.ijse.finalproject02.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory ;

    private ServiceFactory() {
    }
    public static ServiceFactory getServiceFactory(){
        return (serviceFactory == null) ? serviceFactory =new ServiceFactory() : serviceFactory;
    }
    public enum ServiceTypes{
        ATTENDENCE,ATTENDENCEDETAIL,CLASS,CLASSDETAIL,EXAM,EXAMDETAIL,PARENT,PAYMENT,STUDENT,TEACHER,USER
    }
    public AllService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case EXAM: return new ExamServiceImpl();
            case USER: return new AdminServiceImpl();
            case CLASS: return new ClassServiceImpl();
            case PARENT: return new ParentServiceImpl();
            case PAYMENT: return new PaymentServiceImpl();
            case STUDENT: return new StudentServiceImpl();
            case TEACHER: return new TeacherServiceImpl();
            case ATTENDENCE: return new AttendenceServiceImpl();
            case EXAMDETAIL: return  new ExamDetailServiceImpl();
            case CLASSDETAIL: return new ClassDetailServiceImpl();
            case ATTENDENCEDETAIL: return new AttendenceDetailServiceImpl();
            default: return null;
        }
    }
}
