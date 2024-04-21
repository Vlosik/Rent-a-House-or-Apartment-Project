package Proiect_PS;

import Proiect_PS.dto.PropertyData;
import Proiect_PS.dto.PropertyTitleData;
import Proiect_PS.dto.PropertyTrioData;
import Proiect_PS.dto.UserData;
import Proiect_PS.model.Property;
import Proiect_PS.model.User;
import Proiect_PS.repository.RepositoryProperty;
import Proiect_PS.repository.RepositoryUser;
import Proiect_PS.service.ServiceProperty;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Clasa ServicePropertyTests este folosită pentru testarea metodelor din clasa ServiceProperty utilizând Mockito.
 */
public class ServicePropertyTests {
    private ServiceProperty property;
    @Mock
    private RepositoryProperty repositoryProperty;
    @Mock
    private RepositoryUser repositoryUser;
    /**
     * Inițializează obiectele mock și clasa ServiceProperty înainte de fiecare test.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        property = new ServiceProperty(repositoryProperty,repositoryUser);
    }
    /**
     * Testează metoda insertProperty din clasa ServiceProperty.
     */
    @Test
    public void insertPropertyMethodTest(){
        PropertyData propertyData = new PropertyData();
        Property propertyClone = property.insertProperty(propertyData);
        verify(repositoryProperty).save(propertyClone);
    }
    /**
     * Testează metoda deleteProperty din clasa ServiceProperty pentru căutarea unei proprietăți după titlu.
     */
    @Test
    public void deletePropertyFindMethodTest(){
        PropertyTitleData propertyData = new PropertyTitleData();
        property.deleteProperty(propertyData);
        verify(repositoryProperty).findByTitle(propertyData.getTitle());
    }
    /**
     * Testează metoda deleteProperty din clasa ServiceProperty pentru ștergerea unei proprietăți.
     */
    @Test
    public void deletePropertyMethodTest(){
        PropertyTitleData propertyData = new PropertyTitleData();
        Property propertyClone = property.deleteProperty(propertyData);
        verify(repositoryProperty).delete(propertyClone);
    }
    /**
     * Testează metoda findByTitlePriceAndLocation din clasa ServiceProperty.
     */
    @Test
    public void findByTitlePriceAndLocationTest(){
        PropertyTrioData propertyTrioData = new PropertyTrioData();
        property.findByTitlePriceAndLocation(propertyTrioData);
        verify(repositoryProperty).findByTitlePriceAndLocation(propertyTrioData.getTitle(),propertyTrioData.getPrice(),propertyTrioData.getLocation());
    }
    /**
     * Testează metoda updateProperty din clasa ServiceProperty pentru căutarea unei proprietăți după titlu.
     */
    @Test
    public void updatePropertyFindMethodTest(){
        PropertyData propertyData = new PropertyData();
        property.updateProperty(propertyData);
        verify(repositoryProperty).findByTitle(propertyData.getTitle());
    }
    /**
     * Testează metoda updateProperty din clasa ServiceProperty pentru actualizarea unei proprietăți.
     */
    @Test
    public void updatePropertyMethodTest(){
        PropertyData propertyData = new PropertyData();
        when(property.findByTitle(propertyData.getTitle())).thenReturn(new Property());
        Property propertyClone = property.updateProperty(propertyData);
        verify(repositoryProperty).save(propertyClone);
    }

    /**
     * Testează metoda findAll din clasa ServiceProperty.
     */
    @Test
    public void findAllTest(){
        property.findAll();
        verify(repositoryProperty).findAll();
    }
    /**
     * Testează metoda findByTitle din clasa ServiceProperty pentru căutarea unei proprietăți după titlu.
     */
    @Test
    public void findByTitleTest(){
        String string = "";
        property.findByTitle(string);
        verify(repositoryProperty).findByTitle(string);
    }
    /**
     * Testează metoda findAvailables din clasa ServiceProperty pentru găsirea proprietăților disponibile.
     */
    @Test
    public void findAvailablesTest(){
        property.findAvailables();
        verify(repositoryProperty).findByIsAvailable(Boolean.valueOf("true"));
    }
}
