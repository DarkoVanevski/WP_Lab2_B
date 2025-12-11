package mk.ukim.finki.wp.lab1b.web.controller;

import mk.ukim.finki.wp.lab1b.model.Dish;
import mk.ukim.finki.wp.lab1b.service.ChefService;
import mk.ukim.finki.wp.lab1b.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) Integer minTime,
            @RequestParam(required = false) Integer maxTime,
            @RequestParam(required = false) String chefName,
            Model model) {


        List<Dish> dishes = dishService.find(name, cuisine, minTime, maxTime, chefName);

        model.addAttribute("dishes", dishes);
        model.addAttribute("searchName", name);
        model.addAttribute("searchCuisine", cuisine);
        model.addAttribute("searchMinTime", minTime);
        model.addAttribute("searchMaxTime", maxTime);
        model.addAttribute("searchChefName", chefName);

        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }

        return "listDishes";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam(required = false) Long chefId) {
        if (chefId != null) {
            dishService.create(dishId, name, cuisine, preparationTime, chefId);
        } else {
            dishService.create(dishId, name, cuisine, preparationTime);
        }
        return "redirect:/dishes";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam(required = false) Long chefId) {
        if (chefId != null) {
            dishService.update(id, dishId, name, cuisine, preparationTime, chefId);
        } else {
            dishService.update(id, dishId, name, cuisine, preparationTime);
        }
        return "redirect:/dishes";
    }

    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("isEdit", false);
        return "dish-form";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        try {
            Dish dish = dishService.findById(id);
            model.addAttribute("dish", dish);
            model.addAttribute("chefs", chefService.listChefs());
            model.addAttribute("isEdit", true);
            return "dish-form";
        } catch (Exception e) {
            return "redirect:/dishes?error=DishNotFound";
        }
    }

    @PostMapping("/{chefId}/assign/{dishId}")
    public String assignDishToChef(@PathVariable Long chefId, @PathVariable Long dishId) {
        chefService.addDishToChef(chefId, String.valueOf(dishId));
        return "redirect:/chefs/" + chefId;
    }
}