package Proiect_PS.repository;

import Proiect_PS.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
/**
 * Interfața RepositoryProperty oferă metode pentru a accesa și manipula entitățile de tip Property în baza de date.
 */
public interface RepositoryProperty extends JpaRepository<Property,Long> {
    /**
     * Caută proprietățile în funcție de titlu, preț și locație.
     * @param title titlul proprietății căutate
     * @param price prețul proprietății căutate
     * @param location locația proprietății căutate
     * @return lista de proprietăți care îndeplinesc criteriile specificate
     */
    @Query("SELECT p FROM Property p WHERE p.title = :title AND p.price = :price AND p.location = :location")
    List<Property> findByTitlePriceAndLocation(String title, BigDecimal price, String location);
    /**
     * Caută proprietățile care sunt disponibile sau nu.
     * @param isAvailable true dacă proprietatea este disponibilă, false altfel
     * @return lista de proprietăți care sunt sau nu sunt disponibile
     */
    List<Property> findByIsAvailable(Boolean isAvailable);
    /**
     * Caută o proprietate după titlul său.
     * @param title titlul proprietății căutate
     * @return proprietatea care are titlul specificat
     */
    Property findByTitle(String title);
}
