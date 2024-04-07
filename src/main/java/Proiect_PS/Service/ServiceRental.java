package Proiect_PS.Service;

import Proiect_PS.DTO.RentalData;
import Proiect_PS.Model.Rental;
import Proiect_PS.Model.User;
import Proiect_PS.ObserverPattern.Observer;
import Proiect_PS.ObserverPattern.Subject;
import Proiect_PS.Repository.RepositoryProperty;
import Proiect_PS.Repository.RepositoryRental;
import Proiect_PS.Repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ServiceRental implements ServiceRentalInterface, Subject {
    private RepositoryProperty repositoryProperty;
    private RepositoryUser repositoryUser;
    private RepositoryRental repositoryRental;
    private List<Observer> observers;
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

    @Override
    public void insertRental(RentalData rentalData) {
        Rental rental = new Rental();
        rental.setUser(repositoryUser.findByUsername(rentalData.getUsername_user()));
        rental.setProperty(repositoryProperty.findByTitle(rentalData.getProperty_title()));
        rental.setStartDate(rentalData.getStartDate());
        rental.setEndDate(rentalData.getEndDate());
        this.notifyObservers(this.createNotifyMessage(rentalData),rental.getUser());
        repositoryRental.save(rental);
    }
    public String createNotifyMessage(RentalData rentalData){
        String message = "Proprietatea cu titlul ";
        message += rentalData.getProperty_title();
        message += " a fost inchiriata de user-ul " + rentalData.getUsername_user();
        return message;
    }
    @Override
    public void deleteAllRental() {
        this.repositoryRental.deleteAll();
    }

    @Override
    public void updateRental(RentalData rentalData) {
    }

    @Override
    public List<Rental> findAll() {
        return repositoryRental.findAll();
    }

    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

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
