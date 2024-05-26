package Proiect_PS.repository;

import Proiect_PS.model.Favorite;
import Proiect_PS.model.Property;
import Proiect_PS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface RepositoryFavorite extends JpaRepository<Favorite,Long> {
    List<Favorite> findByUser(User user);
    List<Favorite> findByProperty(Property property);
}
