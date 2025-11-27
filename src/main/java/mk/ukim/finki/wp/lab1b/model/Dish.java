package mk.ukim.finki.wp.lab1b.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class Dish {
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }

    public Dish() {

    }
}
