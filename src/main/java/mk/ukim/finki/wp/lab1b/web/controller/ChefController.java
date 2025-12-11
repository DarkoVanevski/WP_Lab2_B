package mk.ukim.finki.wp.lab1b.web.controller;

import mk.ukim.finki.wp.lab1b.model.Chef;
import mk.ukim.finki.wp.lab1b.service.ChefService;
import mk.ukim.finki.wp.lab1b.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chefs")
public class ChefController {
    private final ChefService chefService;
    private final DishService dishService;

    public ChefController(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }
    @GetMapping
    public String getChefsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("chefs", chefService.listChefs());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }
        return "listChefs";
    }
    @GetMapping("/{id}")
    public String getChefDetails(@PathVariable Long id, Model model) {
        try {
            Chef chef = chefService.findById(id);
            model.addAttribute("chef", chef);
            return "chefDetails";
        } catch (Exception e) {
            return "redirect:/chefs?error=ChefNotFound";
        }
    }

    @GetMapping("/chef-form")
    public String getAddChefPage(Model model) {
        model.addAttribute("chef", new Chef());
        model.addAttribute("isEdit", false);
        return "chef-form";
    }

    @GetMapping("/chef-form/{id}")
    public String getEditChefForm(@PathVariable Long id, Model model) {
        try {
            Chef chef = chefService.findById(id);
            model.addAttribute("chef", chef);
            model.addAttribute("isEdit", true);
            return "chef-form";
        } catch (Exception e) {
            return "redirect:/chefs?error=ChefNotFound";
        }
    }

    @PostMapping("/add")
    public String saveChef(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String bio) {
        chefService.create(firstName, lastName, bio, null);
        return "redirect:/chefs";
    }

    @PostMapping("/edit/{id}")
    public String editChef(@PathVariable Long id,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String bio) {
        chefService.update(id, firstName, lastName, bio, null);
        return "redirect:/chefs";
    }

    @PostMapping("/delete/{id}")
    public String deleteChef(@PathVariable Long id) {
        try {
            chefService.delete(id);
            return "redirect:/chefs";
        } catch (Exception e) {
            return "redirect:/chefs?error=CannotDeleteChef";
        }
    }

    @GetMapping("/{chefId}/assign-dish")
    public String getAssignDishPage(@PathVariable Long chefId, Model model) {
        try {
            Chef chef = chefService.findById(chefId);
            model.addAttribute("chef", chef);
            model.addAttribute("dishes", dishService.listDishes());
            return "assignDish";
        } catch (Exception e) {
            return "redirect:/chefs?error=ChefNotFound";
        }
    }

    @PostMapping("/{chefId}/assign/{dishId}")
    public String assignDishToChef(@PathVariable Long chefId, @PathVariable Long dishId) {
        chefService.addDishToChef(chefId, String.valueOf(dishId));
        return "redirect:/chefs/" + chefId;
    }
}