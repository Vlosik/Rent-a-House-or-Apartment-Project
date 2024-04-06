package Proiect_PS.Repository;

import Proiect_PS.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
