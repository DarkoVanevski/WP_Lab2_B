package mk.ukim.finki.wp.lab1b.service;

import mk.ukim.finki.wp.lab1b.model.Dish;

import java.util.List;

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
    Dish findById(Long id);
    Dish create(String dishId, String name, String cuisine, int preparationTime);
    Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId); // Нов метод
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId); // Нов метод
    void delete(Long id);
}
