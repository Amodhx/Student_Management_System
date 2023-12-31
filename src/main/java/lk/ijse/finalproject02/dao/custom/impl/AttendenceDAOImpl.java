package lk.ijse.finalproject02.dao.custom.impl;

import lk.ijse.finalproject02.DTO.AttendenceDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.AttendenceDAO;
import lk.ijse.finalproject02.entity.Attendence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceDAOImpl implements AttendenceDAO {
    @Override
    public  boolean save(Attendence attendence) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("INSERT into attendence values (?,?,?)",0,
                attendence.getClassID(),
                attendence.getDate());
    }
    @Override
    public  ArrayList<String> getallDates() throws SQLException, ClassNotFoundException {
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select date from attendence");
        while (resultSet.next()){
            String x = resultSet.getString(1);
            arrayList.add(x);
        }
        return arrayList;
    }
    @Override
    public  ArrayList<Attendence> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Attendence> attendenceDTOS = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select  * from  attendence");
        while (resultSet.next()){
            Attendence attendence = new Attendence(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            attendenceDTOS.add(attendence);
        }
        return attendenceDTOS;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
