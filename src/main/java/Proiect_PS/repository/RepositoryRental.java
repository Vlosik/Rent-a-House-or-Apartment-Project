package Proiect_PS.Repository;

import Proiect_PS.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface RepositoryRental extends JpaRepository<Rental,Long> {
    Rental findByCreatedAt(Date date);
}
