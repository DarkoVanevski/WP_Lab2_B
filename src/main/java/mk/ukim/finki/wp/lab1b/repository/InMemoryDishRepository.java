package mk.ukim.finki.wp.lab1b.repository;

import mk.ukim.finki.wp.lab1b.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1b.model.Dish;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository{

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Optional<Dish> findByDishId(String dishId) {
        return DataHolder.dishes.stream().filter(dish -> dish.getDishId().equals(dishId)).findFirst();
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream().filter(dish -> Objects.equals(dish.getId(), id)).findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        // Remove old dish if it exists
        DataHolder.dishes.removeIf(d -> d.getId() != null && d.getId().equals(dish.getId()));
        DataHolder.dishes.add(dish);
        return dish;
    }
    @Override
    public void deleteById(Long id) {
        DataHolder.dishes.removeIf(dish -> dish.getId() != null && dish.getId().equals(id));
    }
}
