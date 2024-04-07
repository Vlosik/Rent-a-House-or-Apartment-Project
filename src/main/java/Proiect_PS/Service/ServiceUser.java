package Proiect_PS.Service;

import Proiect_PS.DTO.UserData;
import Proiect_PS.DTO.UserPasswordData;
import Proiect_PS.DTO.UserUsernameData;
import Proiect_PS.Model.User;
import Proiect_PS.Repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The ServiceUser class provides user management services such as insertion, deletion,
 * updating, and finding users in the system. It interacts with the {@link ReposityUser} to
 * perform operations on the database.
 */
@Service
public class ServiceUser implements ServiceUserInterface{
    private RepositoryUser repositoryUser;
    private ServiceRental serviceRental;
    /**
     * Constructs a ServiceUser with a given user repository.
     *
     * @param repositoryUser The user repository used for database operations.
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
    public void insertUser(UserData userData){
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        user.setEmail(userData.getEmail());
        user.setRole(userData.getRole());
        serviceRental.addObserver(user);
        repositoryUser.save(user);
    }
    /**
     * Deletes an existing user from the system based on the given user data.
     *
     * @param userData The data for the user to be deleted.
     */
    @Override
    public User deleteUser(UserUsernameData userData){
        User user = repositoryUser.findByUsername(userData.getUsername());
        repositoryUser.delete(user);
        serviceRental.removeObserver(user);
        return user;
    }
    /**
     * Updates an existing user's information in the system based on the given user data.
     *
     * @param userData The new data for the user to be updated.
     * @return The updated User object.
     */
    @Override
    public void updateUser(UserData userData){
        User user = repositoryUser.findByUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        user.setEmail(userData.getEmail());
        user.setRole(userData.getRole());
        repositoryUser.save(user);
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

    @Override
    public List<User> findAll() {
        return repositoryUser.findAll();
    }

    @Override
    public void updatePassword(UserPasswordData userPasswordData) {
        User user = repositoryUser.findByUsername(userPasswordData.getUsername());
        if(user != null){
        user.setPassword(userPasswordData.getPassword());
        repositoryUser.save(user);
        }

    }
}
