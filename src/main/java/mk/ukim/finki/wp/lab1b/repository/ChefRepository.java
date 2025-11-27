package mk.ukim.finki.wp.lab1b.repository;

import mk.ukim.finki.wp.lab1b.model.Chef;

import java.util.List;
import java.util.Optional;

public interface ChefRepository {
    List<Chef> findAll();
    Optional<Chef> findById(Long id);
    Chef save(Chef chef);
}
