package Proiect_PS.Service;

import Proiect_PS.DTO.UserData;
import Proiect_PS.DTO.UserPasswordData;
import Proiect_PS.DTO.UserUsernameData;
import Proiect_PS.Model.User;

import java.util.List;

public interface ServiceUserInterface {
    void insertUser(UserData userData);

    User deleteUser(UserUsernameData userData);
    void updateUser(UserData userData);
    User findUserByUsername(UserUsernameData userData);
    List<User> findAll();
    void updatePassword(UserPasswordData userPasswordData);
}
