package Proiect_PS.service;

import Proiect_PS.dto.NotificationData;
import Proiect_PS.dto.PropertyTitleData;
import Proiect_PS.dto.RentalData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Favorite;
import Proiect_PS.model.Property;
import Proiect_PS.model.Rental;
import Proiect_PS.model.User;
import Proiect_PS.observerPattern.Observer;
import Proiect_PS.observerPattern.Subject;
import Proiect_PS.repository.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    private RepositoryFavorite repositoryFavorite;
    private List<Observer> observers;
    private ServiceNotifications serviceNotifications;
    /**
     * Constructorul clasei ServiceRental.
     *
     * @param repositoryProperty Referința către repository-ul de proprietăți.
     * @param repositoryUser Referința către repository-ul de utilizatori.
     * @param repositoryRental Referința către repository-ul de închirieri.
     */
    public ServiceRental(RepositoryProperty repositoryProperty, RepositoryUser repositoryUser, RepositoryRental repositoryRental, ServiceNotifications serviceNotifications, RepositoryFavorite repositoryFavorite,RepositoryNotifications repositoryNotifications){
        this.repositoryRental = repositoryRental;
        this.repositoryProperty = repositoryProperty;
        this.repositoryUser = repositoryUser;
        this.serviceNotifications = serviceNotifications;
        this.repositoryFavorite = repositoryFavorite;
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
            this.notifyObservers(this.createNotifyMessage(rentalData), rental.getUser(),property);
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String message = "Proprietatea cu titlul ";
        message += rentalData.getProperty_title();
        message += " a fost inchiriata de user-ul " + rentalData.getUsername_user();
        message += " in perioada: " + dateFormat.format(rentalData.getStartDate());
        message += " - " + dateFormat.format(rentalData.getEndDate());
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

    @Override
    public List<Date> lockedDays(PropertyTitleData titleData) {
        List<Date> days = new ArrayList<>();
        Property property = this.repositoryProperty.findByTitle(titleData.getTitle());
        List<Rental> rentals = this.repositoryRental.findByProperty(property);
        for(Rental rental : rentals){
            Date start = rental.getStartDate();
            Date end = rental.getEndDate();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            while (!calendar.getTime().after(end)) {
                days.add(calendar.getTime());
                calendar.add(Calendar.DATE, 1); // Adăugăm o zi
            }
        }
        return days;
    }

    @Override
    public List<Rental> rentalsUser(UserUsernameData userUsernameData) {
        User user = this.repositoryUser.findByUsername(userUsernameData.getUsername());
        if(user != null){
            return this.repositoryRental.findByUser(user);
        }
        return null;
    }

    @Override
    public void deleteRental(RentalData rentalData) {
        User user = this.repositoryUser.findByUsername(rentalData.getUsername_user());
        Property property = this.repositoryProperty.findByTitle(rentalData.getProperty_title());
        if(user != null && property != null){
            List<Rental> rentals = this.repositoryRental.findByUser(user);
            for(Rental rental : rentals){
                if(rental.getProperty().getTitle().equals(property.getTitle())){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String rentalStartDateStr = dateFormat.format(rental.getStartDate());
                    String rentalDataStartDateStr = dateFormat.format(rentalData.getStartDate());
                    String rentalEndDateStr = dateFormat.format(rental.getEndDate());
                    String rentalDataEndDateStr = dateFormat.format(rentalData.getEndDate());
                    if (rentalStartDateStr.equals(rentalDataStartDateStr) && rentalEndDateStr.equals(rentalDataEndDateStr)) {
                        this.repositoryRental.delete(rental);
                        return;
                    }
                }
            }
        }
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
    public void notifyObservers(String message,Observer o,Property property) {
        for(Observer observer : observers){
            if(observer instanceof User && o instanceof User) {
                if (!Objects.equals(((User) observer).getUsername(), ((User) o).getUsername())) {
                    List<Favorite> properties = this.repositoryFavorite.findByUser((User) observer);
                    for(Favorite favorite : properties){
                        if(favorite.getProperty().getTitle().equals(property.getTitle())){
                            NotificationData notificationData  = new NotificationData();
                            notificationData.setUsername(((User) observer).getUsername());
                            notificationData.setMessage(message);
                            this.serviceNotifications.insertNotification(notificationData);
                        }
                    }
                }
            }
        }
    }
}
