package Proiect_PS.repository;

import Proiect_PS.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface RepositoryRental extends JpaRepository<Rental,Long> {
    Rental findByCreatedAt(Date date);
}
