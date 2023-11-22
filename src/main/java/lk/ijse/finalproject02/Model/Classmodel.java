package lk.ijse.finalproject02.Model;

import lk.ijse.finalproject02.DB.DBConnection;
import lk.ijse.finalproject02.DTO.ClassDTO;

import java.net.ConnectException;
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into class values (?,?,?,?,?,?)");
            preparedStatement.setString(1,classDTO.getClassId());
            preparedStatement.setString(2,classDTO.getSubject());
            preparedStatement.setInt(3,classDTO.getTeacherId());
            preparedStatement.setString(4,classDTO.getGrade());
            preparedStatement.setString(5,classDTO.getStream());
            preparedStatement.setString(6,classDTO.getFee());

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
    public static boolean updateClass(String classID,String subject ,String batch , String fee ){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update class set subject = ? , batch = ? , fee= ? where classId= ?;");
            preparedStatement.setString(1,subject);
            preparedStatement.setString(2,batch);
            preparedStatement.setString(3,fee);
            preparedStatement.setString(4,classID);

            int executed = preparedStatement.executeUpdate();
            return executed>0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteClass(String classID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from class where classId = ?");
            preparedStatement.setString(1,classID);

            int executed = preparedStatement.executeUpdate();
            return executed > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<String> getsubjects(String stream){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select distinct(subject) from class where stream = ? ");
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
    public static String getClassFee(String clasID){
        String fee = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" select fee from class where classId = ?");
            preparedStatement.setString(1,clasID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                fee = resultSet.getString(1);
            }

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return fee;
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
            PreparedStatement preparedStatement = connection.prepareStatement(" select classId from class where teacherID = ? && batch = ?");
            preparedStatement.setInt(1,teacherID);
            preparedStatement.setInt(2, Integer.parseInt(batch));
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
    public static ArrayList<ClassDTO> getclassObjStudentVIse(int studentID){
        ArrayList<ClassDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" select subject,batch from class c join class_detail cd on c.classId = cd.classId where studentId = ?");
            preparedStatement.setInt(1,studentID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClassDTO classDTO = new ClassDTO(resultSet.getString(1),resultSet.getString(2));
                arrayList.add(classDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static ArrayList<ClassDTO> getallClasses(){
        ArrayList<ClassDTO> classDTOS = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from  class");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ClassDTO classDTO = new ClassDTO(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
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
