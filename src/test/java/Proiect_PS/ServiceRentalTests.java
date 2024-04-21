package Proiect_PS;

import Proiect_PS.dto.RentalData;
import Proiect_PS.model.Property;
import Proiect_PS.model.Rental;
import Proiect_PS.model.User;
import Proiect_PS.observerPattern.Observer;
import Proiect_PS.repository.RepositoryProperty;
import Proiect_PS.repository.RepositoryRental;
import Proiect_PS.repository.RepositoryUser;
import Proiect_PS.service.ServiceRental;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Clasa ServiceRentalTests este folosită pentru testarea metodelor din clasa ServiceRental utilizând Mockito.
 */
public class ServiceRentalTests {
    @Mock
    private RepositoryProperty repositoryProperty;
    @Mock
    private RepositoryUser repositoryUser;
    @Mock
    private RepositoryRental repositoryRental;
    private ServiceRental rental;
    /**
     * Inițializează obiectele mock și clasa ServiceRental înainte de fiecare test.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        rental = new ServiceRental(repositoryProperty,repositoryUser,repositoryRental);
    }
    /**
     * Testează metoda insertRental din clasa ServiceRental pentru inserarea unui contract de închiriere.
     */
    @Test
    public void insertRentalMethodTest(){
        RentalData rentalData = new RentalData();
        when(repositoryUser.findByUsername(rentalData.getUsername_user())).thenReturn(new User());
        when(repositoryProperty.findByTitle(rentalData.getProperty_title())).thenReturn(new Property());
        Rental rentalClone = rental.insertRental(rentalData);
        verify(repositoryRental).save(rentalClone);
    }
    /**
     * Testează metoda insertRental din clasa ServiceRental pentru căutarea unei proprietăți după titlu.
     */
    @Test
    public void insertRentalFindUserTest(){
        RentalData rentalData = new RentalData();
        rental.insertRental(rentalData);
        verify(repositoryProperty).findByTitle(rentalData.getProperty_title());
    }
    /**
     * Testează metoda insertRental din clasa ServiceRental pentru căutarea unui utilizator după numele său de utilizator.
     */
    @Test
    public void insertRentalFindPropertyTest(){
        RentalData rentalData = new RentalData();
        rental.insertRental(rentalData);
        verify(repositoryUser).findByUsername(rentalData.getUsername_user());
    }
    /**
     * Testează metoda insertRental din clasa ServiceRental pentru notificarea observatorilor după inserarea unui contract de închiriere.
     */
    @Test
    public void insertRentalNotifyTest(){
        RentalData rentalData = new RentalData();
        ServiceRental spyRental = Mockito.spy(rental);
        when(repositoryUser.findByUsername(rentalData.getUsername_user())).thenReturn(new User());
        when(repositoryProperty.findByTitle(rentalData.getProperty_title())).thenReturn(new Property());
        Rental rentalClone = spyRental.insertRental(rentalData);
        verify(spyRental).notifyObservers(spyRental.createNotifyMessage(rentalData),rentalClone.getUser());
    }
    /**
     * Testează metoda insertRental din clasa ServiceRental pentru crearea mesajului de notificare.
     */
    @Test
    public void insertRentalCreateNotifyTest(){
        RentalData rentalData = new RentalData();
        ServiceRental spyRental = Mockito.spy(rental);
        when(repositoryUser.findByUsername(rentalData.getUsername_user())).thenReturn(new User());
        when(repositoryProperty.findByTitle(rentalData.getProperty_title())).thenReturn(new Property());
        spyRental.insertRental(rentalData);
        verify(spyRental).createNotifyMessage(rentalData);
    }
    /**
     * Testează metoda createNotifyMessage din clasa ServiceRental pentru crearea mesajului de notificare.
     */
    @Test
    public void CreateNotifyTest(){
        RentalData rentalData = new RentalData();
        rentalData.setProperty_title("Apartament nou");
        rentalData.setUsername_user("Adrian");
        String expectedMessage = "Proprietatea cu titlul Apartament nou a fost inchiriata de user-ul Adrian";
        String actualMessage = rental.createNotifyMessage(rentalData);
        assertEquals(expectedMessage,actualMessage);
    }
    /**
     * Testează metoda deleteAllRental din clasa ServiceRental pentru ștergerea tuturor contractelor de închiriere.
     */
    @Test
    public void deleteAllTest(){
        rental.deleteAllRental();
        verify(repositoryRental).deleteAll();
    }
    /**
     * Testează metoda findAll din clasa ServiceRental pentru găsirea tuturor contractelor de închiriere.
     */
    @Test
    public void findAllTest(){
        rental.findAll();
        verify(repositoryRental).findAll();
    }
}
