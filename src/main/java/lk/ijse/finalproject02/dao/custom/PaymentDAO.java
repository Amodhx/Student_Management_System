package lk.ijse.finalproject02.dao.custom;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.PaymentDTO;
import lk.ijse.finalproject02.dao.CrudDAO;
import lk.ijse.finalproject02.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO  extends CrudDAO<Payment> {
      String totalAmountMonthVise(String month) throws SQLException, ClassNotFoundException;
     ArrayList<Payment> getAllPaymentStudentVise(int stuid) throws SQLException, ClassNotFoundException;
}
