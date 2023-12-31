package lk.ijse.finalproject02.dao.custom.impl;

import lk.ijse.finalproject02.DTO.AttendenceDetailDTO;
import lk.ijse.finalproject02.dao.SQLUtil;
import lk.ijse.finalproject02.dao.custom.AttendenceDetailDAO;
import lk.ijse.finalproject02.entity.AttendenceDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendenceDetailDAOImpl implements AttendenceDetailDAO {
    @Override
    public  boolean save(AttendenceDetail attendenceDetail) throws SQLException, ClassNotFoundException {
        return SQLUtil.databaseConnect("insert into attendencedetail values (?,?,?)",
                attendenceDetail.getStudentID(),
                attendenceDetail.getAttendenceId(),
                attendenceDetail.getMark());
    }
    @Override
    public  ArrayList<AttendenceDetail> getAttendenceDetailClassVise(String classID, String date) throws SQLException, ClassNotFoundException {
        ArrayList<AttendenceDetail> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select ad.* from attendencedetail ad join attendence a" +
                " where ad.attendenceId = a.attendenceId and date = ? and classId = ?;",
                date,
                classID);
        while (resultSet.next()){
            AttendenceDetail attendenceDetailDTO = new AttendenceDetail(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
            arrayList.add(attendenceDetailDTO);
        }
        return arrayList;

    }
    @Override
    public  ArrayList<AttendenceDetail> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<AttendenceDetail> arrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.databaseConnect("select * from attendencedetail");
        while (resultSet.next()){
            AttendenceDetail attendenceDetail = new AttendenceDetail(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
            arrayList.add(attendenceDetail);
        }
        return arrayList;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
