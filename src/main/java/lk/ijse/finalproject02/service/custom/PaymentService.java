package lk.ijse.finalproject02.service.custom;

import lk.ijse.finalproject02.DTO.PaymentDTO;
import lk.ijse.finalproject02.service.AllService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentService extends AllService {
    public String totalAmountMonthVise(String toMonth) throws SQLException, ClassNotFoundException ;

    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException ;

    public ArrayList<PaymentDTO> getAllPaymentStudentVise(int studentID) throws SQLException, ClassNotFoundException ;
}
