package Proiect_PS.Controller;

import Proiect_PS.DTO.UserData;
import Proiect_PS.DTO.UserPasswordData;
import Proiect_PS.DTO.UserUsernameData;
import Proiect_PS.Model.User;
import Proiect_PS.Service.ServiceUser;
import Proiect_PS.Service.ServiceUserInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ControllerUser class manages HTTP operations related to users.
 * This class acts as a controller within the Spring Boot architecture, handling
 * the addition, searching, deletion, and updating of users through the {@link ServiceUser} service.
 */
@RestController
@RequestMapping("/user")
public class ControllerUser {
    private ServiceUserInterface serviceUser;
    /**
     * Constructs a ControllerUser.
     * Initializes the controller with a specific user service.
     *
     * @param userService The user service used for managing user operations.
     */
    public ControllerUser(ServiceUser userService){
        this.serviceUser = userService;
    }
    /**
     * Adds a new user to the system.
     * Via the HTTP POST method, this method receives data about a new user
     * and adds them using the user service.
     *
     * @param userData The data of the user to be added.
     */
    @PostMapping("/insert")
    public void addUser(@RequestBody UserData userData){
       this.serviceUser.insertUser(userData);
    }
    /**
     * Searches for a user in the system.
     * Uses the HTTP GET method to search for a user specified by {@code userData}.
     *
     * @param userData The user data used for the search.
     */
    @GetMapping("/findUser")
    public User findByUser(@RequestBody UserUsernameData userData){
        return this.serviceUser.findUserByUsername(userData);
    }
    /**
     * Deletes a user from the system.
     * This method uses the HTTP DELETE method to remove a specified user.
     *
     * @param userData The data of the user to be deleted.
     */
    @DeleteMapping ("/delete")
    public User deleteUser(@RequestBody UserUsernameData userData){
        return this.serviceUser.deleteUser(userData);
    }
    /**
     * Updates an existing user's data.
     * Uses the HTTP PUT method to update the information of a specified user.
     *
     * @param userData The new data of the user for updating.
     */
    @PutMapping ("/update")
    public void updateUser(@RequestBody UserData userData){
        this.serviceUser.updateUser(userData);
    }

    @GetMapping("/findAll")
        public List<User> findAllUser(){
            return this.serviceUser.findAll();
        }

    @PutMapping ("/updatePassword")
    public void updatePasswordUser(@RequestBody UserPasswordData userData){
        this.serviceUser.updatePassword(userData);
    }
}
