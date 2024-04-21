package Proiect_PS.repository;

import Proiect_PS.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
/**
 * Interfața RepositoryRental oferă metode pentru a accesa și manipula entitățile de tip Rental în baza de date.
 */
public interface RepositoryRental extends JpaRepository<Rental,Long> {
    /**
     * Caută un contract de închiriere după data la care a fost creat.
     * @param date data la care a fost creat contractul de închiriere căutat
     * @return contractul de închiriere creat la data specificată
     */
    Rental findByCreatedAt(Date date);
}
