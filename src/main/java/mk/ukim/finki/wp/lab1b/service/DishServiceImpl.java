package mk.ukim.finki.wp.lab1b.service;

import mk.ukim.finki.wp.lab1b.model.Chef;
import mk.ukim.finki.wp.lab1b.model.Dish;
import mk.ukim.finki.wp.lab1b.repository.ChefRepository;
import mk.ukim.finki.wp.lab1b.repository.DishRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.lab1b.service.specification.FieldFilterSpecification.*;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }
    public List<Dish> find(String name, String cuisine, Integer minTime, Integer maxTime, String chefName) {
        // all filters
        Specification<Dish> specification = Specification.allOf(
                filterContainsText(Dish.class, "name", name),
                filterContainsText(Dish.class, "cuisine", cuisine),
                filterGreaterThanOrEqual(Dish.class, "preparationTime", minTime),
                filterLessThanOrEqual(Dish.class, "preparationTime", maxTime),
                filterContainsText(Dish.class, "chef.firstName", chefName)
        );

        return dishRepository.findAll(specification);
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findById(Long.valueOf(dishId)).orElseThrow();
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow();
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        Dish dish = new Dish();
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);

        return dishRepository.save(dish);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Dish dish = new Dish();
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);

        if (chefId != null) {
            Chef chef = chefRepository.findById(chefId).orElse(null);
            dish.setChef(chef);
        }

        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Dish dish = findById(id);
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);

        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Dish dish = findById(id);
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);

        if (chefId != null) {
            Chef chef = chefRepository.findById(chefId).orElse(null);
            dish.setChef(chef);
        }

        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}