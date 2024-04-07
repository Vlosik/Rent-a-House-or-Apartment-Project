package Proiect_PS.Service;

import Proiect_PS.DTO.RentalData;
import Proiect_PS.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRentalInterface {
    void insertRental(RentalData rentalData);
    void deleteAllRental();
    void updateRental(RentalData rentalData);
    List<Rental> findAll();
}
