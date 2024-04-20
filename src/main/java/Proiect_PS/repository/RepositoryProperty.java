package Proiect_PS.repository;

import Proiect_PS.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface RepositoryProperty extends JpaRepository<Property,Long> {
    @Query("SELECT p FROM Property p WHERE p.title = :title AND p.price = :price AND p.location = :location")
    List<Property> findByTitlePriceAndLocation(String title, BigDecimal price, String location);
    List<Property> findByIsAvailable(Boolean isAvailable);
    Property findByTitle(String title);
}
