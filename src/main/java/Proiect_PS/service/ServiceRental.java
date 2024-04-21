package Proiect_PS.service;

import Proiect_PS.dto.RentalData;
import Proiect_PS.model.Property;
import Proiect_PS.model.Rental;
import Proiect_PS.model.User;
import Proiect_PS.observerPattern.Observer;
import Proiect_PS.observerPattern.Subject;
import Proiect_PS.repository.RepositoryProperty;
import Proiect_PS.repository.RepositoryRental;
import Proiect_PS.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * ServiceRental gestionează operațiunile legate de închirierea proprietăților,
 * implementând logica de business necesară. Implementează interfața ServiceRentalInterface și
 * pattern-ul de Observer ca Subject pentru a notifica utilizatorii despre evenimentele de închiriere.
 */
@Service
public class ServiceRental implements ServiceRentalInterface, Subject {
    private RepositoryProperty repositoryProperty;
    private RepositoryUser repositoryUser;
    private RepositoryRental repositoryRental;
    private List<Observer> observers;
    /**
     * Constructorul clasei ServiceRental.
     *
     * @param repositoryProperty Referința către repository-ul de proprietăți.
     * @param repositoryUser Referința către repository-ul de utilizatori.
     * @param repositoryRental Referința către repository-ul de închirieri.
     */
    public ServiceRental(RepositoryProperty repositoryProperty,RepositoryUser repositoryUser, RepositoryRental repositoryRental){
        this.repositoryRental = repositoryRental;
        this.repositoryProperty = repositoryProperty;
        this.repositoryUser = repositoryUser;
        this.observers = new ArrayList<>();
        List<User> users= repositoryUser.findAll();
        for(User user : users){
            this.addObserver(user);
        }
    }
    /**
     * Inserează o nouă închiriere în sistem și notifică utilizatorii despre aceasta.
     *
     * @param rentalData Datele închirierii pentru inserare.
     * @return Inchirierea inserata.
     */
    @Override
    public Rental insertRental(RentalData rentalData) {
        Rental rental = new Rental();
        User user = repositoryUser.findByUsername(rentalData.getUsername_user());
        Property property = repositoryProperty.findByTitle(rentalData.getProperty_title());
        if(user != null && property != null) {
            rental.setUser(user);
            rental.setProperty(property);
            rental.setStartDate(rentalData.getStartDate());
            rental.setEndDate(rentalData.getEndDate());
            this.notifyObservers(this.createNotifyMessage(rentalData), rental.getUser());
            repositoryRental.save(rental);
        }
        return rental;
    }
    /**
     * Generează un mesaj de notificare pentru închirierile noi.
     *
     * @param rentalData Datele închirierii.
     * @return Mesajul de notificare.
     */
    public String createNotifyMessage(RentalData rentalData){
        String message = "Proprietatea cu titlul ";
        message += rentalData.getProperty_title();
        message += " a fost inchiriata de user-ul " + rentalData.getUsername_user();
        return message;
    }
    /**
     * Șterge toate închirierile din sistem.
     */
    @Override
    public void deleteAllRental() {
        this.repositoryRental.deleteAll();
    }
    /**
     * Actualizează detaliile unei închirieri.
     *
     * @param rentalData Datele închirierii actualizate.
     */
    @Override
    public void updateRental(RentalData rentalData) {
    }
    /**
     * Returnează toate închirierile din sistem.
     *
     * @return Lista tuturor închirierilor.
     */
    @Override
    public List<Rental> findAll() {
        return repositoryRental.findAll();
    }
    /**
     * Adaugarea unui nou Observer.
     *
     * @param o noul Observer.
     */
    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }
    /**
     * Stergerea unui Observer.
     *
     * @param o noul Observer.
     */
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    /**
     * Notificarea Observerilor.
     *
     * @param o Observer-ul care a facut inchirierea
     * @param message acesta va fi trimis observatorilor.
     */
    @Override
    public void notifyObservers(String message,Observer o) {
        for(Observer observer : observers){
            if(observer instanceof User && o instanceof User) {
                if (!Objects.equals(((User) observer).getUsername(), ((User) o).getUsername())) {
                    observer.update(message);
                }
            }
        }
    }
}
