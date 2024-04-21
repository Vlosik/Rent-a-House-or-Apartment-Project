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
/**
 * Clasa ServiceUserTests este folosită pentru testarea metodelor din clasa ServiceUser utilizând Mockito.
 */
public class ServiceUserTests {
    @Mock
    private RepositoryUser mockRepo;
    @Mock
    private ServiceRental mockRental;
    private ServiceUser userTest;
    /**
     * Inițializează obiectele mock și clasa ServiceUser înainte de fiecare test.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userTest = new ServiceUser(mockRepo,mockRental);
    }
    /**
     * Testează metoda insertUser din clasa ServiceUser pentru salvarea unui utilizator.
     */
    @Test
    public void insertUserSaveMethodTest(){
        UserData userD = new UserData();
        User userRepo = new User();
        userTest.insertUser(userD);
        verify(mockRepo).save(userRepo);
    }
    /**
     * Testează metoda insertUser din clasa ServiceUser pentru adăugarea unui observator.
     */
    @Test
    public void insertUserAddObserverMethodTest(){
        UserData userD = new UserData();
        User user = userTest.insertUser(userD);
        verify(mockRental).addObserver(user);
    }
    /**
     * Testează metoda deleteUser din clasa ServiceUser pentru ștergerea unui utilizator.
     */
    @Test
    public void deleteUserMethodTest(){
        UserUsernameData userD = new UserUsernameData();
        User user = userTest.deleteUser(userD);
        verify(mockRepo).delete(user);
    }
    /**
     * Testează metoda deleteUser din clasa ServiceUser pentru căutarea unui utilizator după numele său de utilizator.
     */
    @Test
    public void deleteUserFindMethodTest(){
        UserUsernameData userD = new UserUsernameData();
        userTest.deleteUser(userD);
        verify(mockRepo).findByUsername(userD.getUsername());
    }
    /**
     * Testează metoda deleteUser din clasa ServiceUser pentru eliminarea unui observator.
     */
    @Test
    public void deleteUserObserverMethodTest(){
        UserUsernameData userD = new UserUsernameData();
        User user = userTest.deleteUser(userD);
        verify(mockRental).removeObserver(user);
    }
    /**
     * Testează metoda updateUser din clasa ServiceUser pentru actualizarea unui utilizator.
     */
    @Test
    public void updateUserMethodTest(){
        UserData userData = new UserData();
        when(userTest.findUserByUsername(new UserUsernameData())).thenReturn(new User());
        User user = userTest.updateUser(userData);
        verify(mockRepo).save(user);
    }
    /**
     * Testează metoda updateUser din clasa ServiceUser pentru căutarea unui utilizator după numele său de utilizator.
     */
    @Test
    public void updateUserFindMethodTest(){
        UserData userData = new UserData();
        userTest.updateUser(userData);
        verify(mockRepo).findByUsername(userData.getUsername());
    }
    /**
     * Testează metoda findUserByUsername din clasa ServiceUser pentru căutarea unui utilizator după numele său de utilizator.
     */
    @Test
    public void findUserByUsernameTest(){
        UserUsernameData userD = new UserUsernameData();
        userTest.findUserByUsername(userD);
        verify(mockRepo).findByUsername(userD.getUsername());
    }
    /**
     * Testează metoda updatePassword din clasa ServiceUser pentru actualizarea parolei unui utilizator.
     */
    @Test
    public void updatePasswordMethodTest(){
        UserPasswordData userData = new UserPasswordData();
        when(userTest.findUserByUsername(new UserUsernameData())).thenReturn(new User());
        User user = userTest.updatePassword(userData);
        verify(mockRepo).save(user);
    }
    /**
     * Testează metoda updatePassword din clasa ServiceUser pentru căutarea unui utilizator după numele său de utilizator.
     */
    @Test
    public void updatePasswordFindMethodTest(){
        UserPasswordData userData = new UserPasswordData();
        userTest.updatePassword(userData);
        verify(mockRepo).findByUsername(userData.getUsername());
    }
    /**
     * Testează metoda findAll din clasa ServiceUser pentru găsirea tuturor utilizatorilor.
     */
    @Test
    public void findAllTest(){
        userTest.findAll();
        verify(mockRepo).findAll();
    }
}
