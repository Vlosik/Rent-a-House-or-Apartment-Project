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

public class ServicePropertyTests {
    private ServiceProperty property;
    @Mock
    private RepositoryProperty repositoryProperty;
    @Mock
    private RepositoryUser repositoryUser;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        property = new ServiceProperty(repositoryProperty,repositoryUser);
    }
    @Test
    public void insertPropertyMethodTest(){
        PropertyData propertyData = new PropertyData();
        Property propertyClone = property.insertProperty(propertyData);
        verify(repositoryProperty).save(propertyClone);
    }
    @Test
    public void deletePropertyFindMethodTest(){
        PropertyTitleData propertyData = new PropertyTitleData();
        property.deleteProperty(propertyData);
        verify(repositoryProperty).findByTitle(propertyData.getTitle());
    }
    @Test
    public void deletePropertyMethodTest(){
        PropertyTitleData propertyData = new PropertyTitleData();
        Property propertyClone = property.deleteProperty(propertyData);
        verify(repositoryProperty).delete(propertyClone);
    }
    @Test
    public void findByTitlePriceAndLocationTest(){
        PropertyTrioData propertyTrioData = new PropertyTrioData();
        property.findByTitlePriceAndLocation(propertyTrioData);
        verify(repositoryProperty).findByTitlePriceAndLocation(propertyTrioData.getTitle(),propertyTrioData.getPrice(),propertyTrioData.getLocation());
    }
    @Test
    public void updatePropertyFindMethodTest(){
        PropertyData propertyData = new PropertyData();
        property.updateProperty(propertyData);
        verify(repositoryProperty).findByTitle(propertyData.getTitle());
    }
    @Test
    public void updatePropertyMethodTest(){
        PropertyData propertyData = new PropertyData();
        when(property.findByTitle(propertyData.getTitle())).thenReturn(new Property());
        Property propertyClone = property.updateProperty(propertyData);
        verify(repositoryProperty).save(propertyClone);
    }
    @Test
    public void findAllTest(){
        property.findAll();
        verify(repositoryProperty).findAll();
    }
    @Test
    public void findByTitleTest(){
        String string = "";
        property.findByTitle(string);
        verify(repositoryProperty).findByTitle(string);
    }
    @Test
    public void findAvailablesTest(){
        property.findAvailables();
        verify(repositoryProperty).findByIsAvailable(Boolean.valueOf("true"));
    }
}
