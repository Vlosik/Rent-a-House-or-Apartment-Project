package Proiect_PS.Service;

import Proiect_PS.DTO.RentalData;
import Proiect_PS.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

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
     */
    void insertRental(RentalData rentalData);
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
}
