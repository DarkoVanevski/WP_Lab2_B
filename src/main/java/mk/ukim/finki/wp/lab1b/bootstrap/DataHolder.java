package mk.ukim.finki.wp.lab1b.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab1b.model.Chef;
import mk.ukim.finki.wp.lab1b.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {

        // Create dishes with IDs
        Dish d1 = new Dish(1L, "1", "Cordon Bleu", "French", 25);
        Dish d2 = new Dish(2L, "2", "Ramen", "Japanese", 15);
        Dish d3 = new Dish(3L, "3", "Tacos al Pastor", "Mexican", 12);
        Dish d4 = new Dish(4L, "4", "Butter Chicken", "Indian", 20);
        Dish d5 = new Dish(5L, "5", "Beef Wellington", "British", 30);

        dishes.addAll(List.of(d1, d2, d3, d4, d5));

        chefs.add(new Chef(
                1L, "Gordon", "Ramsay",
                "British chef known for his fiery personality and Michelin-starred restaurants.",
                List.of(d1, d5)
        ));

        chefs.add(new Chef(
                2L, "Masaharu", "Morimoto",
                "Japanese Iron Chef famous for his creative fusion dishes.",
                List.of(d2)
        ));

        chefs.add(new Chef(
                3L, "Clare", "Smyth",
                "First female British chef to hold three Michelin stars.",
                List.of(d5)
        ));

        chefs.add(new Chef(
                4L, "Enrique", "Olvera",
                "Mexican chef celebrated for modernizing traditional Mexican cuisine.",
                List.of(d3)
        ));

        chefs.add(new Chef(
                5L, "Sanjeev", "Kapoor",
                "Famous Indian chef, TV host, and restaurateur.",
                List.of(d4)
        ));
    }
}
