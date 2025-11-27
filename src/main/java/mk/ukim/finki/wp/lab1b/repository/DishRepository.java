package mk.ukim.finki.wp.lab1b.repository;

import mk.ukim.finki.wp.lab1b.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishRepository {
    List<Dish> findAll();
    Optional<Dish> findByDishId(String dishId);
    Optional<Dish> findById(Long id);
    Dish save(Dish dish);
    void deleteById(Long id);
}
