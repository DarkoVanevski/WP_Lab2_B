package mk.ukim.finki.wp.lab1b.service;

import mk.ukim.finki.wp.lab1b.model.Chef;

import java.util.List;

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
}