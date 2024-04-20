package Proiect_PS.repository;

import Proiect_PS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
