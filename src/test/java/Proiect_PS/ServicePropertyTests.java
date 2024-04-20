package Proiect_PS;

import Proiect_PS.repository.RepositoryProperty;
import Proiect_PS.repository.RepositoryUser;
import Proiect_PS.service.ServiceProperty;
import org.junit.Before;
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
}
