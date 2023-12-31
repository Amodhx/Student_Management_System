package lk.ijse.finalproject02.service.custom.impl;

import lk.ijse.finalproject02.DTO.PaymentDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.PaymentDAO;
import lk.ijse.finalproject02.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.finalproject02.entity.Payment;
import lk.ijse.finalproject02.service.custom.PaymentService;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentServiceImpl implements PaymentService {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public String totalAmountMonthVise(String toMonth) throws SQLException, ClassNotFoundException {
        return paymentDAO.totalAmountMonthVise(toMonth);
    }
    @Override
    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(paymentDTO.getPaymentId(),paymentDTO.getStudentId(),paymentDTO.getAmount(),
                paymentDTO.getMonth(),paymentDTO.getClasid()));
    }
    @Override
    public ArrayList<PaymentDTO> getAllPaymentStudentVise(int studentID) throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> arrayList = new ArrayList<>();
        for (Payment p :
                paymentDAO.getAllPaymentStudentVise(studentID) ) {
            arrayList.add(new PaymentDTO(p.getPaymentId(),p.getStudentId(),p.getAmount(),p.getMonth(),p.getClasid()));
        }
        return arrayList;
    }
}
