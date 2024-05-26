package Proiect_PS.service;

import Proiect_PS.dto.PropertyTitleData;
import Proiect_PS.dto.RentalData;
import Proiect_PS.dto.UserPasswordData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.Rental;

import java.util.Date;
import java.util.List;
/**
 * Interfața ServiceRentalInterface definește metodele necesare pentru gestionarea închirierilor
 * în cadrul unui sistem de închiriere proprietăți.
 */
public interface ServiceRentalInterface {
    /**
     * Inserează o nouă închiriere în sistem pe baza datelor furnizate.
     *
     * @param rentalData Datele închirierii pentru inserare.
     * @return Inchirierea inserata.
     */
    Rental insertRental(RentalData rentalData);
    /**
     * Șterge toate închirierile din sistem.
     */
    void deleteAllRental();
    /**
     * Actualizează detalii ale unei închirieri existente în sistem.
     *
     * @param rentalData Datele actualizate ale închirierii.
     */
    void updateRental(RentalData rentalData);
    /**
     * Returnează o listă cu toate închirierile din sistem.
     *
     * @return Lista închirierilor.
     */
    List<Rental> findAll();
    List<Date> lockedDays(PropertyTitleData titleData);

    List<Rental> rentalsUser(UserUsernameData userUsernameData);
    void deleteRental(RentalData rentalData);
}
