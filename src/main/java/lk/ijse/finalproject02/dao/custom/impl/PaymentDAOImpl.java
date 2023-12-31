package lk.ijse.finalproject02.dao.custom.impl;

import lk.ijse.finalproject02.DTO.PaymentDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.PaymentDAO;
import lk.ijse.finalproject02.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public  boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("INSERT into payment values (?,?,?,?,?)",0,
                payment.getStudentId(),
                payment.getAmount(),
                payment.getMonth(),
                payment.getClasid());
    }
    @Override
    public  String totalAmountMonthVise(String month) throws SQLException, ClassNotFoundException {
        String total = null;
        ResultSet resultSet = SQLUtil.databaseConnect("select sum(amount) from payment where month = ?",month);
        while (resultSet.next()){
            total = resultSet.getString(1);
        }
        return total;
    }
    @Override
    public  ArrayList<Payment> getAllPaymentStudentVise(int stuid) throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select * from payment where studentId = ?",stuid);
        while (resultSet.next()){
            Payment payment = new Payment(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            payments.add(payment);
        }
        return payments;
    }
    @Override
    public  ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select  * from  payment");
        while (resultSet.next()){
            Payment payment = new Payment(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            payments.add(payment);
        }
        return payments;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
