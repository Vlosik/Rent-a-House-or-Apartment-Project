package Proiect_PS;

import Proiect_PS.dto.UserData;
import Proiect_PS.dto.UserPasswordData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.User;
import Proiect_PS.repository.RepositoryUser;
import Proiect_PS.service.ServiceRental;
import Proiect_PS.service.ServiceUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServiceUserTests {
    @Mock
    private RepositoryUser mockRepo;
    @Mock
    private ServiceRental mockRental;
    private ServiceUser userTest;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userTest = new ServiceUser(mockRepo,mockRental);
    }
    @Test
    public void insertUserSaveMethodTest(){
        UserData userD = new UserData();
        User userRepo = new User();
        userTest.insertUser(userD);
        verify(mockRepo).save(userRepo)

        ;
    }
    @Test
    public void insertUserAddObserverMethodTest(){
        UserData userD = new UserData();
        User user = userTest.insertUser(userD);
        verify(mockRental).addObserver(user);
    }
    @Test
    public void deleteUserMethodTest(){
        UserUsernameData userD = new UserUsernameData();
        User user = userTest.deleteUser(userD);
        verify(mockRepo).delete(user);
    }
    @Test
    public void deleteUserFindMethodTest(){
        UserUsernameData userD = new UserUsernameData();
        userTest.deleteUser(userD);
        verify(mockRepo).findByUsername(userD.getUsername());
    }
    @Test
    public void deleteUserObserverMethodTest(){
        UserUsernameData userD = new UserUsernameData();
        User user = userTest.deleteUser(userD);
        verify(mockRental).removeObserver(user);
    }
    @Test
    public void updateUserMethodTest(){
        UserData userData = new UserData();
        when(userTest.findUserByUsername(new UserUsernameData())).thenReturn(new User());
        User user = userTest.updateUser(userData);
        verify(mockRepo).save(user);
    }
    @Test
    public void updateUserFindMethodTest(){
        UserData userData = new UserData();
        userTest.updateUser(userData);
        verify(mockRepo).findByUsername(userData.getUsername());
    }
    @Test
    public void findUserByUsernameTest(){
        UserUsernameData userD = new UserUsernameData();
        userTest.findUserByUsername(userD);
        verify(mockRepo).findByUsername(userD.getUsername());
    }
    @Test
    public void updatePasswordMethodTest(){
        UserPasswordData userData = new UserPasswordData();
        when(userTest.findUserByUsername(new UserUsernameData())).thenReturn(new User());
        User user = userTest.updatePassword(userData);
        verify(mockRepo).save(user);
    }
    @Test
    public void updatePasswordFindMethodTest(){
        UserPasswordData userData = new UserPasswordData();
        userTest.updatePassword(userData);
        verify(mockRepo).findByUsername(userData.getUsername());
    }

    @Test
    public void findAllTest(){
        userTest.findAll();
        verify(mockRepo).findAll();
    }
}
