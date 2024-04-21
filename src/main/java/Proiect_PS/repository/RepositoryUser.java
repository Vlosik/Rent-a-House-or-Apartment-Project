package Proiect_PS.repository;

import Proiect_PS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interfața RepositoryUser oferă metode pentru a accesa și manipula entitățile de tip User în baza de date.
 */
public interface RepositoryUser extends JpaRepository<User,Long> {
    /**
     * Caută un utilizator după numele de utilizator.
     * @param username numele de utilizator al utilizatorului căutat
     * @return utilizatorul care are numele de utilizator specificat
     */
    User findByUsername(String username);
}
