package Proiect_PS.controller;

import Proiect_PS.dto.PropertyTitleData;
import Proiect_PS.dto.RentalData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Rental;
import Proiect_PS.service.ServiceRental;
import Proiect_PS.service.ServiceRentalInterface;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
/**
 * ControllerRental gestionează operațiunile HTTP legate de închirieri.
 * Această clasă permite adăugarea, ștergerea și actualizarea închirierilor,
 * precum și recuperarea tuturor închirierilor din sistem.
 */
@RestController
@RequestMapping("/rental")
public class ControllerRental {
    private ServiceRentalInterface serviceRental;
    /**
     * Construiește un ControllerRental.
     *
     * @param RentalRental Serviciul de închirieri utilizat pentru gestionarea operațiunilor legate de închirieri.
     */
    public ControllerRental(ServiceRental RentalRental){
        this.serviceRental = RentalRental;
    }
    /**
     * Adaugă o nouă închiriere în sistem.
     *
     * @param RentalData Datele închirierii ce urmează a fi adăugată.
     */
    @PostMapping("/insert")
    public void addRental(@RequestBody RentalData RentalData){
        this.serviceRental.insertRental(RentalData);
    }
    /**
     * Șterge toate închirierile din sistem.
     */
    @DeleteMapping("/deleteAll")
    public void deleteAllRental(){
        this.serviceRental.deleteAllRental();
    }
    /**
     * Actualizează o închiriere existentă în sistem.
     *
     * @param RentalData Datele actualizate ale închirierii.
     */
    @PutMapping ("/update")
    public void updateRental(@RequestBody RentalData RentalData){
        this.serviceRental.updateRental(RentalData);
    }
    /**
     * Returnează toate închirierile din sistem.
     *
     * @return Lista tuturor închirierilor.
     */
    @GetMapping("/findAll")
    public List<Rental> findByTitlePriceAndLocation(){
        return this.serviceRental.findAll();
    }

    @PostMapping("/getDays")
    public List<Date> lockedDays(@RequestBody PropertyTitleData propertyTitleData){
        return this.serviceRental.lockedDays(propertyTitleData);
    }

    @PostMapping("/getRentals")
    public List<Rental> rentalsUser(@RequestBody UserUsernameData userUsernameData){
        return this.serviceRental.rentalsUser(userUsernameData);
    }
    @DeleteMapping("/delete")
    public void deleteRental(@RequestBody RentalData rentalData){
        this.serviceRental.deleteRental(rentalData);
    }
}
