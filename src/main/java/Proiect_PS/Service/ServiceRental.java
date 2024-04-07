package Proiect_PS.Service;

import Proiect_PS.DTO.RentalData;
import Proiect_PS.Model.Rental;
import Proiect_PS.Repository.RepositoryProperty;
import Proiect_PS.Repository.RepositoryRental;
import Proiect_PS.Repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceRental implements ServiceRentalInterface {
    private RepositoryProperty repositoryProperty;
    private RepositoryUser repositoryUser;
    private RepositoryRental repositoryRental;
    public ServiceRental(RepositoryProperty repositoryProperty,RepositoryUser repositoryUser, RepositoryRental repositoryRental){
        this.repositoryRental = repositoryRental;
        this.repositoryProperty = repositoryProperty;
        this.repositoryUser = repositoryUser;
    }

    @Override
    public void insertRental(RentalData rentalData) {
        Rental rental = new Rental();
        rental.setUser(repositoryUser.findByUsername(rentalData.getUsername_user()));
        rental.setProperty(repositoryProperty.findByTitle(rentalData.getProperty_title()));
        rental.setStartDate(rentalData.getStartDate());
        rental.setEndDate(rentalData.getEndDate());
        repositoryRental.save(rental);
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
}
