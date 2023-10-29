package pl.edu.pjatk.foodbook.foodbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.foodbook.foodbookservice.repository.model.Foodbook;

import java.util.UUID;

@Repository
public interface FoodbookRepository extends JpaRepository<Foodbook, UUID> {

}
