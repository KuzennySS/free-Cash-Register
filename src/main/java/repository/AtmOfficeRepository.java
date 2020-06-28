package repository;

import entity.AtmOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmOfficeRepository extends JpaRepository<AtmOffice, Integer> {
    AtmOffice getById(Integer id);
}
