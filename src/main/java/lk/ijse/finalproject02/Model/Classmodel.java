package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ClassDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Classmodel {
    public static boolean saveclass(ClassDTO classDTO){
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into class values (?,?,?,?,?)");
            preparedStatement.setString(1,classDTO.getClassId());
            preparedStatement.setString(2,classDTO.getSubject());
            preparedStatement.setInt(3,classDTO.getTeacherId());
            preparedStatement.setString(4,classDTO.getGrade());
            preparedStatement.setString(5,classDTO.getStream());

            int is = preparedStatement.executeUpdate();

            return is>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public static int getTeacherid(String id){
        int teacherid = 0;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select teacherID from class where classId = ?");
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                teacherid = resultSet.getInt(1);
            };
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return teacherid;
    }
    public static ArrayList<String> getsubjects(String stream){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select subject from class where stream = ?");
            preparedStatement.setString(1,stream);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                arrayList.add(id);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public static int getClassCount(){
        int x = 0;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(classId) from class;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                x = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return x;
    }
    public static String getclassID(int teacherID , String batch){
        String clsid = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select classId from class where teacherID = ? & batch = ?");
            preparedStatement.setInt(1,teacherID);
            preparedStatement.setString(2,batch);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                clsid = resultSet.getString(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return clsid;
    }
    public static ArrayList<ClassDTO> getallClasses(){
        ArrayList<ClassDTO> classDTOS = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  class");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClassDTO classDTO = new ClassDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5));
                classDTOS.add(classDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return classDTOS;
    }
}
