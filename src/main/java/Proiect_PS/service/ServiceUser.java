package Proiect_PS.service;

import Proiect_PS.dto.UserData;
import Proiect_PS.dto.UserPasswordData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Role;
import Proiect_PS.model.User;
import Proiect_PS.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clasa ServiceUser oferă servicii de gestionare a utilizatorilor, cum ar fi inserția, ștergerea,
 * actualizarea și căutarea utilizatorilor în sistem. Interacționează cu RepositoryUser pentru
 * a efectua operațiuni pe baza de date și gestionează integrarea utilizatorilor cu sistemul de notificări.
 */
@Service
public class ServiceUser implements ServiceUserInterface{
    private RepositoryUser repositoryUser;
    private ServiceRental serviceRental;
    /**
     * Constructorul clasei ServiceUser.
     *
     * @param repositoryUser Repository-ul utilizat pentru operațiunile pe baza de date.
     * @param serviceRental Serviciul de închiriere pentru gestionarea notificărilor.
     */
    public ServiceUser(RepositoryUser repositoryUser,ServiceRental serviceRental){
        this.repositoryUser = repositoryUser;
        this.serviceRental = serviceRental;
    }
    /**
     * Inserts a new user into the system based on the given user data.
     *
     * @param userData The data for the user to be inserted.
     * @return The inserted User object.
     */
    @Override
    public User insertUser(UserData userData){
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        user.setEmail(userData.getEmail());
        user.setRole(userData.getRole());
        serviceRental.addObserver(user);
        repositoryUser.save(user);
        return user;
    }
    /**
     * Deletes an existing user from the system based on the given user data.
     *
     * @param userData The data for the user to be deleted.
     * @return user deleted.
     */
    @Override
    public User deleteUser(UserUsernameData userData){
        User user = repositoryUser.findByUsername(userData.getUsername());
        serviceRental.removeObserver(user);
        repositoryUser.delete(user);
        return user;
    }
    /**
     * Updates an existing user's information in the system based on the given user data.
     *
     * @param userData The new data for the user to be updated.
     * @return user updated.
     */
    @Override
    public User updateUser(UserData userData){
        User user = repositoryUser.findByUsername(userData.getUsername());
        if(user != null) {
            user.setPassword(userData.getPassword());
            user.setEmail(userData.getEmail());
            user.setRole(userData.getRole());
            repositoryUser.save(user);
        }
        return user;
    }
    /**
     * Finds a user in the system based on the given user data.
     *
     * @param userData The data of the user to find.
     * @return The found User object, or null if the user does not exist.
     */
    @Override
    public User findUserByUsername(UserUsernameData userData){
        User user = repositoryUser.findByUsername(userData.getUsername());
        if(user != null){
            return user;
        }
        else{
            return null;
        }
    }
    /**
     * Returnează toți utilizatorii din sistem.
     *
     * @return Lista tuturor utilizatorilor.
     */
    @Override
    public List<User> findAll() {
        return repositoryUser.findAll();
    }
    /**
     * Actualizează parola unui utilizator.
     *
     * @param userPasswordData Datele utilizatorului pentru actualizarea parolei.
     * @return user-ul cu parola actualizata.
     */
    @Override
    public User updatePassword(UserPasswordData userPasswordData) {
        User user = repositoryUser.findByUsername(userPasswordData.getUsername());
        if(user != null){
        user.setPassword(userPasswordData.getPassword());
        }
        repositoryUser.save(user);
        return user;
    }

    @Override
    public Integer login(UserPasswordData userPasswordData) {
        User user = repositoryUser.findByUsername(userPasswordData.getUsername());
        if(user != null){
            if(user.getPassword().equals(userPasswordData.getPassword())){
                if(user.getRole().equals(Role.ADMIN)){
                    return 1;
                }
                else{
                    return 2;
                }
            }
            else{
                return 0;
            }
        }
        return null;
    }
}
