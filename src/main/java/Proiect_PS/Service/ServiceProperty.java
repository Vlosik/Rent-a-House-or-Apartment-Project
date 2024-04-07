package Proiect_PS.Service;

import Proiect_PS.DTO.PropertyData;
import Proiect_PS.DTO.PropertyTitleData;
import Proiect_PS.DTO.PropertyTrioData;
import Proiect_PS.Model.Property;
import Proiect_PS.Repository.RepositoryProperty;
import Proiect_PS.Repository.RepositoryUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceProperty implements ServicePropertyInterface {
    private RepositoryProperty repositoryProperty;
    private RepositoryUser repositoryUser;
    public ServiceProperty(RepositoryProperty repositoryProperty,RepositoryUser repositoryUser){
        this.repositoryProperty = repositoryProperty;
        this.repositoryUser = repositoryUser;
    }
    @Override
    public void insertProperty(PropertyData propertyData) {
        Property property = new Property();
        property.setAdmin(repositoryUser.findByUsername(propertyData.getAdmin_username()));
        property.setTitle(propertyData.getTitle());
        property.setDescription(propertyData.getDescription());
        property.setAvailable(propertyData.getFree());
        property.setPrice(propertyData.getPrice());
        property.setType(propertyData.getType());
        property.setLocation(propertyData.getLocation());
        repositoryProperty.save(property);
    }

    @Override
    public void deleteProperty(PropertyTitleData propertyData) {
        repositoryProperty.delete(this.findByTitle(propertyData.getTitle()));
    }

    @Override
    public void updateProperty(PropertyData propertyData) {
        Property property = this.findByTitle(propertyData.getTitle());
        property.setDescription(propertyData.getDescription());
        property.setAvailable(propertyData.getFree());
        property.setPrice(propertyData.getPrice());
        property.setLocation(propertyData.getLocation());
        repositoryProperty.save(property);
    }

    @Override
    public List<Property> findByTitlePriceAndLocation(PropertyTrioData propertyTrioData) {
        return repositoryProperty.findByTitlePriceAndLocation(propertyTrioData.getTitle(),propertyTrioData.getPrice(), propertyTrioData.getLocation());
    }

    @Override
    public List<Property> findAll() {
        return repositoryProperty.findAll();
    }

    @Override
    public Property findByTitle(String title) {
        return repositoryProperty.findByTitle(title);
    }

    @Override
    public List<Property> findAvailables() {
        return repositoryProperty.findByIsAvailable(Boolean.valueOf("true"));
    }
}
