package Proiect_PS;

import Proiect_PS.dto.PropertyData;
import Proiect_PS.dto.UserData;
import Proiect_PS.model.Property;
import Proiect_PS.model.User;
import Proiect_PS.repository.RepositoryProperty;
import Proiect_PS.repository.RepositoryUser;
import Proiect_PS.service.ServiceProperty;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

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
        UserData userD = new UserData();
        User userRepo = new User();
        PropertyData propertyData = new PropertyData();
        Property propertyClone = property.insertProperty(propertyData);
        verify(repositoryProperty).save(propertyClone);
    }
}
