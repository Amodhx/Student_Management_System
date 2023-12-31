package lk.ijse.finalproject02.service.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.finalproject02.DTO.UserDTO;
import lk.ijse.finalproject02.dao.DAOFactory;
import lk.ijse.finalproject02.dao.custom.UserDAO;
import lk.ijse.finalproject02.dao.custom.impl.UserDAOImpl;
import lk.ijse.finalproject02.entity.User;
import lk.ijse.finalproject02.service.custom.AdminService;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminServiceImpl implements AdminService {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO userDTO){
        try {
            return userDAO.save(new User(userDTO.getUserId(),userDTO.getUserName(),userDTO.getPassword(),
                    userDTO.getType(),userDTO.getMail(),userDTO.getFullname()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Cant Save User").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Cant Save User").show();
        }
        return false;
    }
    @Override
    public ArrayList<UserDTO> getAllUsers(){
        try {
            ArrayList<UserDTO> arrayList =  new ArrayList<>();
            for (User u : userDAO.getAll()) {
                arrayList.add(new UserDTO(u.getUserId(), u.getUserName(), u.getPassword(), u.getType(), u.getMail(),u.getFullname()));
            }
            return arrayList;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Cant get User Details").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Cant get User Details").show();
        }
        return new ArrayList<UserDTO>();
    }
    @Override

    public boolean changePassword(String usernameText, String passwordText) throws SQLException, ClassNotFoundException {
        return userDAO.changePassword(usernameText,passwordText);
    }
}
