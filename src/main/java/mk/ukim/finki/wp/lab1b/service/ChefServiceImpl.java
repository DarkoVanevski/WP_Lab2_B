package mk.ukim.finki.wp.lab1b.service;

import mk.ukim.finki.wp.lab1b.model.Chef;
import mk.ukim.finki.wp.lab1b.model.Dish;
import mk.ukim.finki.wp.lab1b.repository.ChefRepository;
import mk.ukim.finki.wp.lab1b.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService{
    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = findById(chefId);
        Dish dish = dishRepository.findByDishId(dishId).orElseThrow();

        if(chef !=null && dish != null){
            chef.getDishes().add(dish);
            chefRepository.save(chef);
        }
        return chef;
    }
}
