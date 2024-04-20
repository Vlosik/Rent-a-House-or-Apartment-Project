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

public class ServiceRentalTests {
    @Mock
    private RepositoryProperty repositoryProperty;
    @Mock
    private RepositoryUser repositoryUser;
    @Mock
    private RepositoryRental repositoryRental;
    private ServiceRental rental;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        rental = new ServiceRental(repositoryProperty,repositoryUser,repositoryRental);
    }
    @Test
    public void insertRentalMethodTest(){
        RentalData rentalData = new RentalData();
        when(repositoryUser.findByUsername(rentalData.getUsername_user())).thenReturn(new User());
        when(repositoryProperty.findByTitle(rentalData.getProperty_title())).thenReturn(new Property());
        Rental rentalClone = rental.insertRental(rentalData);
        verify(repositoryRental).save(rentalClone);
    }
    @Test
    public void insertRentalFindUserTest(){
        RentalData rentalData = new RentalData();
        rental.insertRental(rentalData);
        verify(repositoryProperty).findByTitle(rentalData.getProperty_title());
    }
    @Test
    public void insertRentalFindPropertyTest(){
        RentalData rentalData = new RentalData();
        rental.insertRental(rentalData);
        verify(repositoryUser).findByUsername(rentalData.getUsername_user());
    }
    @Test
    public void insertRentalNotifyTest(){
        RentalData rentalData = new RentalData();
        ServiceRental spyRental = Mockito.spy(rental);
        when(repositoryUser.findByUsername(rentalData.getUsername_user())).thenReturn(new User());
        when(repositoryProperty.findByTitle(rentalData.getProperty_title())).thenReturn(new Property());
        Rental rentalClone = spyRental.insertRental(rentalData);
        verify(spyRental).notifyObservers(spyRental.createNotifyMessage(rentalData),rentalClone.getUser());
    }
    @Test
    public void insertRentalCreateNotifyTest(){
        RentalData rentalData = new RentalData();
        ServiceRental spyRental = Mockito.spy(rental);
        when(repositoryUser.findByUsername(rentalData.getUsername_user())).thenReturn(new User());
        when(repositoryProperty.findByTitle(rentalData.getProperty_title())).thenReturn(new Property());
        spyRental.insertRental(rentalData);
        verify(spyRental).createNotifyMessage(rentalData);
    }
    @Test
    public void CreateNotifyTest(){
        RentalData rentalData = new RentalData();
        rentalData.setProperty_title("Apartament nou");
        rentalData.setUsername_user("Adrian");
        String expectedMessage = "Proprietatea cu titlul Apartament nou a fost inchiriata de user-ul Adrian";
        String actualMessage = rental.createNotifyMessage(rentalData);
        assertEquals(expectedMessage,actualMessage);
    }
    @Test
    public void deleteAllTest(){
        rental.deleteAllRental();
        verify(repositoryRental).deleteAll();
    }
    @Test
    public void findAllTest(){
        rental.findAll();
        verify(repositoryRental).findAll();
    }
}
