package mk.ukim.finki.wp.lab1b.service;

import mk.ukim.finki.wp.lab1b.model.Chef;
import mk.ukim.finki.wp.lab1b.model.Dish;

import java.util.List;

//    private long id;
//    private String firstName;
//    private String lastName;
//    private String bio;
//    private List<Dish> dishes;

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
    Chef create(String firstName,String lastName,String bio, List<Dish> dishes);
    Chef update(Long id, String firstName,String lastName,String bio, List<Dish> dishes);
    void delete(Long id);
}