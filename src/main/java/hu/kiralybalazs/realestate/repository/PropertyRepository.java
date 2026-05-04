package hu.kiralybalazs.realestate.repository;

import hu.kiralybalazs.realestate.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    // Itt nem kell semmit írnod, a JpaRepository-ból jön a findAll() és a save()
}