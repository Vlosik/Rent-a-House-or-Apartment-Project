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
/**
 * Clasa ServiceProperty oferă servicii de gestionare a proprietăților în sistem.
 * Implementează interfața ServicePropertyInterface și folosește RepositoryProperty
 * și RepositoryUser pentru a interacționa cu baza de date.
 */
@Service
public class ServiceProperty implements ServicePropertyInterface {
    private RepositoryProperty repositoryProperty;
    private RepositoryUser repositoryUser;
    /**
     * Constructor pentru ServiceProperty.
     *
     * @param repositoryProperty Referința către RepositoryProperty pentru interacțiunea cu proprietățile.
     * @param repositoryUser Referința către RepositoryUser pentru interacțiunea cu utilizatorii.
     */
    public ServiceProperty(RepositoryProperty repositoryProperty,RepositoryUser repositoryUser){
        this.repositoryProperty = repositoryProperty;
        this.repositoryUser = repositoryUser;
    }
    /**
     * Inserează o nouă proprietate în sistem.
     *
     * @param propertyData Datele proprietății pentru inserare.
     */
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
    /**
     * Șterge o proprietate din sistem bazat pe titlu.
     *
     * @param propertyData Datele proprietății pentru identificarea titlului.
     */
    @Override
    public void deleteProperty(PropertyTitleData propertyData) {
        repositoryProperty.delete(this.findByTitle(propertyData.getTitle()));
    }
    /**
     * Actualizează detaliile unei proprietăți existente în sistem.
     *
     * @param propertyData Datele actualizate ale proprietății.
     */
    @Override
    public void updateProperty(PropertyData propertyData) {
        Property property = this.findByTitle(propertyData.getTitle());
        property.setDescription(propertyData.getDescription());
        property.setAvailable(propertyData.getFree());
        property.setPrice(propertyData.getPrice());
        property.setLocation(propertyData.getLocation());
        repositoryProperty.save(property);
    }
    /**
     * Caută proprietăți bazate pe titlu, preț și locație.
     *
     * @param propertyTrioData Datele pentru căutarea proprietății.
     * @return Lista de proprietăți care corespund criteriilor de căutare.
     */
    @Override
    public List<Property> findByTitlePriceAndLocation(PropertyTrioData propertyTrioData) {
        return repositoryProperty.findByTitlePriceAndLocation(propertyTrioData.getTitle(),propertyTrioData.getPrice(), propertyTrioData.getLocation());
    }
    /**
     * Returnează toate proprietățile din sistem.
     *
     * @return Lista tuturor proprietăților.
     */
    @Override
    public List<Property> findAll() {
        return repositoryProperty.findAll();
    }
    /**
     * Caută o proprietate bazată pe titlu.
     *
     * @param title Titlul proprietății căutate.
     * @return Proprietatea care corespunde titlului.
     */
    @Override
    public Property findByTitle(String title) {
        return repositoryProperty.findByTitle(title);
    }
    /**
     * Returnează lista proprietăților disponibile pentru închiriere.
     *
     * @return Lista de proprietăți disponibile.
     */
    @Override
    public List<Property> findAvailables() {
        return repositoryProperty.findByIsAvailable(Boolean.valueOf("true"));
    }
}
